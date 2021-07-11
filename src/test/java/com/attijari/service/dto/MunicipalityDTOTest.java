package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MunicipalityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MunicipalityDTO.class);
        MunicipalityDTO municipalityDTO1 = new MunicipalityDTO();
        municipalityDTO1.setId(1L);
        MunicipalityDTO municipalityDTO2 = new MunicipalityDTO();
        assertThat(municipalityDTO1).isNotEqualTo(municipalityDTO2);
        municipalityDTO2.setId(municipalityDTO1.getId());
        assertThat(municipalityDTO1).isEqualTo(municipalityDTO2);
        municipalityDTO2.setId(2L);
        assertThat(municipalityDTO1).isNotEqualTo(municipalityDTO2);
        municipalityDTO1.setId(null);
        assertThat(municipalityDTO1).isNotEqualTo(municipalityDTO2);
    }
}
