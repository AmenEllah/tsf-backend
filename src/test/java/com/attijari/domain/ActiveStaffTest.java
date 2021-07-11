package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ActiveStaffTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActiveStaff.class);
        ActiveStaff activeStaff1 = new ActiveStaff();
        activeStaff1.setMatricule(1);
        ActiveStaff activeStaff2 = new ActiveStaff();
        activeStaff2.setMatricule(activeStaff1.getMatricule());
        assertThat(activeStaff1).isEqualTo(activeStaff2);
        activeStaff2.setMatricule(2);
        assertThat(activeStaff1).isNotEqualTo(activeStaff2);
        activeStaff1.setMatricule(null);
        assertThat(activeStaff1).isNotEqualTo(activeStaff2);
    }
}
