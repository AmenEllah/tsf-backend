package com.attijari.service.mapper;


import com.attijari.domain.Nationality;

import com.attijari.service.dto.NationalityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Nationality} and its DTO {@link NationalityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NationalityMapper extends EntityMapper<NationalityDTO, Nationality> {



    default Nationality fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nationality nationality = new Nationality();
        nationality.setId(id);
        return nationality;
    }
}
