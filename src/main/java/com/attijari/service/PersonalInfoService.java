package com.attijari.service;

import com.attijari.domain.PersonalInfo;
import com.attijari.service.dto.PersonalInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link PersonalInfo}.
 */
public interface PersonalInfoService {

    /**
     * Save a personalInfo.
     *
     * @param personalInfoDTO the entity to save.
     * @return the persisted entity.
     */
    PersonalInfoDTO save(PersonalInfoDTO personalInfoDTO);

    /**
     * Get all the personalInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PersonalInfoDTO> findAll(Pageable pageable);
    /**
     * Get the "id" personalInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersonalInfoDTO> findOne(Long id);

    /**
     * Delete the "id" personalInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
