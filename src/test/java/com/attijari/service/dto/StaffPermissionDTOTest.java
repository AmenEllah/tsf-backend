package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StaffPermissionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StaffPermissionDTO.class);
        StaffPermissionDTO staffPermissionDTO1 = new StaffPermissionDTO();
        staffPermissionDTO1.setId(1L);
        StaffPermissionDTO staffPermissionDTO2 = new StaffPermissionDTO();
        assertThat(staffPermissionDTO1).isNotEqualTo(staffPermissionDTO2);
        staffPermissionDTO2.setId(staffPermissionDTO1.getId());
        assertThat(staffPermissionDTO1).isEqualTo(staffPermissionDTO2);
        staffPermissionDTO2.setId(2L);
        assertThat(staffPermissionDTO1).isNotEqualTo(staffPermissionDTO2);
        staffPermissionDTO1.setId(null);
        assertThat(staffPermissionDTO1).isNotEqualTo(staffPermissionDTO2);
    }
}
