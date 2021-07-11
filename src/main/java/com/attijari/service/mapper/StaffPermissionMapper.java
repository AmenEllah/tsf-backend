package com.attijari.service.mapper;


import com.attijari.domain.StaffPermission;
import com.attijari.service.dto.StaffPermissionDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link StaffPermission} and its DTO {@link StaffPermissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StaffPermissionMapper extends EntityMapper<StaffPermissionDTO, StaffPermission> {



    default StaffPermission fromId(Long id) {
        if (id == null) {
            return null;
        }
        StaffPermission staffPermission = new StaffPermission();
        staffPermission.setId(id);
        return staffPermission;
    }
}
