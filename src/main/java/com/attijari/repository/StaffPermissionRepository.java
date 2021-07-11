package com.attijari.repository;

import com.attijari.domain.StaffPermission;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StaffPermission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StaffPermissionRepository extends JpaRepository<StaffPermission, Long>, JpaSpecificationExecutor<StaffPermission> {
}
