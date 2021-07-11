package com.attijari.service.mapper;


import com.attijari.domain.ActiveStaff;
import com.attijari.service.dto.ActiveStaffDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link ActiveStaff} and its DTO {@link ActiveStaffDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActiveStaffMapper extends EntityMapper<ActiveStaffDTO, ActiveStaff> {



    default ActiveStaff fromId(Integer matricule) {
        if (matricule == null) {
            return null;
        }
        ActiveStaff activeStaff = new ActiveStaff();
        activeStaff.setMatricule(matricule);
        return activeStaff;
    }
}
