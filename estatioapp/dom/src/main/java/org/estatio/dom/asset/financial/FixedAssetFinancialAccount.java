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
package org.estatio.dom.asset.financial;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.VersionStrategy;
import com.google.common.base.Function;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Title;
import org.estatio.dom.EstatioDomainObject;
import org.estatio.dom.apptenancy.WithApplicationTenancyProperty;
import org.estatio.dom.asset.FixedAsset;
import org.estatio.dom.asset.FixedAssetRole;
import org.estatio.dom.financial.FinancialAccount;

@javax.jdo.annotations.PersistenceCapable
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.NATIVE,
        column = "id")
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByFixedAsset", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.asset.financial.FixedAssetFinancialAccount "
                        + "WHERE fixedAsset == :fixedAsset"),
        @javax.jdo.annotations.Query(
                name = "findByFixedAssetAndFinancialAccount", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.asset.financial.FixedAssetFinancialAccount "
                        + "WHERE fixedAsset == :fixedAsset "
                        + "&& financialAccount == :financialAccount"),
        @javax.jdo.annotations.Query(
                name = "findByFinancialAccount", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.asset.financial.FixedAssetFinancialAccount "
                        + "WHERE financialAccount == :financialAccount")
})
@Unique(name = "FixedAssetFinancialAccount_fixedAsset_financialAccount_IDX", members = { "fixedAsset", "financialAccount" })
public class FixedAssetFinancialAccount
        extends EstatioDomainObject<FixedAssetFinancialAccount>
        implements WithApplicationTenancyProperty {

    public FixedAssetFinancialAccount() {
        super("fixedAsset,financialAccount");
    }

    public FixedAssetFinancialAccount(FixedAsset fixedAsset, FinancialAccount financialAccount) {
        super("fixedAsset,financialAccount");
        setFinancialAccount(financialAccount);
        setFixedAsset(fixedAsset);
    }

    // //////////////////////////////////////

    @PropertyLayout(
            named = "Application Level",
            describedAs = "Determines those users for whom this object is available to view and/or modify."
    )
    public ApplicationTenancy getApplicationTenancy() {
        return getFixedAsset().getApplicationTenancy();
    }

    // //////////////////////////////////////

    private FixedAsset fixedAsset;

    @javax.jdo.annotations.Column(name = "fixedAssetId", allowsNull = "false")
    @Disabled
    @MemberOrder(sequence = "1")
    @Title(sequence = "1")
    @Named("Property")
    public FixedAsset getFixedAsset() {
        return fixedAsset;
    }

    public void setFixedAsset(final FixedAsset fixedAsset) {
        this.fixedAsset = fixedAsset;
    }

    // //////////////////////////////////////

    private FinancialAccount financialAccount;

    @javax.jdo.annotations.Column(name = "financialAccountId", allowsNull = "false")
    @Disabled
    @MemberOrder(sequence = "1")
    @Title(sequence = "2")
    @Named("Bank account")
    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(final FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
     }

    // //////////////////////////////////////

    public final static class Functions {

        private Functions() {
        }

        /**
         * A {@link com.google.common.base.Function} that obtains the {@link FixedAssetFinancialAccount#getFinancialAccount() account}.
         */
        public static <T extends FinancialAccount> Function<FixedAssetFinancialAccount, T> financialAccountOf() {
            return new Function<FixedAssetFinancialAccount, T>() {
                @SuppressWarnings("unchecked")
                public T apply(final FixedAssetFinancialAccount fixedAssetFinancialAccount) {
                    return (T) (fixedAssetFinancialAccount != null ? fixedAssetFinancialAccount.getFinancialAccount() : null);
                }
            };
        }

        /**
         * A {@link Function} that obtains the {@link FixedAssetRole#getAsset() asset}..
         */
        public static <T extends FixedAsset> Function<FixedAssetRole, T> assetOf() {
            return new Function<FixedAssetRole, T>() {
                @SuppressWarnings("unchecked")
                public T apply(final FixedAssetRole fixedAssetRole) {
                    return (T) (fixedAssetRole != null ? fixedAssetRole.getAsset() : null);
                }
            };
        }
    }



}