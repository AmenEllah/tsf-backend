package com.attijari.repository;

import com.attijari.domain.BotScan;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BotScan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BotScanRepository extends JpaRepository<BotScan, Long>, JpaSpecificationExecutor<BotScan> {
}
