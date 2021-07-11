package com.attijari.repository;

import com.attijari.domain.SubscriberStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SubscriberStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubscriberStatusRepository extends JpaRepository<SubscriberStatus, Long>, JpaSpecificationExecutor<SubscriberStatus> {
}
