package com.attijari.domain;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BotScanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BotScan.class);
        BotScan botScan1 = new BotScan();
        botScan1.setId(1L);
        BotScan botScan2 = new BotScan();
        botScan2.setId(botScan1.getId());
        assertThat(botScan1).isEqualTo(botScan2);
        botScan2.setId(2L);
        assertThat(botScan1).isNotEqualTo(botScan2);
        botScan1.setId(null);
        assertThat(botScan1).isNotEqualTo(botScan2);
    }
}
