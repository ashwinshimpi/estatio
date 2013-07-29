/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.estatio.integration.glue.agreement;

import com.google.common.base.Objects;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.joda.time.LocalDate;
import org.junit.Assert;

import org.apache.isis.core.specsupport.specs.CukeGlueAbstract;
import org.apache.isis.core.specsupport.specs.V;

import org.estatio.dom.agreement.AgreementRole;
import org.estatio.dom.party.Party;
import org.estatio.integration.glue.ActionWithDateParameter;
import org.estatio.integration.glue.agreement.AgreementRoleGlue_succeededBy_precededBy.ActionInvokedWithPartyDateDate;
import org.estatio.integration.glue.agreement.AgreementRoleGlue_succeededBy_precededBy.SucceededByAction;
import org.estatio.integration.spectransformers.ETO;

public class AgreementRoleGlue_updateDates extends CukeGlueAbstract {
    
    class UpdateDatesAction implements ActionWithDateParameter {
        
        private final AgreementRole agreementRole;
        UpdateDatesAction(AgreementRole agreementRole) {
            this.agreementRole = agreementRole;
        }
        
        @Override
        public LocalDate defaultDateParameter(String paramName) {
            if("start date".equals(paramName)) {
                return agreementRole.default0UpdateDates();
            }
            if("end date".equals(paramName)) {
                return agreementRole.default1UpdateDates();
            }
            throw new IllegalArgumentException("Unknown parameter name '" + paramName + "'");
        }
        public void invoke(LocalDate startDate, LocalDate endDate) {
            try {
                wrap(agreementRole).updateDates(startDate, endDate);
            } catch(Exception ex) {
                putVar("exception", "exception", ex);
            }
        }
    }
    
    @Given("^.*want to update.* dates.*indicated agreement role$")
    public void I_want_to_update_the_dates_on_the_indicated_agreement_role() throws Throwable {
        final AgreementRole agreementRole = getVar("agreementRole", "indicated", AgreementRole.class);
        putVar("isis-action", "updateDates",  new UpdateDatesAction(agreementRole));
    }

    @When("^.*invoke the action, start date.* \"([^\"]*)\", end date.* \"([^\"]*)\"$")
    public void I_invoke_the_action_start_date_end_date(
            @Transform(V.LyyyyMMdd.class) LocalDate startDate, 
            @Transform(V.LyyyyMMdd.class) LocalDate endDate) throws Throwable {
        
        nextTransaction();
        
        UpdateDatesAction action = 
                getVar("isis-action", null, UpdateDatesAction.class);
        action.invoke(startDate, endDate);
    }    
}
