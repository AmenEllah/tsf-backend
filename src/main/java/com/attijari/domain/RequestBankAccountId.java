package com.attijari.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RequestBankAccountId
    implements Serializable {

    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "bank_account_id")
    private Long bankAccountId;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestBankAccountId that = (RequestBankAccountId) o;
        return Objects.equals(requestId, that.requestId) &&
            Objects.equals(bankAccountId, that.bankAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, bankAccountId);
    }

    @Override
    public String toString() {
        return "RequestBankAccountId{" +
            "requestId=" + requestId +
            ", bankAccountId=" + bankAccountId +
            '}';
    }
}
