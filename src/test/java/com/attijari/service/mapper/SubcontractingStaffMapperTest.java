package com.attijari.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SubcontractingStaffMapperTest {

    private SubcontractingStaffMapper subcontractingStaffMapper;

    @BeforeEach
    public void setUp() {
        subcontractingStaffMapper = new SubcontractingStaffMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(subcontractingStaffMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(subcontractingStaffMapper.fromId(null)).isNull();
    }
}
