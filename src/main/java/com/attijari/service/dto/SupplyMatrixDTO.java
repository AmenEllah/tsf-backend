package com.attijari.service.dto;

import com.attijari.domain.SupplyMatrix;

import java.io.Serializable;

/**
 * A DTO for the {@link SupplyMatrix} entity.
 */
public class SupplyMatrixDTO implements Serializable {

    private Long id;

    private Long categoryId;

    private String incomeTypeCode;

    private String incomeType;

    private Long monthlyIncomeId;

    private Long marketCode;

    private String market;

    private String segmentCode;

    private String segment;

    private Long activityId;

    private String professionCode;

    private String profession;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getIncomeTypeCode() {
        return incomeTypeCode;
    }

    public void setIncomeTypeCode(String incomeTypeCode) {
        this.incomeTypeCode = incomeTypeCode;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public Long getMonthlyIncomeId() {
        return monthlyIncomeId;
    }

    public void setMonthlyIncomeId(Long monthlyIncomeId) {
        this.monthlyIncomeId = monthlyIncomeId;
    }

    public Long getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(Long marketCode) {
        this.marketCode = marketCode;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SupplyMatrixDTO)) {
            return false;
        }

        return id != null && id.equals(((SupplyMatrixDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SupplyMatrixDTO{" +
            "id=" + getId() +
            ", categoryId=" + getCategoryId() +
            ", incomeTypeCode='" + getIncomeTypeCode() + "'" +
            ", incomeType='" + getIncomeType() + "'" +
            ", monthlyIncomeId=" + getMonthlyIncomeId() +
            ", marketCode=" + getMarketCode() +
            ", market='" + getMarket() + "'" +
            ", segmentCode='" + getSegmentCode() + "'" +
            ", segment='" + getSegment() + "'" +
            ", activityId=" + getActivityId() +
            ", professionCode='" + getProfessionCode() + "'" +
            ", profession='" + getProfession() + "'" +
            "}";
    }
}
