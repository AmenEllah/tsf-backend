package com.attijari.service.mapper;


import com.attijari.domain.AdressInfo;
import com.attijari.service.dto.AdressInfoDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdressInfo} and its DTO {@link AdressInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface AdressInfoMapper extends EntityMapper<AdressInfoDTO, AdressInfo> {

    AdressInfoDTO toDto(AdressInfo adressInfo);

    AdressInfo toEntity(AdressInfoDTO adressInfoDTO);

    default AdressInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdressInfo adressInfo = new AdressInfo();
        adressInfo.setId(id);
        return adressInfo;
    }
}
