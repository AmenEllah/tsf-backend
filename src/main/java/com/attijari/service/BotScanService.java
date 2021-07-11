package com.attijari.service;

import com.attijari.domain.BotScan;
import com.attijari.service.dto.BotScanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link BotScan}.
 */
public interface BotScanService {

    /**
     * Save a botScan.
     *
     * @param botScanDTO the entity to save.
     * @return the persisted entity.
     */
    BotScanDTO save(BotScanDTO botScanDTO);

    /**
     * Get all the botScans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BotScanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" botScan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BotScanDTO> findOne(Long id);

    /**
     * Delete the "id" botScan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    String getAgencyCode(String codeAgence);
}
