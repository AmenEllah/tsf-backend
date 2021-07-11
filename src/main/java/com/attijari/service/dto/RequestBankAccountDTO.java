package com.attijari.service.dto;


import com.attijari.domain.RequestBankAccountId;


public class RequestBankAccountDTO {

    private RequestBankAccountId id;

    private String accountNumber ;

    private String currency ;

    private BankAccountDTO bankAccount;

    private Boolean oppositionStatus ;

    private String rib;

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

    public BankAccountDTO getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountDTO bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Boolean getOppositionStatus() {
        return oppositionStatus;
    }

    public void setOppositionStatus(Boolean oppositionStatus) {
        this.oppositionStatus = oppositionStatus;
    }

    @Override
    public String toString() {
        return "RequestBankAccountDTO{" +
            "id=" + id +
            ", accountNumber='" + accountNumber + '\'' +
            ", currency='" + currency + '\'' +
            ", bankAccount=" + bankAccount +
            ", oppositionStatus=" + oppositionStatus +
            '}';
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }
}
