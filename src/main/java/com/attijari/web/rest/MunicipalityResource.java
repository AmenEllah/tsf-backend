package com.attijari.web.rest;

import com.attijari.domain.Municipality;
import com.attijari.service.MunicipalityQueryService;
import com.attijari.service.dto.MunicipalityCriteria;
import com.attijari.security.AuthoritiesConstants;
import com.attijari.service.MunicipalityService;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.dto.MunicipalityDTO;

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
 * REST controller for managing {@link Municipality}.
 */
@RestController
@RequestMapping("/api")
public class MunicipalityResource {

    private final Logger log = LoggerFactory.getLogger(MunicipalityResource.class);

    private static final String ENTITY_NAME = "municipality";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MunicipalityService municipalityService;

    private final MunicipalityQueryService municipalityQueryService;

    public MunicipalityResource(MunicipalityService municipalityService, MunicipalityQueryService municipalityQueryService) {
        this.municipalityService = municipalityService;
        this.municipalityQueryService = municipalityQueryService;
    }

    /**
     * {@code POST  /municipalities} : Create a new municipality.
     *
     * @param municipalityDTO the municipalityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new municipalityDTO, or with status {@code 400 (Bad Request)} if the municipality has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/municipalities")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<MunicipalityDTO> createMunicipality(@RequestBody MunicipalityDTO municipalityDTO) throws URISyntaxException {
        log.debug("REST request to save Municipality : {}", municipalityDTO);
        if (municipalityDTO.getId() != null) {
            throw new BadRequestAlertException("A new municipality cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MunicipalityDTO result = municipalityService.save(municipalityDTO);
        return ResponseEntity.created(new URI("/api/municipalities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /municipalities} : Updates an existing municipality.
     *
     * @param municipalityDTO the municipalityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated municipalityDTO,
     * or with status {@code 400 (Bad Request)} if the municipalityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the municipalityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/municipalities")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<MunicipalityDTO> updateMunicipality(@RequestBody MunicipalityDTO municipalityDTO) throws URISyntaxException {
        log.debug("REST request to update Municipality : {}", municipalityDTO);
        if (municipalityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MunicipalityDTO result = municipalityService.save(municipalityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, municipalityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /municipalities} : get all the municipalities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of municipalities in body.
     */
    @GetMapping("/municipalities")
    public ResponseEntity<List<MunicipalityDTO>> getAllMunicipalities(MunicipalityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Municipalities by criteria: {}", criteria);
        Page<MunicipalityDTO> page = municipalityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /municipalities/count} : count all the municipalities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/municipalities/count")
    public ResponseEntity<Long> countMunicipalities(MunicipalityCriteria criteria) {
        log.debug("REST request to count Municipalities by criteria: {}", criteria);
        return ResponseEntity.ok().body(municipalityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /municipalities/:id} : get the "id" municipality.
     *
     * @param id the id of the municipalityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the municipalityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/municipalities/{id}")
    public ResponseEntity<MunicipalityDTO> getMunicipality(@PathVariable Long id) {
        log.debug("REST request to get Municipality : {}", id);
        Optional<MunicipalityDTO> municipalityDTO = municipalityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(municipalityDTO);
    }

    /**
     * {@code DELETE  /municipalities/:id} : delete the "id" municipality.
     *
     * @param id the id of the municipalityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/municipalities/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteMunicipality(@PathVariable Long id) {
        log.debug("REST request to delete Municipality : {}", id);
        municipalityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
