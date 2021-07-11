package com.attijari.service;

import com.attijari.domain.AppSettings;
import com.attijari.repository.AppSettingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AppSettings}.
 */
@Service
@Transactional
public class AppSettingsService {

    private final Logger log = LoggerFactory.getLogger(AppSettingsService.class);

    private final AppSettingsRepository appSettingsRepository;

    public AppSettingsService(AppSettingsRepository appSettingsRepository) {
        this.appSettingsRepository = appSettingsRepository;
    }

    /**
     * Save a appSettings.
     *
     * @param appSettings the entity to save.
     * @return the persisted entity.
     */
    public AppSettings save(AppSettings appSettings) {
        log.debug("Request to save AppSettings : {}", appSettings);
        return appSettingsRepository.save(appSettings);
    }

    /**
     * Get all the appSettings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AppSettings> findAll(Pageable pageable) {
        log.debug("Request to get all AppSettings");
        return appSettingsRepository.findAll(pageable);
    }


    /**
     * Get one appSettings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AppSettings> findOne(Long id) {
        log.debug("Request to get AppSettings : {}", id);
        return appSettingsRepository.findById(id);
    }

    /**
     * Delete the appSettings by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AppSettings : {}", id);
        appSettingsRepository.deleteById(id);
    }
}
