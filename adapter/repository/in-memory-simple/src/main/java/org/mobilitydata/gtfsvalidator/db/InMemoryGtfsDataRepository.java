/*
 * Copyright (c) 2020. MobilityData IO.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mobilitydata.gtfsvalidator.db;

import org.mobilitydata.gtfsvalidator.domain.entity.gtfs.Agency;
import org.mobilitydata.gtfsvalidator.usecase.port.GtfsDataRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGtfsDataRepository implements GtfsDataRepository {

    private final Map<String, Agency> agencyCollection = new HashMap<>();

    public Map<String, Agency> getAgencyCollection() {
        return agencyCollection;
    }

    @Override
    public Agency addEntity(final Agency newAgency) {
        agencyCollection.put(newAgency.getAgencyName(), newAgency);
        return newAgency;
    }

    @Override
    public Agency getAgencyByName(final String agencyName) {
        return agencyCollection.get(agencyName);
    }
}