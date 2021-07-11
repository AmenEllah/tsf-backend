package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StaffPermissionMapperTest {

    private StaffPermissionMapper staffPermissionMapper;

    @BeforeEach
    public void setUp() {
        staffPermissionMapper = new StaffPermissionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(staffPermissionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(staffPermissionMapper.fromId(null)).isNull();
    }
}
