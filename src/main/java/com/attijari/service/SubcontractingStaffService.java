package com.attijari.service;

import com.attijari.domain.SubcontractingStaff;
import com.attijari.service.dto.SubcontractingStaffDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SubcontractingStaff}.
 */
public interface SubcontractingStaffService {

    /**
     * Save a subcontractingStaff.
     *
     * @param subcontractingStaffDTO the entity to save.
     * @return the persisted entity.
     */
    SubcontractingStaffDTO save(SubcontractingStaffDTO subcontractingStaffDTO);

    /**
     * Get all the subcontractingStaffs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SubcontractingStaffDTO> findAll(Pageable pageable);


    /**
     * Get the "id" subcontractingStaff.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubcontractingStaffDTO> findOne(Long id);

    /**
     * Delete the "id" subcontractingStaff.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
