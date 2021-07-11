package com.attijari.web.rest;

import com.attijari.domain.SupplyMatrix;
import com.attijari.service.dto.SupplyMatrixCriteria;
import com.attijari.service.dto.SupplyMatrixDTO;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.SupplyMatrixService;
import com.attijari.service.SupplyMatrixQueryService;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link SupplyMatrix}.
 */
@RestController
@RequestMapping("/api")
public class SupplyMatrixResource {

    private final Logger log = LoggerFactory.getLogger(SupplyMatrixResource.class);

    private static final String ENTITY_NAME = "supplyMatrix";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SupplyMatrixService supplyMatrixService;

    private final SupplyMatrixQueryService supplyMatrixQueryService;

    public SupplyMatrixResource(SupplyMatrixService supplyMatrixService, SupplyMatrixQueryService supplyMatrixQueryService) {
        this.supplyMatrixService = supplyMatrixService;
        this.supplyMatrixQueryService = supplyMatrixQueryService;
    }

    /**
     * {@code POST  /supply-matrices} : Create a new supplyMatrix.
     *
     * @param supplyMatrixDTO the supplyMatrixDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supplyMatrixDTO, or with status {@code 400 (Bad Request)} if the supplyMatrix has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/supply-matrices")
    public ResponseEntity<SupplyMatrixDTO> createSupplyMatrix(@RequestBody SupplyMatrixDTO supplyMatrixDTO) throws URISyntaxException {
        log.debug("REST request to save SupplyMatrix : {}", supplyMatrixDTO);
        if (supplyMatrixDTO.getId() != null) {
            throw new BadRequestAlertException("A new supplyMatrix cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupplyMatrixDTO result = supplyMatrixService.save(supplyMatrixDTO);
        return ResponseEntity.created(new URI("/api/supply-matrices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /supply-matrices} : Updates an existing supplyMatrix.
     *
     * @param supplyMatrixDTO the supplyMatrixDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supplyMatrixDTO,
     * or with status {@code 400 (Bad Request)} if the supplyMatrixDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supplyMatrixDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/supply-matrices")
    public ResponseEntity<SupplyMatrixDTO> updateSupplyMatrix(@RequestBody SupplyMatrixDTO supplyMatrixDTO) throws URISyntaxException {
        log.debug("REST request to update SupplyMatrix : {}", supplyMatrixDTO);
        if (supplyMatrixDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SupplyMatrixDTO result = supplyMatrixService.save(supplyMatrixDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, supplyMatrixDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /supply-matrices} : get all the supplyMatrices.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supplyMatrices in body.
     */
    @GetMapping("/supply-matrices")
    public ResponseEntity<List<SupplyMatrixDTO>> getAllSupplyMatrices(SupplyMatrixCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SupplyMatrices by criteria: {}", criteria);
        Page<SupplyMatrixDTO> page = supplyMatrixQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /supply-matrices/count} : count all the supplyMatrices.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/supply-matrices/count")
    public ResponseEntity<Long> countSupplyMatrices(SupplyMatrixCriteria criteria) {
        log.debug("REST request to count SupplyMatrices by criteria: {}", criteria);
        return ResponseEntity.ok().body(supplyMatrixQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /supply-matrices/:id} : get the "id" supplyMatrix.
     *
     * @param id the id of the supplyMatrixDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supplyMatrixDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/supply-matrices/{id}")
    public ResponseEntity<SupplyMatrixDTO> getSupplyMatrix(@PathVariable Long id) {
        log.debug("REST request to get SupplyMatrix : {}", id);
        Optional<SupplyMatrixDTO> supplyMatrixDTO = supplyMatrixService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supplyMatrixDTO);
    }

    /**
     * {@code DELETE  /supply-matrices/:id} : delete the "id" supplyMatrix.
     *
     * @param id the id of the supplyMatrixDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/supply-matrices/{id}")
    public ResponseEntity<Void> deleteSupplyMatrix(@PathVariable Long id) {
        log.debug("REST request to delete SupplyMatrix : {}", id);
        supplyMatrixService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
