package com.attijari.service.mapper;


import com.attijari.domain.SubcontractingStaff;
import com.attijari.service.dto.SubcontractingStaffDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link SubcontractingStaff} and its DTO {@link SubcontractingStaffDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubcontractingStaffMapper extends EntityMapper<SubcontractingStaffDTO, SubcontractingStaff> {



    default SubcontractingStaff fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubcontractingStaff subcontractingStaff = new SubcontractingStaff();
        subcontractingStaff.setId(id);
        return subcontractingStaff;
    }
}
