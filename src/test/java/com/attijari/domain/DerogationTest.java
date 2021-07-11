package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DerogationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Derogation.class);
        Derogation derogation1 = new Derogation();
        derogation1.setId(1L);
        Derogation derogation2 = new Derogation();
        derogation2.setId(derogation1.getId());
        assertThat(derogation1).isEqualTo(derogation2);
        derogation2.setId(2L);
        assertThat(derogation1).isNotEqualTo(derogation2);
        derogation1.setId(null);
        assertThat(derogation1).isNotEqualTo(derogation2);
    }
}
