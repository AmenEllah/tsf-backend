package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestMapperTest {

    private RequestMapper requestMapper;

    @BeforeEach
    public void setUp() {
        requestMapper = new RequestMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(requestMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(requestMapper.fromId(null)).isNull();
    }
}
