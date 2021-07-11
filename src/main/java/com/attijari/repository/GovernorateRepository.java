package com.attijari.repository;

import com.attijari.domain.Governorate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * Spring Data  repository for the Governorate entity.
 */
@Repository
public interface GovernorateRepository extends JpaRepository<Governorate, Long>, JpaSpecificationExecutor<Governorate> {
    @Query(value = "select distinct gov from Governorate gov left join gov.municipalities m left join m.agencies",
        countQuery = "select count(distinct gov) from Governorate gov")
    List<Governorate> findAllWithEagerRelationships();

}
