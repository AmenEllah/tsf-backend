package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ActiveStaffMapperTest {

    private ActiveStaffMapper activeStaffMapper;

    @BeforeEach
    public void setUp() {
        activeStaffMapper = new ActiveStaffMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Integer id = 1;
        assertThat(activeStaffMapper.fromId(id).getMatricule()).isEqualTo(id);
        assertThat(activeStaffMapper.fromId(null)).isNull();
    }
}
