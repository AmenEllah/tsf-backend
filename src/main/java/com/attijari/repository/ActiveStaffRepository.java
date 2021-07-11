package com.attijari.repository;

import com.attijari.domain.ActiveStaff;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ActiveStaff entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActiveStaffRepository extends JpaRepository<ActiveStaff, Integer>, JpaSpecificationExecutor<ActiveStaff> {
}
