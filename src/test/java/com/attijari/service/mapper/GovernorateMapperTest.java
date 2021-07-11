package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GovernorateMapperTest {

    private GovernorateMapper governorateMapper;

    @BeforeEach
    public void setUp() {
        governorateMapper = new GovernorateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(governorateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(governorateMapper.fromId(null)).isNull();
    }
}
