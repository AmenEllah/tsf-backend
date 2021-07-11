package com.attijari.repository;

import com.attijari.domain.SupplyMatrix;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SupplyMatrix entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupplyMatrixRepository extends JpaRepository<SupplyMatrix, Long>, JpaSpecificationExecutor<SupplyMatrix> {
}
