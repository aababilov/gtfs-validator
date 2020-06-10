/*
 *  Copyright (c) 2020. MobilityData IO.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.mobilitydata.gtfsvalidator.domain.entity.notice.warning;

import org.mobilitydata.gtfsvalidator.domain.entity.notice.NoticeExporter;
import org.mobilitydata.gtfsvalidator.domain.entity.notice.base.WarningNotice;

import java.io.IOException;

public class MissingFeedEndDateNotice extends WarningNotice {
    final private String fieldName;

    public MissingFeedEndDateNotice(final String entityId) {
        super("feed_info.txt", W_010, "Missing `feed_end_date`",
                "Entity with `feed_publisher_name`: " + entityId + " should provide non null value for field" +
                        "`feed_end_date` since a value for field `feed_start_date` is provided", entityId);
        this.fieldName = "feed_end_date";
    }

    @Override
    public void export(final NoticeExporter exporter) throws IOException {
        exporter.export(this);
    }

    public String getFieldName(){
        return fieldName;
    }
}