package com.attijari.service;

import com.attijari.domain.FinancialInfo;
import com.attijari.service.dto.FinancialInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link FinancialInfo}.
 */
public interface FinancialInfoService {

    /**
     * Save a financialInfo.
     *
     * @param financialInfoDTO the entity to save.
     * @return the persisted entity.
     */
    FinancialInfoDTO save(FinancialInfoDTO financialInfoDTO);

    /**
     * Get all the financialInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FinancialInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" financialInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FinancialInfoDTO> findOne(Long id);

    /**
     * Delete the "id" financialInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
