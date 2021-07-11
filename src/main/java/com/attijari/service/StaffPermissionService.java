package com.attijari.service;

import com.attijari.domain.StaffPermission;
import com.attijari.service.dto.StaffPermissionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link StaffPermission}.
 */
public interface StaffPermissionService {

    /**
     * Save a staffPermission.
     *
     * @param staffPermissionDTO the entity to save.
     * @return the persisted entity.
     */
    StaffPermissionDTO save(StaffPermissionDTO staffPermissionDTO);

    /**
     * Get all the staffPermissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StaffPermissionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" staffPermission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StaffPermissionDTO> findOne(Long id);

    /**
     * Delete the "id" staffPermission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
