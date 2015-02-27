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
package org.estatio.fixture;

import javax.inject.Inject;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;
import org.estatio.dom.agreement.AgreementRoleCommunicationChannelType;
import org.estatio.dom.agreement.AgreementRoleType;
import org.estatio.dom.agreement.AgreementType;
import org.estatio.dom.asset.registration.FixedAssetRegistrationType;
import org.estatio.dom.charge.Charge;
import org.estatio.dom.charge.ChargeGroup;
import org.estatio.dom.currency.Currency;
import org.estatio.dom.geography.Country;
import org.estatio.dom.geography.State;
import org.estatio.dom.index.Index;
import org.estatio.dom.index.IndexBase;
import org.estatio.dom.index.IndexValue;
import org.estatio.dom.lease.LeaseType;
import org.estatio.dom.tax.Tax;
import org.estatio.dom.tax.TaxRate;
import org.estatio.services.links.Link;


/**
 * No longer used, see algorithm in {@link EstatioBaseLineFixture}.
 */
public class EstatioRefDataTeardownFixture extends FixtureScript {

    @Override
    protected void execute(ExecutionContext fixtureResults) {
        //tearDownJdo();
        tearDownSQL();
    }

    private void tearDownJdo() {
        // TODO: not sure that this actually does anything :-(
        // using tearDownSQL() instead
        isisJdoSupport.deleteAll(
            State.class,
            Country.class,
            Currency.class,
            Charge.class,
            ChargeGroup.class,
            TaxRate.class,
            Tax.class,
            FixedAssetRegistrationType.class,
            LeaseType.class,
            AgreementRoleCommunicationChannelType.class,
            AgreementRoleType.class,
            AgreementType.class,
            IndexValue.class,
            IndexBase.class,
            Index.class,
            Link.class
        );
    }

    private void tearDownSQL() {

        isisJdoSupport.executeUpdate("DELETE FROM \"State\"");
        isisJdoSupport.executeUpdate("DELETE FROM \"Country\"");
        
        isisJdoSupport.executeUpdate("DELETE FROM \"Currency\"");
        isisJdoSupport.executeUpdate("DELETE FROM \"Charge\"");
        isisJdoSupport.executeUpdate("DELETE FROM \"ChargeGroup\"");
        
        isisJdoSupport.executeUpdate("DELETE FROM \"TaxRate\"");
        isisJdoSupport.executeUpdate("DELETE FROM \"Tax\"");
        
        isisJdoSupport.executeUpdate("DELETE FROM \"IndexValue\"");
        isisJdoSupport.executeUpdate("DELETE FROM \"IndexBase\"");
        isisJdoSupport.executeUpdate("DELETE FROM \"Index\"");
        
        isisJdoSupport.executeUpdate("DELETE FROM \"Link\"");
    }

    @Inject
    private IsisJdoSupport isisJdoSupport;

}
