package com.attijari.service;

import com.attijari.domain.SupplyMatrix;
import com.attijari.service.dto.SupplyMatrixDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SupplyMatrix}.
 */
public interface SupplyMatrixService {

    /**
     * Save a supplyMatrix.
     *
     * @param supplyMatrixDTO the entity to save.
     * @return the persisted entity.
     */
    SupplyMatrixDTO save(SupplyMatrixDTO supplyMatrixDTO);

    /**
     * Get all the supplyMatrices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SupplyMatrixDTO> findAll(Pageable pageable);


    /**
     * Get the "id" supplyMatrix.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SupplyMatrixDTO> findOne(Long id);

    /**
     * Delete the "id" supplyMatrix.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
