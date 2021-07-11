package com.attijari.service;

import com.attijari.domain.Agency;
import com.attijari.service.dto.AgencyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Agency}.
 */
public interface AgencyService {

    /**
     * Save a agency.
     *
     * @param agencyDTO the entity to save.
     * @return the persisted entity.
     */
    AgencyDTO save(AgencyDTO agencyDTO);

    /**
     * Get all the agencies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AgencyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" agency.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AgencyDTO> findOne(Long id);

    /**
     * Delete the "id" agency.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
