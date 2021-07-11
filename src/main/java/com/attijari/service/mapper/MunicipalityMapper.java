package com.attijari.service.mapper;


import com.attijari.domain.Municipality;
import com.attijari.service.dto.MunicipalityDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Municipality} and its DTO {@link MunicipalityDTO}.
 */
@Mapper(componentModel = "spring", uses = {GovernorateMapper.class, AgencyMapper.class})
public interface MunicipalityMapper extends EntityMapper<MunicipalityDTO, Municipality> {

    @Mapping(source = "governorate.id", target = "governorateId")
    MunicipalityDTO toDto(Municipality municipality);

    @Mapping(source = "governorateId", target = "governorate")
    Municipality toEntity(MunicipalityDTO municipalityDTO);

    default Municipality fromId(Long id) {
        if (id == null) {
            return null;
        }
        Municipality municipality = new Municipality();
        municipality.setId(id);
        return municipality;
    }
}
