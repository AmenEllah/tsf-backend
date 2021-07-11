package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonalInfoMapperTest {

    private PersonalInfoMapper personalInfoMapper;

    @BeforeEach
    public void setUp() {
        personalInfoMapper = new PersonalInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(personalInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(personalInfoMapper.fromId(null)).isNull();
    }
}
