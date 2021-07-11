package com.attijari.service.dto;

import com.attijari.domain.FinancialInfo;
import com.attijari.web.rest.FinancialInfoResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link FinancialInfo} entity. This class is used
 * in {@link FinancialInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /financial-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FinancialInfoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter activityId;

    private LongFilter categoryId;

    private LongFilter personalInfoId;

    private LongFilter monthlyNetIncomeId;

    private StringFilter incomeProof;

    public FinancialInfoCriteria() {
    }

    public FinancialInfoCriteria(FinancialInfoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.activityId = other.activityId == null ? null : other.activityId.copy();
        this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
        this.personalInfoId = other.personalInfoId == null ? null : other.personalInfoId.copy();
        this.monthlyNetIncomeId = other.monthlyNetIncomeId == null ? null : other.monthlyNetIncomeId.copy();
        this.incomeProof = other.incomeProof == null ? null : other.incomeProof.copy();
    }

    @Override
    public FinancialInfoCriteria copy() {
        return new FinancialInfoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getActivityId() {
        return activityId;
    }

    public void setActivityId(LongFilter activityId) {
        this.activityId = activityId;
    }

    public LongFilter getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(LongFilter categoryId) {
        this.categoryId = categoryId;
    }

    public LongFilter getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(LongFilter personalInfoId) {
        this.personalInfoId = personalInfoId;
    }

    public LongFilter getMonthlyNetIncomeId() {
        return monthlyNetIncomeId;
    }

    public void setMonthlyNetIncomeId(LongFilter monthlyNetIncomeId) {
        this.monthlyNetIncomeId = monthlyNetIncomeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FinancialInfoCriteria that = (FinancialInfoCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(activityId, that.activityId) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(personalInfoId, that.personalInfoId) &&
                Objects.equals(incomeProof, that.incomeProof) &&
                Objects.equals(monthlyNetIncomeId, that.monthlyNetIncomeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            activityId,
            categoryId,
            personalInfoId,
            monthlyNetIncomeId,
            incomeProof
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FinancialInfoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (activityId != null ? "activityId=" + activityId + ", " : "") +
            (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
            (personalInfoId != null ? "personalInfoId=" + personalInfoId + ", " : "") +
            (monthlyNetIncomeId != null ? "monthlyNetIncomeId=" + monthlyNetIncomeId + ", " : "") +
            (incomeProof != null ? "incomeProof=" + incomeProof + ", " : "") +
            "}";
    }

    public StringFilter getIncomeProof() {
        return incomeProof;
    }

    public void setIncomeProof(StringFilter incomeProof) {
        this.incomeProof = incomeProof;
    }
}
