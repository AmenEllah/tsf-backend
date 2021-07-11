package com.attijari.service;

import com.attijari.domain.DetailOffer;
import com.attijari.service.dto.DetailOfferDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DetailOffer}.
 */
public interface DetailOfferService {

    /**
     * Save a detailOffer.
     *
     * @param detailOfferDTO the entity to save.
     * @return the persisted entity.
     */
    DetailOfferDTO save(DetailOfferDTO detailOfferDTO);

    /**
     * Get all the detailOffers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetailOfferDTO> findAll(Pageable pageable);


    /**
     * Get the "id" detailOffer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailOfferDTO> findOne(Long id);

    /**
     * Delete the "id" detailOffer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
