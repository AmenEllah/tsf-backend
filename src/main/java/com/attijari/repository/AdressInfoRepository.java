package com.attijari.repository;

import com.attijari.domain.AdressInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdressInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdressInfoRepository extends JpaRepository<AdressInfo, Long>, JpaSpecificationExecutor<AdressInfo> {
}
