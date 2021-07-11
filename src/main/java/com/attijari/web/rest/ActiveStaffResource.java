package com.attijari.web.rest;

import com.attijari.domain.ActiveStaff;
import com.attijari.service.ActiveStaffQueryService;
import com.attijari.service.ActiveStaffService;
import com.attijari.service.dto.ActiveStaffCriteria;
import com.attijari.service.dto.ActiveStaffDTO;
import com.attijari.security.AuthoritiesConstants;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ActiveStaff}.
 */
@RestController
@RequestMapping("/api")
public class ActiveStaffResource {

    private final Logger log = LoggerFactory.getLogger(ActiveStaffResource.class);

    private static final String ENTITY_NAME = "activeStaff";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActiveStaffService activeStaffService;

    private final ActiveStaffQueryService activeStaffQueryService;

    public ActiveStaffResource(ActiveStaffService activeStaffService, ActiveStaffQueryService activeStaffQueryService) {
        this.activeStaffService = activeStaffService;
        this.activeStaffQueryService = activeStaffQueryService;
    }

    /**
     * {@code POST  /active-staffs} : Create a new activeStaff.
     *
     * @param activeStaffDTO the activeStaffDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activeStaffDTO, or with status {@code 400 (Bad Request)} if the activeStaff has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/active-staffs")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<ActiveStaffDTO> createActiveStaff(@Valid @RequestBody ActiveStaffDTO activeStaffDTO) throws URISyntaxException {
        log.debug("REST request to save ActiveStaff : {}", activeStaffDTO);
        if (activeStaffDTO.getMatricule() != null) {
            throw new BadRequestAlertException("A new activeStaff cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActiveStaffDTO result = activeStaffService.save(activeStaffDTO);
        return ResponseEntity.created(new URI("/api/active-staffs/" + result.getMatricule()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getMatricule().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /active-staffs} : Updates an existing activeStaff.
     *
     * @param activeStaffDTO the activeStaffDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activeStaffDTO,
     * or with status {@code 400 (Bad Request)} if the activeStaffDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activeStaffDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/active-staffs")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<ActiveStaffDTO> updateActiveStaff(@Valid @RequestBody ActiveStaffDTO activeStaffDTO) throws URISyntaxException {
        log.debug("REST request to update ActiveStaff : {}", activeStaffDTO);
        if (activeStaffDTO.getMatricule() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActiveStaffDTO result = activeStaffService.save(activeStaffDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activeStaffDTO.getMatricule().toString()))
            .body(result);
    }

    /**
     * {@code GET  /active-staffs} : get all the activeStaffs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activeStaffs in body.
     */
    @GetMapping("/active-staffs")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<List<ActiveStaffDTO>> getAllActiveStaffs(ActiveStaffCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ActiveStaffs by criteria: {}", criteria);
        Page<ActiveStaffDTO> page = activeStaffQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /active-staffs/count} : count all the activeStaffs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/active-staffs/count")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Long> countActiveStaffs(ActiveStaffCriteria criteria) {
        log.debug("REST request to count ActiveStaffs by criteria: {}", criteria);
        return ResponseEntity.ok().body(activeStaffQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /active-staffs/:id} : get the "id" activeStaff.
     *
     * @param id the id of the activeStaffDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activeStaffDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/active-staffs/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<ActiveStaffDTO> getActiveStaff(@PathVariable Integer id) {
        log.debug("REST request to get ActiveStaff : {}", id);
        Optional<ActiveStaffDTO> activeStaffDTO = activeStaffService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activeStaffDTO);
    }

    /**
     * {@code DELETE  /active-staffs/:id} : delete the "id" activeStaff.
     *
     * @param id the id of the activeStaffDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/active-staffs/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteActiveStaff(@PathVariable Integer id) {
        log.debug("REST request to delete ActiveStaff : {}", id);
        activeStaffService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
