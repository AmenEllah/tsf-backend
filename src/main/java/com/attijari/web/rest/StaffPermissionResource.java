package com.attijari.web.rest;

import com.attijari.domain.StaffPermission;
import com.attijari.service.StaffPermissionQueryService;
import com.attijari.service.StaffPermissionService;
import com.attijari.service.dto.StaffPermissionCriteria;
import com.attijari.service.dto.StaffPermissionDTO;
import com.attijari.web.rest.errors.BadRequestAlertException;

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
 * REST controller for managing {@link StaffPermission}.
 */
@RestController
@RequestMapping("/api")
public class StaffPermissionResource {

    private final Logger log = LoggerFactory.getLogger(StaffPermissionResource.class);

    private static final String ENTITY_NAME = "staffPermission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StaffPermissionService staffPermissionService;

    private final StaffPermissionQueryService staffPermissionQueryService;

    public StaffPermissionResource(StaffPermissionService staffPermissionService, StaffPermissionQueryService staffPermissionQueryService) {
        this.staffPermissionService = staffPermissionService;
        this.staffPermissionQueryService = staffPermissionQueryService;
    }

    /**
     * {@code POST  /staff-permissions} : Create a new staffPermission.
     *
     * @param staffPermissionDTO the staffPermissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new staffPermissionDTO, or with status {@code 400 (Bad Request)} if the staffPermission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/staff-permissions")
    public ResponseEntity<StaffPermissionDTO> createStaffPermission(@Valid @RequestBody StaffPermissionDTO staffPermissionDTO) throws URISyntaxException {
        log.debug("REST request to save StaffPermission : {}", staffPermissionDTO);
        if (staffPermissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new staffPermission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StaffPermissionDTO result = staffPermissionService.save(staffPermissionDTO);
        return ResponseEntity.created(new URI("/api/staff-permissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /staff-permissions} : Updates an existing staffPermission.
     *
     * @param staffPermissionDTO the staffPermissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated staffPermissionDTO,
     * or with status {@code 400 (Bad Request)} if the staffPermissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the staffPermissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/staff-permissions")
    public ResponseEntity<StaffPermissionDTO> updateStaffPermission(@Valid @RequestBody StaffPermissionDTO staffPermissionDTO) throws URISyntaxException {
        log.debug("REST request to update StaffPermission : {}", staffPermissionDTO);
        if (staffPermissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StaffPermissionDTO result = staffPermissionService.save(staffPermissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, staffPermissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /staff-permissions} : get all the staffPermissions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of staffPermissions in body.
     */
    @GetMapping("/staff-permissions")
    public ResponseEntity<List<StaffPermissionDTO>> getAllStaffPermissions(StaffPermissionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get StaffPermissions by criteria: {}", criteria);
        Page<StaffPermissionDTO> page = staffPermissionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /staff-permissions/count} : count all the staffPermissions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/staff-permissions/count")
    public ResponseEntity<Long> countStaffPermissions(StaffPermissionCriteria criteria) {
        log.debug("REST request to count StaffPermissions by criteria: {}", criteria);
        return ResponseEntity.ok().body(staffPermissionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /staff-permissions/:id} : get the "id" staffPermission.
     *
     * @param id the id of the staffPermissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the staffPermissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/staff-permissions/{id}")
    public ResponseEntity<StaffPermissionDTO> getStaffPermission(@PathVariable Long id) {
        log.debug("REST request to get StaffPermission : {}", id);
        Optional<StaffPermissionDTO> staffPermissionDTO = staffPermissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(staffPermissionDTO);
    }

    /**
     * {@code DELETE  /staff-permissions/:id} : delete the "id" staffPermission.
     *
     * @param id the id of the staffPermissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/staff-permissions/{id}")
    public ResponseEntity<Void> deleteStaffPermission(@PathVariable Long id) {
        log.debug("REST request to delete StaffPermission : {}", id);
        staffPermissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
