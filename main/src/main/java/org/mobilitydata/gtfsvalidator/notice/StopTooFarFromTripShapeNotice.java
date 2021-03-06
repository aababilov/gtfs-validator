/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mobilitydata.gtfsvalidator.notice;

import com.google.common.collect.ImmutableMap;

/**
 * "Route alignments (in shapes.txt) should be within 100 meters of stop locations which a trip
 * serves." (http://gtfs.org/best-practices/#shapestxt)
 *
 * <p>Severity: {@code SeverityLevel.WARNING}
 */
public class StopTooFarFromTripShapeNotice extends ValidationNotice {
  public StopTooFarFromTripShapeNotice(
      final String stopId,
      final int stopSequence,
      final String tripId,
      final String shapeId,
      final double stopShapeThresholdMeters) {
    super(
        ImmutableMap.of(
            "stopId", stopId,
            "stopSequence", stopSequence,
            "tripId", tripId,
            "shapeId", shapeId,
            "stopShapeThresholdMeters", stopShapeThresholdMeters),
        SeverityLevel.WARNING);
  }

  @Override
  public String getCode() {
    return "stop_too_far_from_trip_shape";
  }
}
