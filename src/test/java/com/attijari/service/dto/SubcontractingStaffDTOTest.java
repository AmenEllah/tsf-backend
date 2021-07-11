package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SubcontractingStaffDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubcontractingStaffDTO.class);
        SubcontractingStaffDTO subcontractingStaffDTO1 = new SubcontractingStaffDTO();
        subcontractingStaffDTO1.setId(1L);
        SubcontractingStaffDTO subcontractingStaffDTO2 = new SubcontractingStaffDTO();
        assertThat(subcontractingStaffDTO1).isNotEqualTo(subcontractingStaffDTO2);
        subcontractingStaffDTO2.setId(subcontractingStaffDTO1.getId());
        assertThat(subcontractingStaffDTO1).isEqualTo(subcontractingStaffDTO2);
        subcontractingStaffDTO2.setId(2L);
        assertThat(subcontractingStaffDTO1).isNotEqualTo(subcontractingStaffDTO2);
        subcontractingStaffDTO1.setId(null);
        assertThat(subcontractingStaffDTO1).isNotEqualTo(subcontractingStaffDTO2);
    }
}
