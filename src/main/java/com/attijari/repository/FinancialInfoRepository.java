package com.attijari.repository;

import com.attijari.domain.FinancialInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FinancialInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinancialInfoRepository extends JpaRepository<FinancialInfo, Long>, JpaSpecificationExecutor<FinancialInfo> {
}
