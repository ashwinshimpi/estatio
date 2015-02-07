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
package org.estatio.dom.lease;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Title;
import org.estatio.dom.EstatioDomainObject;
import org.estatio.dom.JdoColumnLength;
import org.estatio.dom.WithNameUnique;
import org.estatio.dom.WithReferenceComparable;
import org.estatio.dom.apptenancy.ApplicationTenancyInvariantsService;
import org.estatio.dom.apptenancy.WithApplicationTenancyGlobal;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.NATIVE,
        column = "id")
@javax.jdo.annotations.Uniques({
        @javax.jdo.annotations.Unique(
                name = "LeaseType_reference_UNQ", members = "reference"),
        @javax.jdo.annotations.Unique(
                name = "LeaseType_name_UNQ", members = "name")
})
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByReference", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.lease.LeaseType "
                        + "WHERE reference == :reference")
})
@DomainObject(editing = Editing.DISABLED, bounded = true)
public class LeaseType
        extends EstatioDomainObject<LeaseType>
        implements WithReferenceComparable<LeaseType>, WithNameUnique, WithApplicationTenancyGlobal {

    public LeaseType() {
        super("reference");
    }

    // //////////////////////////////////////

    @Hidden
    public ApplicationTenancy getApplicationTenancy() {
        return applicationTenancies.findTenancyByPath(ApplicationTenancyInvariantsService.GLOBAL_APPLICATION_TENANCY_PATH);
    }

    // //////////////////////////////////////

    private String reference;

    @javax.jdo.annotations.Column(allowsNull = "false", length = JdoColumnLength.REFERENCE)
    @MemberOrder(sequence = "1")
    public String getReference() {
        return reference;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }

    // //////////////////////////////////////

    private String name;

    @javax.jdo.annotations.Column(allowsNull = "false", length = JdoColumnLength.TITLE)
    @Title()
    @MemberOrder(sequence = "2")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    // //////////////////////////////////////

    private String description;

    @javax.jdo.annotations.Column(allowsNull = "true", length = JdoColumnLength.DESCRIPTION)
    @Property(optionality = Optionality.OPTIONAL)
    @PropertyLayout(multiLine = 3)
    @MemberOrder(sequence = "1")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
