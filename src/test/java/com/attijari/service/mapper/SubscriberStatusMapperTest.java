package com.attijari.service.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SubscriberStatusMapperTest {

    private SubscriberStatusMapper subscriberStatusMapper;

    @BeforeEach
    public void setUp() {
        subscriberStatusMapper = new SubscriberStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        Assertions.assertThat(subscriberStatusMapper.fromId(id).getId()).isEqualTo(id);
        Assertions.assertThat(subscriberStatusMapper.fromId(null)).isNull();
    }
}
