package com.attijari.service;

import com.attijari.domain.FAQ;
import com.attijari.service.dto.FAQDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link FAQ}.
 */
public interface FAQService {

    /**
     * Save a fAQ.
     *
     * @param fAQDTO the entity to save.
     * @return the persisted entity.
     */
    FAQDTO save(FAQDTO fAQDTO);

    /**
     * Get all the fAQS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FAQDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fAQ.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FAQDTO> findOne(Long id);

    /**
     * Delete the "id" fAQ.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
