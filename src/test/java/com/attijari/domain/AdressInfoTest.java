package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdressInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdressInfo.class);
        AdressInfo adressInfo1 = new AdressInfo();
        adressInfo1.setId(1L);
        AdressInfo adressInfo2 = new AdressInfo();
        adressInfo2.setId(adressInfo1.getId());
        assertThat(adressInfo1).isEqualTo(adressInfo2);
        adressInfo2.setId(2L);
        assertThat(adressInfo1).isNotEqualTo(adressInfo2);
        adressInfo1.setId(null);
        assertThat(adressInfo1).isNotEqualTo(adressInfo2);
    }
}
