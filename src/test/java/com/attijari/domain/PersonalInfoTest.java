package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonalInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalInfo.class);
        PersonalInfo personalInfo1 = new PersonalInfo();
        personalInfo1.setId(1L);
        PersonalInfo personalInfo2 = new PersonalInfo();
        personalInfo2.setId(personalInfo1.getId());
        assertThat(personalInfo1).isEqualTo(personalInfo2);
        personalInfo2.setId(2L);
        assertThat(personalInfo1).isNotEqualTo(personalInfo2);
        personalInfo1.setId(null);
        assertThat(personalInfo1).isNotEqualTo(personalInfo2);
    }
}
