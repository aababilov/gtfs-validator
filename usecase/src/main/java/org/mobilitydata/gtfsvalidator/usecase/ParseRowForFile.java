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

package org.mobilitydata.gtfsvalidator.usecase;

import org.mobilitydata.gtfsvalidator.domain.entity.ParsedEntity;
import org.mobilitydata.gtfsvalidator.domain.entity.RawEntity;
import org.mobilitydata.gtfsvalidator.domain.entity.RawFileInfo;
import org.mobilitydata.gtfsvalidator.usecase.notice.CannotConstructDataProviderNotice;
import org.mobilitydata.gtfsvalidator.usecase.port.GtfsSpecRepository;
import org.mobilitydata.gtfsvalidator.usecase.port.RawFileRepository;
import org.mobilitydata.gtfsvalidator.usecase.port.ValidationResultRepository;

import java.util.concurrent.atomic.AtomicReference;

public class ParseRowForFile {

    private final RawFileInfo rawFileInfo;
    private final RawFileRepository rawFileRepo;
    private final GtfsSpecRepository specRepo;
    private final ValidationResultRepository resultRepo;
    private RawFileRepository.RawEntityProvider provider;
    private GtfsSpecRepository.RawEntityParser parser;

    public ParseRowForFile(final RawFileInfo rawFileInfo,
                           final RawFileRepository rawFileRepo,
                           final GtfsSpecRepository specRepo,
                           final ValidationResultRepository resultRepo) {
        this.rawFileInfo = rawFileInfo;
        this.rawFileRepo = rawFileRepo;
        this.specRepo = specRepo;
        this.resultRepo = resultRepo;

        rawFileRepo.getProviderForFile(rawFileInfo).ifPresentOrElse(
                provider -> {
                    this.parser = specRepo.getParserForFile(rawFileInfo);
                    this.provider = provider;
                },
                () -> resultRepo.addNotice(new CannotConstructDataProviderNotice(rawFileInfo.getFilename()))
        );
    }

    public boolean hasNext() {
        return provider.hasNext();
    }

    public ParsedEntity getNext() {
        RawEntity rawEntity = provider.getNext();
        //parser.validateNumericTypes(rawEntity).forEach(resultRepo::addNotice);
        return parser.parse(rawEntity);
    }
}
