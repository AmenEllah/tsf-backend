package com.attijari.service;

import com.attijari.domain.ActiveStaff;
import com.attijari.service.dto.ActiveStaffDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ActiveStaff}.
 */
public interface ActiveStaffService {

    /**
     * Save a activeStaff.
     *
     * @param activeStaffDTO the entity to save.
     * @return the persisted entity.
     */
    ActiveStaffDTO save(ActiveStaffDTO activeStaffDTO);

    /**
     * Get all the activeStaffs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActiveStaffDTO> findAll(Pageable pageable);


    /**
     * Get the "id" activeStaff.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ActiveStaffDTO> findOne(Integer id);

    /**
     * Delete the "id" activeStaff.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
