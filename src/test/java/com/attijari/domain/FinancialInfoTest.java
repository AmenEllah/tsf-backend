package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FinancialInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancialInfo.class);
        FinancialInfo financialInfo1 = new FinancialInfo();
        financialInfo1.setId(1L);
        FinancialInfo financialInfo2 = new FinancialInfo();
        financialInfo2.setId(financialInfo1.getId());
        assertThat(financialInfo1).isEqualTo(financialInfo2);
        financialInfo2.setId(2L);
        assertThat(financialInfo1).isNotEqualTo(financialInfo2);
        financialInfo1.setId(null);
        assertThat(financialInfo1).isNotEqualTo(financialInfo2);
    }
}
