package com.attijari.service.dto;

import com.attijari.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankAccountDTO.class);
        BankAccountDTO bankAccountDTO1 = new BankAccountDTO();
        bankAccountDTO1.setId(1L);
        BankAccountDTO bankAccountDTO2 = new BankAccountDTO();
        assertThat(bankAccountDTO1).isNotEqualTo(bankAccountDTO2);
        bankAccountDTO2.setId(bankAccountDTO1.getId());
        assertThat(bankAccountDTO1).isEqualTo(bankAccountDTO2);
        bankAccountDTO2.setId(2L);
        assertThat(bankAccountDTO1).isNotEqualTo(bankAccountDTO2);
        bankAccountDTO1.setId(null);
        assertThat(bankAccountDTO1).isNotEqualTo(bankAccountDTO2);
    }
}
