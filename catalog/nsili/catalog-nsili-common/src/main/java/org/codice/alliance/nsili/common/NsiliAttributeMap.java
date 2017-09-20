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
package org.codice.alliance.nsili.common;

import ddf.catalog.data.Metacard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NsiliAttributeMap {
  private static Map<String, String> nsiliToDdfMap = new HashMap<>();

  private static Map<String, List<String>> ddfToNsiliMap = new HashMap<>();

  static {
    // Build up attribute mappings

    nsiliToDdfMap.put(
        NsiliConstants.NSIL_APPROVAL + "." + NsiliConstants.APPROVED_BY, "approvedBy");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_APPROVAL + "." + NsiliConstants.DATE_TIME_MODIFIED,
        "approvedDateTimeModified");
    nsiliToDdfMap.put(NsiliConstants.NSIL_APPROVAL + "." + NsiliConstants.STATUS, "approvalStatus");
    nsiliToDdfMap.put(NsiliConstants.NSIL_CARD + "." + NsiliConstants.IDENTIFIER, Metacard.ID);
    nsiliToDdfMap.put(NsiliConstants.NSIL_CARD + "." + NsiliConstants.PUBLISHER, "publisher");
    nsiliToDdfMap.put(NsiliConstants.NSIL_CARD + "." + NsiliConstants.SOURCE_LIBRARY, "sourceId");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_CARD + "." + NsiliConstants.SOURCE_DATE_TIME_MODIFIED,
        Metacard.EFFECTIVE);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_CARD + "." + NsiliConstants.DATE_TIME_MODIFIED, Metacard.MODIFIED);
    nsiliToDdfMap.put(NsiliConstants.NSIL_CARD + "." + NsiliConstants.STATUS, "status");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_COMMON + "." + NsiliConstants.IDENTIFIER_MISSION, "identifierMission");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_COMMON + "." + NsiliConstants.IDENTIFIER_UUID, Metacard.ID);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_COMMON + "." + NsiliConstants.IDENTIFIER_JC3IEDM, "identiferJC3Idm");
    nsiliToDdfMap.put(NsiliConstants.NSIL_COMMON + "." + NsiliConstants.LANGUAGE, "language");
    nsiliToDdfMap.put(NsiliConstants.NSIL_COMMON + "." + NsiliConstants.SOURCE, "NSILISource");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_COMMON + "." + NsiliConstants.SUBJECT_CATEGORY_TARGET,
        "subjectCategoryTarget");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_COMMON + "." + NsiliConstants.TARGET_NUMBER, "targetNumber");
    nsiliToDdfMap.put(NsiliConstants.NSIL_COMMON + "." + NsiliConstants.TYPE, "productType");
    nsiliToDdfMap.put(NsiliConstants.NSIL_CXP + "." + NsiliConstants.STATUS, "cxpStatus");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_COVERAGE + "." + NsiliConstants.SPATIAL_COUNTRY_CODE, "countryCode");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_COVERAGE + "." + NsiliConstants.SPATIAL_GEOGRAPHIC_REF_BOX,
        Metacard.GEOGRAPHY);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_COVERAGE + "." + NsiliConstants.TEMPORAL_START, "startDateTime");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_COVERAGE + "." + NsiliConstants.TEMPORAL_END, "endDateTime");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_EXPLOITATION_INFO + "." + NsiliConstants.DESCRIPTION,
        "exploitationDescription");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_EXPLOITATION_INFO + "." + NsiliConstants.LEVEL, "exploitationLevel");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_EXPLOITATION_INFO + "." + NsiliConstants.AUTO_GENERATED,
        "exploitationAutoGenerated");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_EXPLOITATION_INFO + "." + NsiliConstants.SUBJ_QUALITY_CODE,
        "exploitationSubjectiveQualityCode");
    nsiliToDdfMap.put(NsiliConstants.NSIL_FILE + "." + NsiliConstants.ARCHIVED, "fileArchived");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_FILE + "." + NsiliConstants.ARCHIVE_INFORMATION, "fileArchivedInfo");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_FILE + "." + NsiliConstants.CREATOR, Metacard.POINT_OF_CONTACT);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_FILE + "." + NsiliConstants.DATE_TIME_DECLARED, "productCreateTime");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_FILE + "." + NsiliConstants.EXTENT, Metacard.RESOURCE_SIZE);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_FILE + "." + NsiliConstants.FORMAT, Metacard.CONTENT_TYPE);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_FILE + "." + NsiliConstants.FORMAT_VERSION,
        Metacard.CONTENT_TYPE_VERSION);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_FILE + "." + NsiliConstants.PRODUCT_URL,
        Metacard.RESOURCE_DOWNLOAD_URL);
    nsiliToDdfMap.put(NsiliConstants.NSIL_FILE + "." + NsiliConstants.TITLE, Metacard.TITLE);
    nsiliToDdfMap.put(NsiliConstants.NSIL_GMTI + "." + NsiliConstants.IDENTIFIER_JOB, "jobId");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_GMTI + "." + NsiliConstants.NUMBER_OF_TARGET_REPORTS,
        "numTargetReports");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.CATEGORY, "imageryCategory");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.CLOUD_COVER_PCT, "cloudCoverPct");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.COMMENTS, "imageryComments");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.DECOMPRESSION_TECHNIQUE,
        "decompressionTechnique");
    nsiliToDdfMap.put(NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.IDENTIFIER, "imageId");
    nsiliToDdfMap.put(NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.NIIRS, "niirs");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.NUMBER_OF_BANDS, "numBands");
    nsiliToDdfMap.put(NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.NUMBER_OF_ROWS, "numRows");
    nsiliToDdfMap.put(NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.NUMBER_OF_COLS, "numCols");
    nsiliToDdfMap.put(NsiliConstants.NSIL_IMAGERY + "." + NsiliConstants.TITLE, Metacard.TITLE);
    nsiliToDdfMap.put(NsiliConstants.NSIL_MESSAGE + "." + NsiliConstants.RECIPIENT, "recipient");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_MESSAGE + "." + NsiliConstants.MESSAGE_BODY, Metacard.DESCRIPTION);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_MESSAGE + "." + NsiliConstants.MESSAGE_TYPE, "messageType");
    nsiliToDdfMap.put(NsiliConstants.NSIL_MESSAGE + "." + NsiliConstants.SUBJECT, Metacard.TITLE);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_METADATA_SECURITY + "." + NsiliConstants.CLASSIFICATION,
        "securityClassification");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_METADATA_SECURITY + "." + NsiliConstants.POLICY, "securityPolicy");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_METADATA_SECURITY + "." + NsiliConstants.RELEASABILITY,
        "securityReleasability");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_SECURITY + "." + NsiliConstants.CLASSIFICATION,
        "securityClassification");
    nsiliToDdfMap.put(NsiliConstants.NSIL_SECURITY + "." + NsiliConstants.POLICY, "securityPolicy");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_SECURITY + "." + NsiliConstants.RELEASABILITY, "securityReleasability");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_REPORT + "." + NsiliConstants.ORIGINATORS_REQ_SERIAL_NUM,
        "originatorsRequestSerialNumber");
    nsiliToDdfMap.put(NsiliConstants.NSIL_REPORT + "." + NsiliConstants.PRIORITY, "reportPriority");
    nsiliToDdfMap.put(NsiliConstants.NSIL_REPORT + "." + NsiliConstants.TYPE, "reportType");
    nsiliToDdfMap.put(NsiliConstants.NSIL_RFI + "." + NsiliConstants.FOR_ACTION, "forAction");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_RFI + "." + NsiliConstants.FOR_INFORMATION, "forInformation");
    nsiliToDdfMap.put(NsiliConstants.NSIL_RFI + "." + NsiliConstants.SERIAL_NUMBER, "serialNumber");
    nsiliToDdfMap.put(NsiliConstants.NSIL_RFI + "." + NsiliConstants.STATUS, "rfiStatus");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_RFI + "." + NsiliConstants.WORKFLOW_STATUS, "workflowStatus");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_SDS + "." + NsiliConstants.OPERATIONAL_STATUS, "sdsOperationalStatus");
    nsiliToDdfMap.put(NsiliConstants.NSIL_STREAM + "." + NsiliConstants.ARCHIVED, "archived");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_STREAM + "." + NsiliConstants.ARCHIVE_INFORMATION, "archivalInfo");
    nsiliToDdfMap.put(NsiliConstants.NSIL_STREAM + "." + NsiliConstants.CREATOR, "creator");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_STREAM + "." + NsiliConstants.DATE_TIME_DECLARED, "dateTimeDeclared");
    nsiliToDdfMap.put(NsiliConstants.NSIL_STREAM + "." + NsiliConstants.PROGRAM_ID, "programId");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_STREAM + "." + NsiliConstants.STANDARD, Metacard.CONTENT_TYPE);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_STREAM + "." + NsiliConstants.STANDARD_VERSION,
        Metacard.CONTENT_TYPE_VERSION);
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_STREAM + "." + NsiliConstants.SOURCE_URL,
        Metacard.RESOURCE_DOWNLOAD_URL);
    nsiliToDdfMap.put(NsiliConstants.NSIL_TASK + "." + NsiliConstants.COMMENTS, "comments");
    nsiliToDdfMap.put(NsiliConstants.NSIL_TASK + "." + NsiliConstants.STATUS, "taskStatus");
    nsiliToDdfMap.put(NsiliConstants.NSIL_TDL + "." + NsiliConstants.ACTIVITY, "activity");
    nsiliToDdfMap.put(NsiliConstants.NSIL_TDL + "." + NsiliConstants.MESSAGE_NUM, "messageNumber");
    nsiliToDdfMap.put(NsiliConstants.NSIL_TDL + "." + NsiliConstants.PLATFORM, "platform");
    nsiliToDdfMap.put(NsiliConstants.NSIL_TDL + "." + NsiliConstants.TRACK_NUM, "trackNumber");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_VIDEO + "." + NsiliConstants.AVG_BIT_RATE, "averageBitRate");
    nsiliToDdfMap.put(NsiliConstants.NSIL_VIDEO + "." + NsiliConstants.CATEGORY, "category");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_VIDEO + "." + NsiliConstants.ENCODING_SCHEME, "encodingScheme");
    nsiliToDdfMap.put(NsiliConstants.NSIL_VIDEO + "." + NsiliConstants.FRAME_RATE, "frameRate");
    nsiliToDdfMap.put(NsiliConstants.NSIL_VIDEO + "." + NsiliConstants.NUMBER_OF_ROWS, "numRows");
    nsiliToDdfMap.put(NsiliConstants.NSIL_VIDEO + "." + NsiliConstants.NUMBER_OF_COLS, "numCols");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_VIDEO + "." + NsiliConstants.METADATA_ENC_SCHEME,
        "metadataEncodingScheme");
    nsiliToDdfMap.put(NsiliConstants.NSIL_VIDEO + "." + NsiliConstants.MISM_LEVEL, "mismLevel");
    nsiliToDdfMap.put(
        NsiliConstants.NSIL_VIDEO + "." + NsiliConstants.SCANNING_MODE, "scanningMode");

    for (Map.Entry<String, String> nsiliFieldEntry : nsiliToDdfMap.entrySet()) {
      List<String> nsiliList = ddfToNsiliMap.get(nsiliFieldEntry.getValue());
      if (nsiliList == null) {
        nsiliList = new ArrayList<>(2);
        ddfToNsiliMap.put(nsiliFieldEntry.getValue(), nsiliList);
      }
      nsiliList.add(nsiliFieldEntry.getKey());
    }
    // Manual Mappings for NSIL attribute mapping to more than one DDF Attr
    ddfToNsiliMap.put(
        Metacard.CREATED,
        Arrays.asList(NsiliConstants.NSIL_CARD + "." + NsiliConstants.SOURCE_DATE_TIME_MODIFIED));
  }

  public static String getDdfAttributeForNsili(String nsiliAttribute, boolean removeSourceLibrary) {
    String attribute = nsiliAttribute;
    if (attribute.contains(".")) {
      attribute = attribute.substring(attribute.lastIndexOf(":") + 1);
    }
    String attributeName = nsiliToDdfMap.get(attribute);

    if (removeSourceLibrary) {
      if (nsiliAttribute != null
          && nsiliAttribute.equalsIgnoreCase(
              NsiliConstants.NSIL_CARD + "." + NsiliConstants.SOURCE_LIBRARY)) {
        attributeName = null;
      }
    }

    return attributeName;
  }

  public static List<String> getNsiliAttributeForDdf(String ddfAttribute) {
    return ddfToNsiliMap.get(ddfAttribute);
  }
}
