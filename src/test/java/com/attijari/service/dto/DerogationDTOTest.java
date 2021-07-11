package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DerogationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DerogationDTO.class);
        DerogationDTO derogationDTO1 = new DerogationDTO();
        derogationDTO1.setId(1L);
        DerogationDTO derogationDTO2 = new DerogationDTO();
        assertThat(derogationDTO1).isNotEqualTo(derogationDTO2);
        derogationDTO2.setId(derogationDTO1.getId());
        assertThat(derogationDTO1).isEqualTo(derogationDTO2);
        derogationDTO2.setId(2L);
        assertThat(derogationDTO1).isNotEqualTo(derogationDTO2);
        derogationDTO1.setId(null);
        assertThat(derogationDTO1).isNotEqualTo(derogationDTO2);
    }
}
