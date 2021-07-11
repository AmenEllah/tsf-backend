package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GovernorateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GovernorateDTO.class);
        GovernorateDTO governorateDTO1 = new GovernorateDTO();
        governorateDTO1.setId(1L);
        GovernorateDTO governorateDTO2 = new GovernorateDTO();
        assertThat(governorateDTO1).isNotEqualTo(governorateDTO2);
        governorateDTO2.setId(governorateDTO1.getId());
        assertThat(governorateDTO1).isEqualTo(governorateDTO2);
        governorateDTO2.setId(2L);
        assertThat(governorateDTO1).isNotEqualTo(governorateDTO2);
        governorateDTO1.setId(null);
        assertThat(governorateDTO1).isNotEqualTo(governorateDTO2);
    }
}
