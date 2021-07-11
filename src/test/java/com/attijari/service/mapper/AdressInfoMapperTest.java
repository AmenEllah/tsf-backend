package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdressInfoMapperTest {

    private AdressInfoMapper adressInfoMapper;

    @BeforeEach
    public void setUp() {
        adressInfoMapper = new AdressInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adressInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adressInfoMapper.fromId(null)).isNull();
    }
}
