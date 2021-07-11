package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetailOfferMapperTest {

    private DetailOfferMapper detailOfferMapper;

    @BeforeEach
    public void setUp() {
        detailOfferMapper = new DetailOfferMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(detailOfferMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(detailOfferMapper.fromId(null)).isNull();
    }
}
