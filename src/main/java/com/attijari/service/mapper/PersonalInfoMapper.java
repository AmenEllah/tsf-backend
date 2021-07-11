package com.attijari.service.mapper;


import com.attijari.domain.PersonalInfo;
import com.attijari.service.dto.PersonalInfoDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link PersonalInfo} and its DTO {@link PersonalInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {AgencyMapper.class, AdressInfoMapper.class, FinancialInfoMapper.class, CountryMapper.class, NationalityMapper.class})
public interface PersonalInfoMapper extends EntityMapper<PersonalInfoDTO, PersonalInfo> {

    @Mapping(source = "agency.id", target = "agencyId")
    PersonalInfoDTO toDto(PersonalInfo personalInfo);

    PersonalInfo toEntity(PersonalInfoDTO personalInfoDTO);

    default PersonalInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(id);
        return personalInfo;
    }
}
