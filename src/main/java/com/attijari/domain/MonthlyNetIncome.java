package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A MonthlyNetIncome.
 */
@Entity
@Table(name = "monthly_income")
public class MonthlyNetIncome implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "incomes_fr")
    private String incomesFR;

    @Column(name = "incomes_en")
    private String incomesEN;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncomesFR() {
        return incomesFR;
    }

    public MonthlyNetIncome incomesFR(String incomesFR) {
        this.incomesFR = incomesFR;
        return this;
    }

    public void setIncomesFR(String incomesFR) {
        this.incomesFR = incomesFR;
    }

    public String getIncomesEN() {
        return incomesEN;
    }

    public MonthlyNetIncome incomesEN(String incomesEN) {
        this.incomesEN = incomesEN;
        return this;
    }

    public void setIncomesEN(String incomesEN) {
        this.incomesEN = incomesEN;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyNetIncome)) {
            return false;
        }
        return id != null && id.equals(((MonthlyNetIncome) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyNetIncome{" +
            "id=" + getId() +
            ", incomesFR='" + getIncomesFR() + "'" +
            ", incomesEN='" + getIncomesEN() + "'" +
            "}";
    }
}
