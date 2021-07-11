package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MonthlyNetIncomeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MonthlyNetIncomeDTO.class);
        MonthlyNetIncomeDTO monthlyNetIncomeDTO1 = new MonthlyNetIncomeDTO();
        monthlyNetIncomeDTO1.setId(1L);
        MonthlyNetIncomeDTO monthlyNetIncomeDTO2 = new MonthlyNetIncomeDTO();
        assertThat(monthlyNetIncomeDTO1).isNotEqualTo(monthlyNetIncomeDTO2);
        monthlyNetIncomeDTO2.setId(monthlyNetIncomeDTO1.getId());
        assertThat(monthlyNetIncomeDTO1).isEqualTo(monthlyNetIncomeDTO2);
        monthlyNetIncomeDTO2.setId(2L);
        assertThat(monthlyNetIncomeDTO1).isNotEqualTo(monthlyNetIncomeDTO2);
        monthlyNetIncomeDTO1.setId(null);
        assertThat(monthlyNetIncomeDTO1).isNotEqualTo(monthlyNetIncomeDTO2);
    }
}
