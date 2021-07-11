package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BotScanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BotScanDTO.class);
        BotScanDTO botScanDTO1 = new BotScanDTO();
        botScanDTO1.setId(1L);
        BotScanDTO botScanDTO2 = new BotScanDTO();
        assertThat(botScanDTO1).isNotEqualTo(botScanDTO2);
        botScanDTO2.setId(botScanDTO1.getId());
        assertThat(botScanDTO1).isEqualTo(botScanDTO2);
        botScanDTO2.setId(2L);
        assertThat(botScanDTO1).isNotEqualTo(botScanDTO2);
        botScanDTO1.setId(null);
        assertThat(botScanDTO1).isNotEqualTo(botScanDTO2);
    }
}
