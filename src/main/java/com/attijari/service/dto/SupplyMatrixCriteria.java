package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.SupplyMatrix;
import com.attijari.web.rest.SupplyMatrixResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SupplyMatrix} entity. This class is used
 * in {@link SupplyMatrixResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /supply-matrices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SupplyMatrixCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter categoryId;

    private StringFilter incomeTypeCode;

    private StringFilter incomeType;

    private LongFilter monthlyIncomeId;

    private LongFilter marketCode;

    private StringFilter market;

    private StringFilter segmentCode;

    private StringFilter segment;

    private LongFilter activityId;

    private StringFilter professionCode;

    private StringFilter profession;

    public SupplyMatrixCriteria() {
    }

    public SupplyMatrixCriteria(SupplyMatrixCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
        this.incomeTypeCode = other.incomeTypeCode == null ? null : other.incomeTypeCode.copy();
        this.incomeType = other.incomeType == null ? null : other.incomeType.copy();
        this.monthlyIncomeId = other.monthlyIncomeId == null ? null : other.monthlyIncomeId.copy();
        this.marketCode = other.marketCode == null ? null : other.marketCode.copy();
        this.market = other.market == null ? null : other.market.copy();
        this.segmentCode = other.segmentCode == null ? null : other.segmentCode.copy();
        this.segment = other.segment == null ? null : other.segment.copy();
        this.activityId = other.activityId == null ? null : other.activityId.copy();
        this.professionCode = other.professionCode == null ? null : other.professionCode.copy();
        this.profession = other.profession == null ? null : other.profession.copy();
    }

    @Override
    public SupplyMatrixCriteria copy() {
        return new SupplyMatrixCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(LongFilter categoryId) {
        this.categoryId = categoryId;
    }

    public StringFilter getIncomeTypeCode() {
        return incomeTypeCode;
    }

    public void setIncomeTypeCode(StringFilter incomeTypeCode) {
        this.incomeTypeCode = incomeTypeCode;
    }

    public StringFilter getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(StringFilter incomeType) {
        this.incomeType = incomeType;
    }

    public LongFilter getMonthlyIncomeId() {
        return monthlyIncomeId;
    }

    public void setMonthlyIncomeId(LongFilter monthlyIncomeId) {
        this.monthlyIncomeId = monthlyIncomeId;
    }

    public LongFilter getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(LongFilter marketCode) {
        this.marketCode = marketCode;
    }

    public StringFilter getMarket() {
        return market;
    }

    public void setMarket(StringFilter market) {
        this.market = market;
    }

    public StringFilter getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(StringFilter segmentCode) {
        this.segmentCode = segmentCode;
    }

    public StringFilter getSegment() {
        return segment;
    }

    public void setSegment(StringFilter segment) {
        this.segment = segment;
    }

    public LongFilter getActivityId() {
        return activityId;
    }

    public void setActivityId(LongFilter activityId) {
        this.activityId = activityId;
    }

    public StringFilter getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(StringFilter professionCode) {
        this.professionCode = professionCode;
    }

    public StringFilter getProfession() {
        return profession;
    }

    public void setProfession(StringFilter profession) {
        this.profession = profession;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SupplyMatrixCriteria that = (SupplyMatrixCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(categoryId, that.categoryId) &&
            Objects.equals(incomeTypeCode, that.incomeTypeCode) &&
            Objects.equals(incomeType, that.incomeType) &&
            Objects.equals(monthlyIncomeId, that.monthlyIncomeId) &&
            Objects.equals(marketCode, that.marketCode) &&
            Objects.equals(market, that.market) &&
            Objects.equals(segmentCode, that.segmentCode) &&
            Objects.equals(segment, that.segment) &&
            Objects.equals(activityId, that.activityId) &&
            Objects.equals(professionCode, that.professionCode) &&
            Objects.equals(profession, that.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        categoryId,
        incomeTypeCode,
        incomeType,
        monthlyIncomeId,
        marketCode,
        market,
        segmentCode,
        segment,
        activityId,
        professionCode,
        profession
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SupplyMatrixCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
                (incomeTypeCode != null ? "incomeTypeCode=" + incomeTypeCode + ", " : "") +
                (incomeType != null ? "incomeType=" + incomeType + ", " : "") +
                (monthlyIncomeId != null ? "monthlyIncomeId=" + monthlyIncomeId + ", " : "") +
                (marketCode != null ? "marketCode=" + marketCode + ", " : "") +
                (market != null ? "market=" + market + ", " : "") +
                (segmentCode != null ? "segmentCode=" + segmentCode + ", " : "") +
                (segment != null ? "segment=" + segment + ", " : "") +
                (activityId != null ? "activityId=" + activityId + ", " : "") +
                (professionCode != null ? "professionCode=" + professionCode + ", " : "") +
                (profession != null ? "profession=" + profession + ", " : "") +
            "}";
    }

}
