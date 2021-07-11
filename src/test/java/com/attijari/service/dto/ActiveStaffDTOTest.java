package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ActiveStaffDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActiveStaffDTO.class);
        ActiveStaffDTO activeStaffDTO1 = new ActiveStaffDTO();
        activeStaffDTO1.setMatricule(1);
        ActiveStaffDTO activeStaffDTO2 = new ActiveStaffDTO();
        assertThat(activeStaffDTO1).isNotEqualTo(activeStaffDTO2);
        activeStaffDTO2.setMatricule(activeStaffDTO1.getMatricule());
        assertThat(activeStaffDTO1).isEqualTo(activeStaffDTO2);
        activeStaffDTO2.setMatricule(2);
        assertThat(activeStaffDTO1).isNotEqualTo(activeStaffDTO2);
        activeStaffDTO1.setMatricule(null);
        assertThat(activeStaffDTO1).isNotEqualTo(activeStaffDTO2);
    }
}
