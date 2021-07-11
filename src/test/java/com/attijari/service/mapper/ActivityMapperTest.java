package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ActivityMapperTest {

    private ActivityMapper activityMapper;

    @BeforeEach
    public void setUp() {
        activityMapper = new ActivityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(activityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(activityMapper.fromId(null)).isNull();
    }
}
