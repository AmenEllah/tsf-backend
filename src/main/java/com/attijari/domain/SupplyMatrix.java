package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A SupplyMatrix.
 */
@Entity
@Table(name = "supply_matrix")
public class SupplyMatrix implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "income_type_code")
    private String incomeTypeCode;

    @Column(name = "income_type")
    private String incomeType;

    @Column(name = "monthly_income_id")
    private Long monthlyIncomeId;

    @Column(name = "market_code")
    private Long marketCode;

    @Column(name = "market")
    private String market;

    @Column(name = "segment_code")
    private String segmentCode;

    @Column(name = "jhi_segment")
    private String segment;

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "profession_code")
    private String professionCode;

    @Column(name = "profession")
    private String profession;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public SupplyMatrix categoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getIncomeTypeCode() {
        return incomeTypeCode;
    }

    public SupplyMatrix incomeTypeCode(String incomeTypeCode) {
        this.incomeTypeCode = incomeTypeCode;
        return this;
    }

    public void setIncomeTypeCode(String incomeTypeCode) {
        this.incomeTypeCode = incomeTypeCode;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public SupplyMatrix incomeType(String incomeType) {
        this.incomeType = incomeType;
        return this;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public Long getMonthlyIncomeId() {
        return monthlyIncomeId;
    }

    public SupplyMatrix monthlyIncomeId(Long monthlyIncomeId) {
        this.monthlyIncomeId = monthlyIncomeId;
        return this;
    }

    public void setMonthlyIncomeId(Long monthlyIncomeId) {
        this.monthlyIncomeId = monthlyIncomeId;
    }

    public Long getMarketCode() {
        return marketCode;
    }

    public SupplyMatrix marketCode(Long marketCode) {
        this.marketCode = marketCode;
        return this;
    }

    public void setMarketCode(Long marketCode) {
        this.marketCode = marketCode;
    }

    public String getMarket() {
        return market;
    }

    public SupplyMatrix market(String market) {
        this.market = market;
        return this;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public SupplyMatrix segmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
        return this;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getSegment() {
        return segment;
    }

    public SupplyMatrix segment(String segment) {
        this.segment = segment;
        return this;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Long getActivityId() {
        return activityId;
    }

    public SupplyMatrix activityId(Long activityId) {
        this.activityId = activityId;
        return this;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public SupplyMatrix professionCode(String professionCode) {
        this.professionCode = professionCode;
        return this;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getProfession() {
        return profession;
    }

    public SupplyMatrix profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SupplyMatrix)) {
            return false;
        }
        return id != null && id.equals(((SupplyMatrix) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SupplyMatrix{" +
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
