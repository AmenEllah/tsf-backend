package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MunicipalityMapperTest {

    private MunicipalityMapper municipalityMapper;

    @BeforeEach
    public void setUp() {
        municipalityMapper = new MunicipalityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(municipalityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(municipalityMapper.fromId(null)).isNull();
    }
}
