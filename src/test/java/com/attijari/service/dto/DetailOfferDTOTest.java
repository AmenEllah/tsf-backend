package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetailOfferDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailOfferDTO.class);
        DetailOfferDTO detailOfferDTO1 = new DetailOfferDTO();
        detailOfferDTO1.setId(1L);
        DetailOfferDTO detailOfferDTO2 = new DetailOfferDTO();
        assertThat(detailOfferDTO1).isNotEqualTo(detailOfferDTO2);
        detailOfferDTO2.setId(detailOfferDTO1.getId());
        assertThat(detailOfferDTO1).isEqualTo(detailOfferDTO2);
        detailOfferDTO2.setId(2L);
        assertThat(detailOfferDTO1).isNotEqualTo(detailOfferDTO2);
        detailOfferDTO1.setId(null);
        assertThat(detailOfferDTO1).isNotEqualTo(detailOfferDTO2);
    }
}
