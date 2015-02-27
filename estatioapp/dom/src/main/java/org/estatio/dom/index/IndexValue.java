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
package org.estatio.dom.index;

import java.math.BigDecimal;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;
import org.joda.time.LocalDate;
import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.estatio.dom.EstatioDomainObject;
import org.estatio.dom.WithStartDate;
import org.estatio.dom.apptenancy.WithApplicationTenancyCountry;

/**
 * Holds the {@link #getValue() value} of an {@link #getIndexBase() index
 * (base)} from a particular {@link #getStartDate() point in time} (until
 * succeeded by some other value).
 */
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.NATIVE,
        column = "id")
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByIndexAndStartDate", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.index.IndexValue "
                        + "WHERE indexBase.index == :index "
                        + "   && startDate == :startDate"),
        @javax.jdo.annotations.Query(
                name = "findLastByIndex", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.index.IndexValue "
                        + "WHERE indexBase.index == :index "
                        + "ORDER BY startDate DESC")
})
@javax.jdo.annotations.Unique(
        name = "IndexValue_indexBase_startDate_IDX",
        members = { "indexBase", "startDate" })
public class IndexValue
        extends EstatioDomainObject<IndexValue>
        implements WithStartDate, WithApplicationTenancyCountry {

    public static final int VALUE_SCALE = 4;

    public IndexValue() {
        super("indexBase, startDate desc");
    }

    // //////////////////////////////////////

    @PropertyLayout(
            named = "Application Level",
            describedAs = "Determines those users for whom this object is available to view and/or modify."
    )
    public ApplicationTenancy getApplicationTenancy() {
        return getIndexBase().getApplicationTenancy();
    }


    // //////////////////////////////////////

    @javax.jdo.annotations.Persistent
    private LocalDate startDate;

    @javax.jdo.annotations.Column(allowsNull = "false")
    @Title(sequence = "2", prepend = ":")
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    // //////////////////////////////////////

    private IndexBase indexBase;

    @javax.jdo.annotations.Column(name = "indexBaseId", allowsNull = "false")
    @PropertyLayout(hidden = Where.PARENTED_TABLES)
    @Title(sequence = "1")
    public IndexBase getIndexBase() {
        return indexBase;
    }

    public void setIndexBase(final IndexBase indexBase) {
        this.indexBase = indexBase;
    }

    public void modifyIndexBase(final IndexBase indexBase) {
        IndexBase currentIndexBase = getIndexBase();
        if (indexBase == null || indexBase.equals(currentIndexBase)) {
            return;
        }
        indexBase.addToValues(this);
    }

    public void clearIndexBase() {
        IndexBase currentIndexBase = getIndexBase();
        if (currentIndexBase == null) {
            return;
        }
        currentIndexBase.removeFromValues(this);
    }

    // //////////////////////////////////////

    private BigDecimal value;

    @javax.jdo.annotations.Column(scale = VALUE_SCALE, allowsNull = "false")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(final BigDecimal value) {
        this.value = value;
    }

    // //////////////////////////////////////

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT, domainEvent = UpdateEvent.class)
    public void remove() {
        getContainer().remove(this);
    }

    // //////////////////////////////////////

    public static class UpdateEvent extends ActionDomainEvent<IndexValue> {
        private static final long serialVersionUID = 1L;

        public UpdateEvent(
                final IndexValue source,
                final Identifier identifier,
                final Object... arguments) {
            super(source, identifier, arguments);
        }

    }

}