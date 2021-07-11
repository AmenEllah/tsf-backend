package com.attijari.service.mapper;


import com.attijari.domain.SupplyMatrix;
import com.attijari.service.dto.SupplyMatrixDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link SupplyMatrix} and its DTO {@link SupplyMatrixDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SupplyMatrixMapper extends EntityMapper<SupplyMatrixDTO, SupplyMatrix> {



    default SupplyMatrix fromId(Long id) {
        if (id == null) {
            return null;
        }
        SupplyMatrix supplyMatrix = new SupplyMatrix();
        supplyMatrix.setId(id);
        return supplyMatrix;
    }
}
