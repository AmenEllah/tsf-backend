package com.attijari.web.rest;

import com.attijari.domain.AppSettings;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.AppSettingsService;
import com.attijari.service.dto.AppSettingsCriteria;
import com.attijari.service.AppSettingsQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link AppSettings}.
 */
@RestController
@RequestMapping("/api")
public class AppSettingsResource {

    private final Logger log = LoggerFactory.getLogger(AppSettingsResource.class);

    private static final String ENTITY_NAME = "appSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppSettingsService appSettingsService;

    private final AppSettingsQueryService appSettingsQueryService;

    public AppSettingsResource(AppSettingsService appSettingsService, AppSettingsQueryService appSettingsQueryService) {
        this.appSettingsService = appSettingsService;
        this.appSettingsQueryService = appSettingsQueryService;
    }

    /**
     * {@code POST  /app-settings} : Create a new appSettings.
     *
     * @param appSettings the appSettings to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appSettings, or with status {@code 400 (Bad Request)} if the appSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/app-settings")
    public ResponseEntity<AppSettings> createAppSettings(@Valid @RequestBody AppSettings appSettings) throws URISyntaxException {
        log.debug("REST request to save AppSettings : {}", appSettings);
        if (appSettings.getId() != null) {
            throw new BadRequestAlertException("A new appSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppSettings result = appSettingsService.save(appSettings);
        return ResponseEntity.created(new URI("/api/app-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /app-settings} : Updates an existing appSettings.
     *
     * @param appSettings the appSettings to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appSettings,
     * or with status {@code 400 (Bad Request)} if the appSettings is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appSettings couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/app-settings")
    public ResponseEntity<AppSettings> updateAppSettings(@Valid @RequestBody AppSettings appSettings) throws URISyntaxException {
        log.debug("REST request to update AppSettings : {}", appSettings);
        if (appSettings.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppSettings result = appSettingsService.save(appSettings);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appSettings.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /app-settings} : get all the appSettings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appSettings in body.
     */
    @GetMapping("/app-settings")
    public ResponseEntity<List<AppSettings>> getAllAppSettings(AppSettingsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AppSettings by criteria: {}", criteria);
        Page<AppSettings> page = appSettingsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /app-settings/count} : count all the appSettings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/app-settings/count")
    public ResponseEntity<Long> countAppSettings(AppSettingsCriteria criteria) {
        log.debug("REST request to count AppSettings by criteria: {}", criteria);
        return ResponseEntity.ok().body(appSettingsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /app-settings/:id} : get the "id" appSettings.
     *
     * @param id the id of the appSettings to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appSettings, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/app-settings/{id}")
    public ResponseEntity<AppSettings> getAppSettings(@PathVariable Long id) {
        log.debug("REST request to get AppSettings : {}", id);
        Optional<AppSettings> appSettings = appSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appSettings);
    }

    /**
     * {@code DELETE  /app-settings/:id} : delete the "id" appSettings.
     *
     * @param id the id of the appSettings to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/app-settings/{id}")
    public ResponseEntity<Void> deleteAppSettings(@PathVariable Long id) {
        log.debug("REST request to delete AppSettings : {}", id);
        appSettingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
