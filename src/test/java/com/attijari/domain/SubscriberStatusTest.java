package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SubscriberStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubscriberStatus.class);
        SubscriberStatus subscriberStatus1 = new SubscriberStatus();
        subscriberStatus1.setId(1L);
        SubscriberStatus subscriberStatus2 = new SubscriberStatus();
        subscriberStatus2.setId(subscriberStatus1.getId());
        assertThat(subscriberStatus1).isEqualTo(subscriberStatus2);
        subscriberStatus2.setId(2L);
        assertThat(subscriberStatus1).isNotEqualTo(subscriberStatus2);
        subscriberStatus1.setId(null);
        assertThat(subscriberStatus1).isNotEqualTo(subscriberStatus2);
    }
}
