package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FAQTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FAQ.class);
        FAQ fAQ1 = new FAQ();
        fAQ1.setId(1L);
        FAQ fAQ2 = new FAQ();
        fAQ2.setId(fAQ1.getId());
        assertThat(fAQ1).isEqualTo(fAQ2);
        fAQ2.setId(2L);
        assertThat(fAQ1).isNotEqualTo(fAQ2);
        fAQ1.setId(null);
        assertThat(fAQ1).isNotEqualTo(fAQ2);
    }
}
