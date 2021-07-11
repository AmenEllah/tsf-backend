package com.attijari.web.rest;

import com.attijari.domain.SubcontractingStaff;
import com.attijari.service.dto.SubcontractingStaffCriteria;
import com.attijari.service.dto.SubcontractingStaffDTO;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.SubcontractingStaffService;
import com.attijari.service.SubcontractingStaffQueryService;

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
 * REST controller for managing {@link SubcontractingStaff}.
 */
@RestController
@RequestMapping("/api")
public class SubcontractingStaffResource {

    private final Logger log = LoggerFactory.getLogger(SubcontractingStaffResource.class);

    private static final String ENTITY_NAME = "subcontractingStaff";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubcontractingStaffService subcontractingStaffService;

    private final SubcontractingStaffQueryService subcontractingStaffQueryService;

    public SubcontractingStaffResource(SubcontractingStaffService subcontractingStaffService, SubcontractingStaffQueryService subcontractingStaffQueryService) {
        this.subcontractingStaffService = subcontractingStaffService;
        this.subcontractingStaffQueryService = subcontractingStaffQueryService;
    }

    /**
     * {@code POST  /subcontracting-staffs} : Create a new subcontractingStaff.
     *
     * @param subcontractingStaffDTO the subcontractingStaffDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subcontractingStaffDTO, or with status {@code 400 (Bad Request)} if the subcontractingStaff has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subcontracting-staffs")
    public ResponseEntity<SubcontractingStaffDTO> createSubcontractingStaff(@Valid @RequestBody SubcontractingStaffDTO subcontractingStaffDTO) throws URISyntaxException {
        log.debug("REST request to save SubcontractingStaff : {}", subcontractingStaffDTO);
        if (subcontractingStaffDTO.getId() != null) {
            throw new BadRequestAlertException("A new subcontractingStaff cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubcontractingStaffDTO result = subcontractingStaffService.save(subcontractingStaffDTO);
        return ResponseEntity.created(new URI("/api/subcontracting-staffs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subcontracting-staffs} : Updates an existing subcontractingStaff.
     *
     * @param subcontractingStaffDTO the subcontractingStaffDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subcontractingStaffDTO,
     * or with status {@code 400 (Bad Request)} if the subcontractingStaffDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subcontractingStaffDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subcontracting-staffs")
    public ResponseEntity<SubcontractingStaffDTO> updateSubcontractingStaff(@Valid @RequestBody SubcontractingStaffDTO subcontractingStaffDTO) throws URISyntaxException {
        log.debug("REST request to update SubcontractingStaff : {}", subcontractingStaffDTO);
        if (subcontractingStaffDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubcontractingStaffDTO result = subcontractingStaffService.save(subcontractingStaffDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subcontractingStaffDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subcontracting-staffs} : get all the subcontractingStaffs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subcontractingStaffs in body.
     */
    @GetMapping("/subcontracting-staffs")
    public ResponseEntity<List<SubcontractingStaffDTO>> getAllSubcontractingStaffs(SubcontractingStaffCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SubcontractingStaffs by criteria: {}", criteria);
        Page<SubcontractingStaffDTO> page = subcontractingStaffQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subcontracting-staffs/count} : count all the subcontractingStaffs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/subcontracting-staffs/count")
    public ResponseEntity<Long> countSubcontractingStaffs(SubcontractingStaffCriteria criteria) {
        log.debug("REST request to count SubcontractingStaffs by criteria: {}", criteria);
        return ResponseEntity.ok().body(subcontractingStaffQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /subcontracting-staffs/:id} : get the "id" subcontractingStaff.
     *
     * @param id the id of the subcontractingStaffDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subcontractingStaffDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subcontracting-staffs/{id}")
    public ResponseEntity<SubcontractingStaffDTO> getSubcontractingStaff(@PathVariable Long id) {
        log.debug("REST request to get SubcontractingStaff : {}", id);
        Optional<SubcontractingStaffDTO> subcontractingStaffDTO = subcontractingStaffService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subcontractingStaffDTO);
    }

    /**
     * {@code DELETE  /subcontracting-staffs/:id} : delete the "id" subcontractingStaff.
     *
     * @param id the id of the subcontractingStaffDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subcontracting-staffs/{id}")
    public ResponseEntity<Void> deleteSubcontractingStaff(@PathVariable Long id) {
        log.debug("REST request to delete SubcontractingStaff : {}", id);
        subcontractingStaffService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
