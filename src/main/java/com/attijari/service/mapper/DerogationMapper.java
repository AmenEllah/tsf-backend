package com.attijari.service.mapper;


import com.attijari.domain.Derogation;
import com.attijari.service.dto.DerogationDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Derogation} and its DTO {@link DerogationDTO}.
 */
@Mapper(componentModel = "spring", uses = {RequestMapper.class})
public interface DerogationMapper extends EntityMapper<DerogationDTO, Derogation> {

    @Mapping(source = "request.id", target = "requestId")
    DerogationDTO toDto(Derogation derogation);

    @Mapping(source = "requestId", target = "request")
    Derogation toEntity(DerogationDTO derogationDTO);

    default Derogation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Derogation derogation = new Derogation();
        derogation.setId(id);
        return derogation;
    }
}
