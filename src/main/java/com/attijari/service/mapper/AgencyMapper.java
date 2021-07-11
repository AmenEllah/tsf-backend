package com.attijari.service.mapper;


import com.attijari.domain.Agency;
import com.attijari.service.dto.AgencyDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Agency} and its DTO {@link AgencyDTO}.
 */
@Mapper(componentModel = "spring", uses = {MunicipalityMapper.class})
public interface AgencyMapper extends EntityMapper<AgencyDTO, Agency> {

    @Mapping(source = "municipality.id", target = "municipalityId")
    AgencyDTO toDto(Agency agency);

    @Mapping(source = "municipalityId", target = "municipality")
    Agency toEntity(AgencyDTO agencyDTO);

    default Agency fromId(Long id) {
        if (id == null) {
            return null;
        }
        Agency agency = new Agency();
        agency.setId(id);
        return agency;
    }
}
