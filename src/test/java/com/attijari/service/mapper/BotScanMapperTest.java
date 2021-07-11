package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BotScanMapperTest {

    private BotScanMapper botScanMapper;

    @BeforeEach
    public void setUp() {
        botScanMapper = new BotScanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(botScanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(botScanMapper.fromId(null)).isNull();
    }
}
