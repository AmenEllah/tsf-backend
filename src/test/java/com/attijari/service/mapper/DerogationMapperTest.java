package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DerogationMapperTest {

    private DerogationMapper derogationMapper;

    @BeforeEach
    public void setUp() {
        derogationMapper = new DerogationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(derogationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(derogationMapper.fromId(null)).isNull();
    }
}
