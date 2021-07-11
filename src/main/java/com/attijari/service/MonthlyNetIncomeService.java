package com.attijari.service;

import com.attijari.domain.MonthlyNetIncome;
import com.attijari.service.dto.MonthlyNetIncomeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link MonthlyNetIncome}.
 */
public interface MonthlyNetIncomeService {

    /**
     * Save a monthlyNetIncome.
     *
     * @param monthlyNetIncomeDTO the entity to save.
     * @return the persisted entity.
     */
    MonthlyNetIncomeDTO save(MonthlyNetIncomeDTO monthlyNetIncomeDTO);

    /**
     * Get all the monthlyNetIncomes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MonthlyNetIncomeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" monthlyNetIncome.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MonthlyNetIncomeDTO> findOne(Long id);

    /**
     * Delete the "id" monthlyNetIncome.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
