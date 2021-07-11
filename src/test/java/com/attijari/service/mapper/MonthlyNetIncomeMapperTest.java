package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MonthlyNetIncomeMapperTest {

    private MonthlyNetIncomeMapper monthlyNetIncomeMapper;

    @BeforeEach
    public void setUp() {
        monthlyNetIncomeMapper = new MonthlyNetIncomeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(monthlyNetIncomeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(monthlyNetIncomeMapper.fromId(null)).isNull();
    }
}
