package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GovernorateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Governorate.class);
        Governorate governorate1 = new Governorate();
        governorate1.setId(1L);
        Governorate governorate2 = new Governorate();
        governorate2.setId(governorate1.getId());
        assertThat(governorate1).isEqualTo(governorate2);
        governorate2.setId(2L);
        assertThat(governorate1).isNotEqualTo(governorate2);
        governorate1.setId(null);
        assertThat(governorate1).isNotEqualTo(governorate2);
    }
}
