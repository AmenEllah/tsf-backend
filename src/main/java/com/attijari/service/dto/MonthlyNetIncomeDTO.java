package com.attijari.service.dto;

import com.attijari.domain.MonthlyNetIncome;

import java.io.Serializable;

/**
 * A DTO for the {@link MonthlyNetIncome} entity.
 */
public class MonthlyNetIncomeDTO implements Serializable {

    private Long id;

    private String incomesFR;

    private String incomesEN;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncomesFR() {
        return incomesFR;
    }

    public void setIncomesFR(String incomesFR) {
        this.incomesFR = incomesFR;
    }

    public String getIncomesEN() {
        return incomesEN;
    }

    public void setIncomesEN(String incomesEN) {
        this.incomesEN = incomesEN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyNetIncomeDTO)) {
            return false;
        }

        return id != null && id.equals(((MonthlyNetIncomeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyNetIncomeDTO{" +
            "id=" + getId() +
            ", incomesFR='" + getIncomesFR() + "'" +
            ", incomesEN='" + getIncomesEN() + "'" +
            "}";
    }
}
