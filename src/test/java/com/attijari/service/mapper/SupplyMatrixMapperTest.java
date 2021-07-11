package com.attijari.service.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SupplyMatrixMapperTest {

    private SupplyMatrixMapper supplyMatrixMapper;

    @BeforeEach
    public void setUp() {
        supplyMatrixMapper = new SupplyMatrixMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        Assertions.assertThat(supplyMatrixMapper.fromId(id).getId()).isEqualTo(id);
        Assertions.assertThat(supplyMatrixMapper.fromId(null)).isNull();
    }
}
