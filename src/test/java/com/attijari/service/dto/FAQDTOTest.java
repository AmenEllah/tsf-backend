package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FAQDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FAQDTO.class);
        FAQDTO fAQDTO1 = new FAQDTO();
        fAQDTO1.setId(1L);
        FAQDTO fAQDTO2 = new FAQDTO();
        assertThat(fAQDTO1).isNotEqualTo(fAQDTO2);
        fAQDTO2.setId(fAQDTO1.getId());
        assertThat(fAQDTO1).isEqualTo(fAQDTO2);
        fAQDTO2.setId(2L);
        assertThat(fAQDTO1).isNotEqualTo(fAQDTO2);
        fAQDTO1.setId(null);
        assertThat(fAQDTO1).isNotEqualTo(fAQDTO2);
    }
}
