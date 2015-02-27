package org.estatio.dom.financial;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Index;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;
import org.joda.time.LocalDate;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Where;
import org.estatio.dom.EstatioDomainObject;
import org.estatio.dom.JdoColumnLength;
import org.estatio.dom.JdoColumnScale;
import org.estatio.dom.apptenancy.WithApplicationTenancyCountry;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@Queries({
        @Query(
                name = "findByFinancialAccount",
                language = "JDOQL",
                value = "SELECT FROM org.estatio.dom.financial.FinancialAccountTransaction "
                        + "WHERE financialAccount == :financialAccount"),
        @Query(
                name = "findByFinancialAccountAndTransactionDate",
                language = "JDOQL",
                value = "SELECT FROM org.estatio.dom.financial.FinancialAccountTransaction "
                        + "WHERE financialAccount == :financialAccount && "
                        + "transactionDate == :transactionDate")
})
@Index(
        name = "FinancialAccountTransaction_financialAccount_transactionDate_IDX",
        members = { "financialAccount", "transactionDate" })
public class FinancialAccountTransaction
        extends EstatioDomainObject<FinancialAccountTransaction>
        implements WithApplicationTenancyCountry {

    public FinancialAccountTransaction() {
        super("financialAccount,transactionDate,description,amount");
    }

    // //////////////////////////////////////

    @PropertyLayout(
            named = "Application Level",
            describedAs = "Determines those users for whom this object is available to view and/or modify."
    )
    public ApplicationTenancy getApplicationTenancy() {
        return getFinancialAccount().getApplicationTenancy();
    }

    // //////////////////////////////////////

    FinancialAccount financialAccount;

    @Column(name = "financialAccountId")
    @MemberOrder(sequence = "1")
    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }

    // //////////////////////////////////////

    LocalDate transactionDate;

    @Column(allowsNull = "false")
    @MemberOrder(sequence = "2")
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(final LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    // //////////////////////////////////////

    String description;

    @Column(allowsNull = "true", length = JdoColumnLength.DESCRIPTION)
    @MemberOrder(sequence = "4")
    @Hidden(where = Where.ALL_TABLES)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // //////////////////////////////////////

    BigDecimal amount;

    @Column(allowsNull = "false", scale = JdoColumnScale.MONEY)
    @MemberOrder(sequence = "5")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
