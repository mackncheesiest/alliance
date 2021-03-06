/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package org.codice.alliance.imaging.chip.actionprovider;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import ddf.action.Action;
import ddf.action.MultiActionProvider;
import ddf.action.impl.ActionImpl;
import ddf.catalog.data.Attribute;
import ddf.catalog.data.Metacard;
import ddf.catalog.data.types.Core;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.codice.ddf.configuration.SystemBaseUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImagingChipActionProvider implements MultiActionProvider {

  public static final String TITLE = "Chip Image";

  public static final String DESCRIPTION =
      "Opens a new window to enter the boundaries of an image chip for a Metacard.";

  public static final String PATH = "/chipping/chipping.html";

  public static final String ID = "catalog.data.metacard.image.chipping";

  private static final Logger LOGGER = LoggerFactory.getLogger(ImagingChipActionProvider.class);

  private static final String NITF_IMAGE_METACARD_TYPE = "isr.image";

  private static final String ORIGINAL_QUALIFIER = "original";

  private static final String QUALIFIER_KEY = "qualifier";

  private GeometryFactory geometryFactory = new GeometryFactory();

  @Override
  public <T> List<Action> getActions(T input) {
    if (input == null) {
      LOGGER.debug("Metacard can not be null.");
      return new ArrayList<>();
    }

    if (canHandle(input)) {
      final Metacard metacard = (Metacard) input;

      URL url;
      String chipPath = null;
      try {
        chipPath =
            String.format(
                "%s%s?id=%s&source=%s",
                getUrl(metacard), PATH, metacard.getId(), metacard.getSourceId());
        url = new URL(chipPath);
      } catch (MalformedURLException e) {
        LOGGER.debug("Invalid URL for chipping path : {}", chipPath, e);
        return new ArrayList<>();
      }

      return Collections.singletonList(new ActionImpl(getId(), TITLE, DESCRIPTION, url));
    }

    return new ArrayList<>();
  }

  private String getUrl(Metacard metacard) {
    String resultUrl = getResultUrl(metacard, Metacard.DERIVED_RESOURCE_DOWNLOAD_URL);
    if (resultUrl != null) {
      return resultUrl;
    }

    resultUrl = getResultUrl(metacard, Metacard.DERIVED_RESOURCE_URI);
    if (resultUrl != null) {
      return resultUrl;
    }

    return SystemBaseUrl.getBaseUrl();
  }

  @Nullable
  private String getResultUrl(Metacard metacard, String attributeName) {
    if (hasValueForAttribute(metacard, attributeName)) {
      try {
        return getBaseUrlFromAttribute(metacard, attributeName);
      } catch (MalformedURLException e) {
        LOGGER.debug("Could not transform derived resource download URL from string to URL", e);
      }
    }
    return null;
  }

  @Nullable
  private String getBaseUrlFromAttribute(Metacard metacard, String attributeName)
      throws MalformedURLException {
    URL url = new URL((String) metacard.getAttribute(attributeName).getValue());
    if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
      return null;
    }

    String resultUrl = String.format("%s://%s", url.getProtocol(), url.getHost());
    resultUrl += url.getPort() != -1 ? ":" + url.getPort() : "";
    return resultUrl;
  }

  private boolean hasValueForAttribute(Metacard metacard, String attributeName) {
    return Optional.of(metacard)
        .map(m -> m.getAttribute(attributeName))
        .map(Attribute::getValue)
        .map(Objects::nonNull)
        .orElse(false);
  }

  @Override
  public String getId() {
    return ID;
  }

  @Override
  public <T> boolean canHandle(T subject) {
    boolean canHandle = false;

    if (subject instanceof Metacard) {
      Metacard metacard = (Metacard) subject;

      boolean isImageNitf = NITF_IMAGE_METACARD_TYPE.equals(metacard.getMetacardType().getName());
      boolean hasLocation = hasValidLocation(metacard.getLocation());
      boolean hasDerivedImage = hasOriginalDerivedResource(metacard);

      canHandle = isImageNitf && hasLocation && hasDerivedImage;
    }

    return canHandle;
  }

  private boolean hasOriginalDerivedResource(Metacard metacard) {
    Attribute attribute = metacard.getAttribute(Core.DERIVED_RESOURCE_URI);

    return Stream.of(attribute)
        .filter(Objects::nonNull)
        .flatMap(a -> a.getValues().stream())
        .filter(String.class::isInstance)
        .map(String.class::cast)
        .anyMatch(this::hasOriginalQualifier);
  }

  private boolean hasOriginalQualifier(String uriString) {
    try {
      URI derivedResourceUri = new URI(uriString);

      // find the #original URI fragment for local or =original param for remote
      if (ORIGINAL_QUALIFIER.equals(derivedResourceUri.getFragment())
          || ORIGINAL_QUALIFIER.equals(getQualifierForRemoteResource(uriString))) {
        return true;
      }
    } catch (URISyntaxException use) {
      LOGGER.debug("Could not parse URI string [{}]", uriString);
    }
    return false;
  }

  private boolean hasValidLocation(String location) {
    boolean hasValidLocation = false;

    if (StringUtils.isNotBlank(location)) {
      try {
        // parse the WKT location to determine if it has valid format
        WKTReader wktReader = new WKTReader(geometryFactory);
        wktReader.read(location);
        hasValidLocation = true;
      } catch (ParseException e) {
        LOGGER.debug("Location [{}] is invalid, cannot chip this image", location);
      }
    }

    return hasValidLocation;
  }

  private String getQualifierForRemoteResource(String uriString) throws URISyntaxException {
    return URLEncodedUtils.parse(new URI(uriString), StandardCharsets.UTF_8.name())
        .stream()
        .filter(pair -> QUALIFIER_KEY.equals(pair.getName()))
        .map(NameValuePair::getValue)
        .filter(ORIGINAL_QUALIFIER::equals)
        .findFirst()
        .orElse(""); // default
  }
}
