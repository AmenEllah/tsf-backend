package com.attijari.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A FinancialInfo.
 */
@Entity
@Table(name = "financial_info")
public class FinancialInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "income_proof")
    private String incomeProof;

    @ManyToOne(fetch = FetchType.EAGER)
    private Activity activity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    private MonthlyNetIncome monthlyNetIncome;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public FinancialInfo activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Category getCategory() {
        return category;
    }

    public FinancialInfo category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MonthlyNetIncome getMonthlyNetIncome() {
        return monthlyNetIncome;
    }

    public FinancialInfo monthlyNetIncome(MonthlyNetIncome monthlyNetIncome) {
        this.monthlyNetIncome = monthlyNetIncome;
        return this;
    }

    public void setMonthlyNetIncome(MonthlyNetIncome monthlyNetIncome) {
        this.monthlyNetIncome = monthlyNetIncome;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinancialInfo)) {
            return false;
        }
        return id != null && id.equals(((FinancialInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FinancialInfo{" +
            "id=" + id +
            ", activity=" + activity +
            ", category=" + category +
            ", monthlyNetIncome=" + monthlyNetIncome +
            '}';
    }

    public String getIncomeProof() {
        return incomeProof;
    }

    public void setIncomeProof(String incomeProof) {
        this.incomeProof = incomeProof;
    }
}
