package com.attijari.service.mapper;


import com.attijari.domain.Request;
import com.attijari.service.dto.RequestDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Request} and its DTO {@link RequestDTO}.
 */
@Mapper(componentModel = "spring", uses = {OfferMapper.class, PersonalInfoMapper.class, DocumentMapper.class, RequestBankAccountMapper.class, DerogationMapper.class, AttachmentMapper.class, UserMapper.class,SubscriberStatusMapper.class})
public interface RequestMapper extends EntityMapper<RequestDTO, Request> {

    @Mapping(source = "requestBankAccounts", target = "requestBankAccounts")
    @Mapping(source = "offer.id", target = "offerId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userAccountLogin")
    @Mapping(source = "personalInfo.agency.code", target = "agencyCode")
    RequestDTO toDto(Request request);

    @Mapping(source = "offerId", target = "offer")
    @Mapping(source = "requestBankAccounts", target = "requestBankAccounts")
    @Mapping(source = "derogations", target = "derogations")
    @Mapping(source = "attachments", target = "attachments")
    @Mapping(source = "userId", target = "user")
    Request toEntity(RequestDTO requestDTO);

    default Request fromId(Long id) {
        if (id == null) {
            return null;
        }
        Request request = new Request();
        request.setId(id);
        return request;
    }
}
