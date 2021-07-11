package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdressInfoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdressInfoDTO.class);
        AdressInfoDTO adressInfoDTO1 = new AdressInfoDTO();
        adressInfoDTO1.setId(1L);
        AdressInfoDTO adressInfoDTO2 = new AdressInfoDTO();
        assertThat(adressInfoDTO1).isNotEqualTo(adressInfoDTO2);
        adressInfoDTO2.setId(adressInfoDTO1.getId());
        assertThat(adressInfoDTO1).isEqualTo(adressInfoDTO2);
        adressInfoDTO2.setId(2L);
        assertThat(adressInfoDTO1).isNotEqualTo(adressInfoDTO2);
        adressInfoDTO1.setId(null);
        assertThat(adressInfoDTO1).isNotEqualTo(adressInfoDTO2);
    }
}
