package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MonthlyNetIncomeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MonthlyNetIncome.class);
        MonthlyNetIncome monthlyNetIncome1 = new MonthlyNetIncome();
        monthlyNetIncome1.setId(1L);
        MonthlyNetIncome monthlyNetIncome2 = new MonthlyNetIncome();
        monthlyNetIncome2.setId(monthlyNetIncome1.getId());
        assertThat(monthlyNetIncome1).isEqualTo(monthlyNetIncome2);
        monthlyNetIncome2.setId(2L);
        assertThat(monthlyNetIncome1).isNotEqualTo(monthlyNetIncome2);
        monthlyNetIncome1.setId(null);
        assertThat(monthlyNetIncome1).isNotEqualTo(monthlyNetIncome2);
    }
}
