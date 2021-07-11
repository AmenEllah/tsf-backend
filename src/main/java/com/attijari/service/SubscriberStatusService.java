package com.attijari.service;

import com.attijari.domain.SubscriberStatus;
import com.attijari.service.dto.SubscriberStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SubscriberStatus}.
 */
public interface SubscriberStatusService {

    /**
     * Save a subscriberStatus.
     *
     * @param subscriberStatusDTO the entity to save.
     * @return the persisted entity.
     */
    SubscriberStatusDTO save(SubscriberStatusDTO subscriberStatusDTO);

    /**
     * Get all the subscriberStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SubscriberStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" subscriberStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubscriberStatusDTO> findOne(Long id);

    /**
     * Delete the "id" subscriberStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
