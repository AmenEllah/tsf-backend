package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FAQMapperTest {

    private FAQMapper fAQMapper;

    @BeforeEach
    public void setUp() {
        fAQMapper = new FAQMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fAQMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fAQMapper.fromId(null)).isNull();
    }
}
