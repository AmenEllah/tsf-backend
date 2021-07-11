package com.attijari.repository;

import com.attijari.domain.FAQ;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FAQ entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long>, JpaSpecificationExecutor<FAQ> {
}
