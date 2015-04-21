/*
 * Copyright 2012-2015 Eurocommercial Properties NV
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.estatio.dom.budget;

import java.math.BigDecimal;

import org.apache.isis.applib.annotation.MemberOrder;

import org.estatio.dom.asset.Unit;
import org.estatio.dom.lease.Occupancy;

public class BudgetCalculationItem {

    public BudgetCalculationItem(Occupancy occupancy){
        this.setOccupancy(occupancy);
        this.setUnit(occupancy.getUnit());
    }

    //region > occupancy (property)
    private Occupancy occupancy;

    @MemberOrder(sequence = "1")
    public Occupancy getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(final Occupancy occupancy) {
        this.occupancy = occupancy;
    }
    //endregion

    //region > unit (property)
    private Unit unit;

    @MemberOrder(sequence = "2")
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(final Unit unit) {
        this.unit = unit;
    }
    //endregion

    //region > keyValue (property)
    private BigDecimal keyValue;

    @MemberOrder(sequence = "3")
    public BigDecimal getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(final BigDecimal keyValue) {
        this.keyValue = keyValue;
    }
    //endregion

    //region > budgetedValue (property)
    private BigDecimal budgetedValue;

    @MemberOrder(sequence = "4")
    public BigDecimal getBudgetedValue() {
        return budgetedValue;
    }

    public void setBudgetedValue(final BigDecimal budgetedValue) {
        this.budgetedValue = budgetedValue;
    }
    //endregion


}
