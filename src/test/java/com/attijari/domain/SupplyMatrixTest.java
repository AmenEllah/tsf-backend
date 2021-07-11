package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SupplyMatrixTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplyMatrix.class);
        SupplyMatrix supplyMatrix1 = new SupplyMatrix();
        supplyMatrix1.setId(1L);
        SupplyMatrix supplyMatrix2 = new SupplyMatrix();
        supplyMatrix2.setId(supplyMatrix1.getId());
        assertThat(supplyMatrix1).isEqualTo(supplyMatrix2);
        supplyMatrix2.setId(2L);
        assertThat(supplyMatrix1).isNotEqualTo(supplyMatrix2);
        supplyMatrix1.setId(null);
        assertThat(supplyMatrix1).isNotEqualTo(supplyMatrix2);
    }
}
