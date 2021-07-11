package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NationalityMapperTest {

    private NationalityMapper nationalityMapper;

    @BeforeEach
    public void setUp() {
        nationalityMapper = new NationalityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(nationalityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nationalityMapper.fromId(null)).isNull();
    }
}
