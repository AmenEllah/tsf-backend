package com.attijari.web.rest;

import com.attijari.domain.Nationality;
import com.attijari.security.AuthoritiesConstants;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.NationalityService;
import com.attijari.service.dto.NationalityDTO;
import com.attijari.service.dto.NationalityCriteria;
import com.attijari.service.NationalityQueryService;

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
 * REST controller for managing {@link Nationality}.
 */
@RestController
@RequestMapping("/api")
public class NationalityResource {

    private final Logger log = LoggerFactory.getLogger(NationalityResource.class);

    private static final String ENTITY_NAME = "nationality";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NationalityService nationalityService;

    private final NationalityQueryService nationalityQueryService;

    public NationalityResource(NationalityService nationalityService, NationalityQueryService nationalityQueryService) {
        this.nationalityService = nationalityService;
        this.nationalityQueryService = nationalityQueryService;
    }

    /**
     * {@code POST  /nationalities} : Create a new nationality.
     *
     * @param nationalityDTO the nationalityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nationalityDTO, or with status {@code 400 (Bad Request)} if the nationality has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nationalities")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<NationalityDTO> createNationality(@RequestBody NationalityDTO nationalityDTO) throws URISyntaxException {
        log.debug("REST request to save Nationality : {}", nationalityDTO);
        if (nationalityDTO.getId() != null) {
            throw new BadRequestAlertException("A new nationality cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NationalityDTO result = nationalityService.save(nationalityDTO);
        return ResponseEntity.created(new URI("/api/nationalities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nationalities} : Updates an existing nationality.
     *
     * @param nationalityDTO the nationalityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nationalityDTO,
     * or with status {@code 400 (Bad Request)} if the nationalityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nationalityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nationalities")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<NationalityDTO> updateNationality(@RequestBody NationalityDTO nationalityDTO) throws URISyntaxException {
        log.debug("REST request to update Nationality : {}", nationalityDTO);
        if (nationalityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NationalityDTO result = nationalityService.save(nationalityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nationalityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nationalities} : get all the nationalities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nationalities in body.
     */
    @GetMapping("/nationalities")
    public ResponseEntity<List<NationalityDTO>> getAllNationalities(NationalityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Nationalities by criteria: {}", criteria);
        Page<NationalityDTO> page = nationalityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nationalities/count} : count all the nationalities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/nationalities/count")
    public ResponseEntity<Long> countNationalities(NationalityCriteria criteria) {
        log.debug("REST request to count Nationalities by criteria: {}", criteria);
        return ResponseEntity.ok().body(nationalityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nationalities/:id} : get the "id" nationality.
     *
     * @param id the id of the nationalityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nationalityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nationalities/{id}")
    public ResponseEntity<NationalityDTO> getNationality(@PathVariable Long id) {
        log.debug("REST request to get Nationality : {}", id);
        Optional<NationalityDTO> nationalityDTO = nationalityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nationalityDTO);
    }

    /**
     * {@code DELETE  /nationalities/:id} : delete the "id" nationality.
     *
     * @param id the id of the nationalityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nationalities/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteNationality(@PathVariable Long id) {
        log.debug("REST request to delete Nationality : {}", id);
        nationalityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
