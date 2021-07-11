package com.attijari.service;

import com.attijari.domain.Municipality;
import com.attijari.service.dto.MunicipalityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Municipality}.
 */
public interface MunicipalityService {

    /**
     * Save a municipality.
     *
     * @param municipalityDTO the entity to save.
     * @return the persisted entity.
     */
    MunicipalityDTO save(MunicipalityDTO municipalityDTO);

    /**
     * Get all the municipalities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MunicipalityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" municipality.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MunicipalityDTO> findOne(Long id);

    /**
     * Delete the "id" municipality.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
