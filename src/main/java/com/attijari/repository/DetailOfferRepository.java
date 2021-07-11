package com.attijari.repository;

import com.attijari.domain.DetailOffer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DetailOffer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailOfferRepository extends JpaRepository<DetailOffer, Long>, JpaSpecificationExecutor<DetailOffer> {
}
