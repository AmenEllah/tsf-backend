package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StaffPermissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StaffPermission.class);
        StaffPermission staffPermission1 = new StaffPermission();
        staffPermission1.setId(1L);
        StaffPermission staffPermission2 = new StaffPermission();
        staffPermission2.setId(staffPermission1.getId());
        assertThat(staffPermission1).isEqualTo(staffPermission2);
        staffPermission2.setId(2L);
        assertThat(staffPermission1).isNotEqualTo(staffPermission2);
        staffPermission1.setId(null);
        assertThat(staffPermission1).isNotEqualTo(staffPermission2);
    }
}
