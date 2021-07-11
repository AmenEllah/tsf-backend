package com.attijari.web.rest;

import com.attijari.domain.Governorate;
import com.attijari.service.GovernorateQueryService;
import com.attijari.service.dto.GovernorateCriteria;
import com.attijari.security.AuthoritiesConstants;
import com.attijari.service.GovernorateService;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.dto.GovernorateDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Governorate}.
 */
@RestController
@RequestMapping("/api")
public class GovernorateResource {

    private final Logger log = LoggerFactory.getLogger(GovernorateResource.class);

    private static final String ENTITY_NAME = "governorate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GovernorateService governorateService;

    private final GovernorateQueryService governorateQueryService;

    public GovernorateResource(GovernorateService governorateService, GovernorateQueryService governorateQueryService) {
        this.governorateService = governorateService;
        this.governorateQueryService = governorateQueryService;
    }

    /**
     * {@code POST  /governorates} : Create a new governorate.
     *
     * @param governorateDTO the governorateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new governorateDTO, or with status {@code 400 (Bad Request)} if the governorate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/governorates")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<GovernorateDTO> createGovernorate(@RequestBody GovernorateDTO governorateDTO) throws URISyntaxException {
        log.debug("REST request to save Governorate : {}", governorateDTO);
        if (governorateDTO.getId() != null) {
            throw new BadRequestAlertException("A new governorate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GovernorateDTO result = governorateService.save(governorateDTO);
        return ResponseEntity.created(new URI("/api/governorates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /governorates} : Updates an existing governorate.
     *
     * @param governorateDTO the governorateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated governorateDTO,
     * or with status {@code 400 (Bad Request)} if the governorateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the governorateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/governorates")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<GovernorateDTO> updateGovernorate(@RequestBody GovernorateDTO governorateDTO) throws URISyntaxException {
        log.debug("REST request to update Governorate : {}", governorateDTO);
        if (governorateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GovernorateDTO result = governorateService.save(governorateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, governorateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /governorates} : get all the governorates.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of governorates in body.
     */
    @GetMapping("/governorates")
    public ResponseEntity<List<GovernorateDTO>> getAllGovernorates(GovernorateCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Governorates by criteria: {}", criteria);
        Page<GovernorateDTO> page = governorateQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /governorates/count} : count all the governorates.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/governorates/count")
    public ResponseEntity<Long> countGovernorates(GovernorateCriteria criteria) {
        log.debug("REST request to count Governorates by criteria: {}", criteria);
        return ResponseEntity.ok().body(governorateQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /governorates/:id} : get the "id" governorate.
     *
     * @param id the id of the governorateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the governorateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/governorates/{id}")
    public ResponseEntity<GovernorateDTO> getGovernorate(@PathVariable Long id) {
        log.debug("REST request to get Governorate : {}", id);
        Optional<GovernorateDTO> governorateDTO = governorateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(governorateDTO);
    }

    /**
     * {@code DELETE  /governorates/:id} : delete the "id" governorate.
     *
     * @param id the id of the governorateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/governorates/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteGovernorate(@PathVariable Long id) {
        log.debug("REST request to delete Governorate : {}", id);
        governorateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
