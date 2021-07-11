package com.attijari.service;

import com.attijari.domain.AdressInfo;
import com.attijari.service.dto.AdressInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link AdressInfo}.
 */
public interface AdressInfoService {

    /**
     * Save a adressInfo.
     *
     * @param adressInfoDTO the entity to save.
     * @return the persisted entity.
     */
    AdressInfoDTO save(AdressInfoDTO adressInfoDTO);

    /**
     * Get all the adressInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdressInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" adressInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdressInfoDTO> findOne(Long id);

    /**
     * Delete the "id" adressInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
