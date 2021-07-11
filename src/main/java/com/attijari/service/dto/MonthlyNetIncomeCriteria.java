package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.MonthlyNetIncome;
import com.attijari.web.rest.MonthlyNetIncomeResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link MonthlyNetIncome} entity. This class is used
 * in {@link MonthlyNetIncomeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /monthly-net-incomes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MonthlyNetIncomeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter incomesFR;

    private StringFilter incomesEN;

    public MonthlyNetIncomeCriteria() {
    }

    public MonthlyNetIncomeCriteria(MonthlyNetIncomeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.incomesFR = other.incomesFR == null ? null : other.incomesFR.copy();
        this.incomesEN = other.incomesEN == null ? null : other.incomesEN.copy();
    }

    @Override
    public MonthlyNetIncomeCriteria copy() {
        return new MonthlyNetIncomeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getIncomesFR() {
        return incomesFR;
    }

    public void setIncomesFR(StringFilter incomesFR) {
        this.incomesFR = incomesFR;
    }

    public StringFilter getIncomesEN() {
        return incomesEN;
    }

    public void setIncomesEN(StringFilter incomesEN) {
        this.incomesEN = incomesEN;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MonthlyNetIncomeCriteria that = (MonthlyNetIncomeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(incomesFR, that.incomesFR) &&
            Objects.equals(incomesEN, that.incomesEN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        incomesFR,
        incomesEN
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyNetIncomeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (incomesFR != null ? "incomesFR=" + incomesFR + ", " : "") +
                (incomesEN != null ? "incomesEN=" + incomesEN + ", " : "") +
            "}";
    }

}
