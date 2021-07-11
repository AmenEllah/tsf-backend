package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SubscriberStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubscriberStatusDTO.class);
        SubscriberStatusDTO subscriberStatusDTO1 = new SubscriberStatusDTO();
        subscriberStatusDTO1.setId(1L);
        SubscriberStatusDTO subscriberStatusDTO2 = new SubscriberStatusDTO();
        assertThat(subscriberStatusDTO1).isNotEqualTo(subscriberStatusDTO2);
        subscriberStatusDTO2.setId(subscriberStatusDTO1.getId());
        assertThat(subscriberStatusDTO1).isEqualTo(subscriberStatusDTO2);
        subscriberStatusDTO2.setId(2L);
        assertThat(subscriberStatusDTO1).isNotEqualTo(subscriberStatusDTO2);
        subscriberStatusDTO1.setId(null);
        assertThat(subscriberStatusDTO1).isNotEqualTo(subscriberStatusDTO2);
    }
}
