package com.attijari.service.mapper;

import com.attijari.domain.RequestBankAccount;
import com.attijari.domain.RequestBankAccountId;
import com.attijari.service.dto.RequestBankAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link RequestBankAccount} and its DTO {@link RequestBankAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {RequestMapper.class, BankAccountMapper.class})
public interface RequestBankAccountMapper extends EntityMapper<RequestBankAccountDTO, RequestBankAccount> {

    @Mapping(source = "id.requestId", target = "request")
    @Mapping(source = "id.bankAccountId", target = "bankAccount")
    RequestBankAccount toEntity(RequestBankAccountDTO requestBankAccountDTO);

    @Mapping(source = "bankAccount", target = "bankAccount")
    RequestBankAccountDTO toDto(RequestBankAccount requestBankAccount);

    default RequestBankAccount fromId(RequestBankAccountId id) {
        if (id == null) {
            return null;
        }
        RequestBankAccount requestBankAccount = new RequestBankAccount();
        requestBankAccount.setId(id);
        return requestBankAccount;
    }
}
