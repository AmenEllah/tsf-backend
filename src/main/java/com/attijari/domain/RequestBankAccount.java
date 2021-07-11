package com.attijari.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "request_bank_account")
@Table(name = "request_bank_account")
public class RequestBankAccount {

    @EmbeddedId
    private RequestBankAccountId id;

    @Column(name = "account_number")
    private String accountNumber ;

    @Column(name = "currency")
    private String currency ;

    @Column(name = "opposition_status")
    private Boolean oppositionStatus;

    @Column(name = "rib")
    private String rib;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("requestId")
    private Request request;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("bankAccountId")
    private BankAccount bankAccount;

    public RequestBankAccountId getId() {
        return id;
    }

    public void setId(RequestBankAccountId id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Boolean getOppositionStatus() {
        return oppositionStatus;
    }

    public void setOppositionStatus(Boolean oppositionStatus) {
        this.oppositionStatus = oppositionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestBankAccount that = (RequestBankAccount) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(accountNumber, that.accountNumber) &&
            Objects.equals(currency, that.currency) &&
            Objects.equals(request, that.request) &&
            Objects.equals(oppositionStatus, that.oppositionStatus) &&
            Objects.equals(bankAccount, that.bankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, currency, request, bankAccount, oppositionStatus);
    }

    @Override
    public String toString() {
        return "RequestBankAccount{" +
            "id=" + id +
            ", accountNumber='" + accountNumber + '\'' +
            ", currency='" + currency + '\'' +
            ", oppositionStatus=" + oppositionStatus +
            ", request=" + request +
            ", bankAccount=" + bankAccount +
            '}';
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }
}
