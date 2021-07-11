package com.attijari.service.dto;

import java.util.Objects;

public class InformationFinancierDTO {

    private Long id;

    private String activityNameFR;

    private String activityNameEN;

    private String categoryNameFR;

    private String categoryNameEN;

    private String incomesFR;

    private String incomesEN;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityNameFR() {
        return activityNameFR;
    }

    public void setActivityNameFR(String activityNameFR) {
        this.activityNameFR = activityNameFR;
    }

    public String getActivityNameEN() {
        return activityNameEN;
    }

    public void setActivityNameEN(String activityNameEN) {
        this.activityNameEN = activityNameEN;
    }

    public String getCategoryNameFR() {
        return categoryNameFR;
    }

    public void setCategoryNameFR(String categoryNameFR) {
        this.categoryNameFR = categoryNameFR;
    }

    public String getCategoryNameEN() {
        return categoryNameEN;
    }

    public void setCategoryNameEN(String categoryNameEN) {
        this.categoryNameEN = categoryNameEN;
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
        if (this == o) return true;
        if (!(o instanceof InformationFinancierDTO)) return false;
        InformationFinancierDTO that = (InformationFinancierDTO) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getActivityNameFR(), that.getActivityNameFR()) &&
            Objects.equals(getActivityNameEN(), that.getActivityNameEN()) &&
            Objects.equals(getCategoryNameFR(), that.getCategoryNameFR()) &&
            Objects.equals(getCategoryNameEN(), that.getCategoryNameEN()) &&
            Objects.equals(getIncomesFR(), that.getIncomesFR()) &&
            Objects.equals(getIncomesEN(), that.getIncomesEN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getActivityNameFR(), getActivityNameEN(), getCategoryNameFR(), getCategoryNameEN(), getIncomesFR(), getIncomesEN());
    }

    @Override
    public String toString() {
        return "InformationFinancierDTO{" +
            "id=" + id +
            ", activityNameFR='" + activityNameFR + '\'' +
            ", activityNameEN='" + activityNameEN + '\'' +
            ", categoryNameFR='" + categoryNameFR + '\'' +
            ", categoryNameEN='" + categoryNameEN + '\'' +
            ", incomesFR='" + incomesFR + '\'' +
            ", incomesEN='" + incomesEN + '\'' +
            '}';
    }
}
