package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FinancialInfoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancialInfoDTO.class);
        FinancialInfoDTO financialInfoDTO1 = new FinancialInfoDTO();
        financialInfoDTO1.setId(1L);
        FinancialInfoDTO financialInfoDTO2 = new FinancialInfoDTO();
        assertThat(financialInfoDTO1).isNotEqualTo(financialInfoDTO2);
        financialInfoDTO2.setId(financialInfoDTO1.getId());
        assertThat(financialInfoDTO1).isEqualTo(financialInfoDTO2);
        financialInfoDTO2.setId(2L);
        assertThat(financialInfoDTO1).isNotEqualTo(financialInfoDTO2);
        financialInfoDTO1.setId(null);
        assertThat(financialInfoDTO1).isNotEqualTo(financialInfoDTO2);
    }
}
