package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SubcontractingStaffTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubcontractingStaff.class);
        SubcontractingStaff subcontractingStaff1 = new SubcontractingStaff();
        subcontractingStaff1.setId(1L);
        SubcontractingStaff subcontractingStaff2 = new SubcontractingStaff();
        subcontractingStaff2.setId(subcontractingStaff1.getId());
        assertThat(subcontractingStaff1).isEqualTo(subcontractingStaff2);
        subcontractingStaff2.setId(2L);
        assertThat(subcontractingStaff1).isNotEqualTo(subcontractingStaff2);
        subcontractingStaff1.setId(null);
        assertThat(subcontractingStaff1).isNotEqualTo(subcontractingStaff2);
    }
}
