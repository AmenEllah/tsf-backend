package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetailOfferTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailOffer.class);
        DetailOffer detailOffer1 = new DetailOffer();
        detailOffer1.setId(1L);
        DetailOffer detailOffer2 = new DetailOffer();
        detailOffer2.setId(detailOffer1.getId());
        assertThat(detailOffer1).isEqualTo(detailOffer2);
        detailOffer2.setId(2L);
        assertThat(detailOffer1).isNotEqualTo(detailOffer2);
        detailOffer1.setId(null);
        assertThat(detailOffer1).isNotEqualTo(detailOffer2);
    }
}
