package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FinancialInfoMapperTest {

    private FinancialInfoMapper financialInfoMapper;

    @BeforeEach
    public void setUp() {
        financialInfoMapper = new FinancialInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(financialInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(financialInfoMapper.fromId(null)).isNull();
    }
}
