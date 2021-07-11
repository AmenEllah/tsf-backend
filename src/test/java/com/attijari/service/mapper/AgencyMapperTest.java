package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AgencyMapperTest {

    private AgencyMapper agencyMapper;

    @BeforeEach
    public void setUp() {
        agencyMapper = new AgencyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(agencyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(agencyMapper.fromId(null)).isNull();
    }
}
