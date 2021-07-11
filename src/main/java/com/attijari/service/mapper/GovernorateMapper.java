package com.attijari.service.mapper;


import com.attijari.domain.Governorate;
import com.attijari.service.dto.GovernorateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Governorate} and its DTO {@link GovernorateDTO}.
 */
@Mapper(componentModel = "spring", uses = {MunicipalityMapper.class})
public interface GovernorateMapper extends EntityMapper<GovernorateDTO, Governorate> {


    @Mapping(target = "municipalities", ignore = true)
    Governorate toEntity(GovernorateDTO governorateDTO);

    default Governorate fromId(Long id) {
        if (id == null) {
            return null;
        }
        Governorate governorate = new Governorate();
        governorate.setId(id);
        return governorate;
    }
}
