package com.attijari.service;

import com.attijari.domain.Derogation;
import com.attijari.service.dto.DerogationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Derogation}.
 */
public interface DerogationService {

    /**
     * Save a derogation.
     *
     * @param derogationDTO the entity to save.
     * @return the persisted entity.
     */
    DerogationDTO save(DerogationDTO derogationDTO);

    /**
     * Get all the derogations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DerogationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" derogation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DerogationDTO> findOne(Long id);

    /**
     * Delete the "id" derogation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
