package com.attijari.service.dto;

import com.attijari.domain.FinancialInfo;

import java.io.Serializable;

/**
 * A DTO for the {@link FinancialInfo} entity.
 */
public class FinancialInfoDTO implements Serializable {

    private Long id;

    private Long activityId;

    private ActivityDTO activity;

    private Long categoryId;

    private CategoryDTO category;

    private Long monthlyNetIncomeId;

    private MonthlyNetIncomeDTO monthlyNetIncome;

    private String incomeProof;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getMonthlyNetIncomeId() {
        return monthlyNetIncomeId;
    }

    public void setMonthlyNetIncomeId(Long monthlyNetIncomeId) {
        this.monthlyNetIncomeId = monthlyNetIncomeId;
    }

    public ActivityDTO getActivity() {
        return activity;
    }

    public void setActivity(ActivityDTO activity) {
        this.activity = activity;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public MonthlyNetIncomeDTO getMonthlyNetIncome() {
        return monthlyNetIncome;
    }

    public void setMonthlyNetIncome(MonthlyNetIncomeDTO monthlyNetIncome) {
        this.monthlyNetIncome = monthlyNetIncome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinancialInfoDTO)) {
            return false;
        }

        return id != null && id.equals(((FinancialInfoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FinancialInfoDTO{" +
            "id=" + id +
            ", activityId=" + activityId +
            ", activity=" + activity +
            ", categoryId=" + categoryId +
            ", category=" + category +
            ", monthlyNetIncomeId=" + monthlyNetIncomeId +
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
