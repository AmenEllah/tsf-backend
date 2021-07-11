package com.attijari.repository;

import com.attijari.domain.Derogation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Derogation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DerogationRepository extends JpaRepository<Derogation, Long>, JpaSpecificationExecutor<Derogation> {
}
