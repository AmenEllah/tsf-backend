package com.attijari.repository;

import com.attijari.domain.AppSettings;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AppSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppSettingsRepository extends JpaRepository<AppSettings, Long>, JpaSpecificationExecutor<AppSettings> {
}
