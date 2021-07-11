package com.attijari.repository;

import com.attijari.domain.SubcontractingStaff;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SubcontractingStaff entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubcontractingStaffRepository extends JpaRepository<SubcontractingStaff, Long>, JpaSpecificationExecutor<SubcontractingStaff> {
}
