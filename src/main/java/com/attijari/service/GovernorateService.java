package com.attijari.service;

import com.attijari.domain.Governorate;
import com.attijari.service.dto.GovernorateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Governorate}.
 */
public interface GovernorateService {

    /**
     * Save a governorate.
     *
     * @param governorateDTO the entity to save.
     * @return the persisted entity.
     */
    GovernorateDTO save(GovernorateDTO governorateDTO);

    /**
     * Get all the governorates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GovernorateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" governorate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GovernorateDTO> findOne(Long id);

    /**
     * Delete the "id" governorate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
