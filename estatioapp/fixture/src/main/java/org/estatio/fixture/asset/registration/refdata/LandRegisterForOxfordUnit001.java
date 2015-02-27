/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.fixture.asset.registration.refdata;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.estatio.dom.asset.Properties;
import org.estatio.dom.asset.Unit;
import org.estatio.dom.asset.registration.LandRegister;
import org.estatio.dom.asset.registration.LandRegisters;
import org.estatio.fixture.EstatioFixtureScript;
import org.estatio.fixture.asset._PropertyForOxfGb;

public class LandRegisterForOxfordUnit001 extends EstatioFixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
        // prereqs
        if (isExecutePrereqs()) {
            executionContext.executeChild(this, new _PropertyForOxfGb());
        }

        Unit unit = properties.findPropertyByReference(_PropertyForOxfGb.REF).getUnits().first();

        LandRegister landRegister = landRegisters.newRegistration(
                unit,
                null,
                "comuneAmministrativo",
                "comuneCatastale",
                "codiceComuneCatastale",
                new BigDecimal("123.45"),
                "foglio",
                "particella",
                "subalterno",
                "categoria",
                "classe",
                "consistenza",
                null,
                "description");

        executionContext.addResult(this, landRegister.title(), landRegister);
    }

    @Inject
    Properties properties;

    @Inject
    LandRegisters landRegisters;

}
