package com.attijari.service;

import com.attijari.domain.Nationality;
import com.attijari.service.dto.NationalityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Nationality}.
 */
public interface NationalityService {

    /**
     * Save a nationality.
     *
     * @param nationalityDTO the entity to save.
     * @return the persisted entity.
     */
    NationalityDTO save(NationalityDTO nationalityDTO);

    /**
     * Get all the nationalities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NationalityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nationality.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NationalityDTO> findOne(Long id);

    /**
     * Delete the "id" nationality.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
