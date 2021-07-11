package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SupplyMatrixDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplyMatrixDTO.class);
        SupplyMatrixDTO supplyMatrixDTO1 = new SupplyMatrixDTO();
        supplyMatrixDTO1.setId(1L);
        SupplyMatrixDTO supplyMatrixDTO2 = new SupplyMatrixDTO();
        assertThat(supplyMatrixDTO1).isNotEqualTo(supplyMatrixDTO2);
        supplyMatrixDTO2.setId(supplyMatrixDTO1.getId());
        assertThat(supplyMatrixDTO1).isEqualTo(supplyMatrixDTO2);
        supplyMatrixDTO2.setId(2L);
        assertThat(supplyMatrixDTO1).isNotEqualTo(supplyMatrixDTO2);
        supplyMatrixDTO1.setId(null);
        assertThat(supplyMatrixDTO1).isNotEqualTo(supplyMatrixDTO2);
    }
}
