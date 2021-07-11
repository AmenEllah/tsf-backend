package com.attijari.web.rest;

import com.attijari.domain.SubscriberStatus;
import com.attijari.service.SubscriberStatusQueryService;
import com.attijari.service.SubscriberStatusService;
import com.attijari.service.dto.SubscriberStatusCriteria;
import com.attijari.service.dto.SubscriberStatusDTO;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link SubscriberStatus}.
 */
@RestController
@RequestMapping("/api")
public class SubscriberStatusResource {

    private final Logger log = LoggerFactory.getLogger(SubscriberStatusResource.class);

    private static final String ENTITY_NAME = "subscriberStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubscriberStatusService subscriberStatusService;

    private final SubscriberStatusQueryService subscriberStatusQueryService;

    public SubscriberStatusResource(SubscriberStatusService subscriberStatusService, SubscriberStatusQueryService subscriberStatusQueryService) {
        this.subscriberStatusService = subscriberStatusService;
        this.subscriberStatusQueryService = subscriberStatusQueryService;
    }

    /**
     * {@code POST  /subscriber-statuses} : Create a new subscriberStatus.
     *
     * @param subscriberStatusDTO the subscriberStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subscriberStatusDTO, or with status {@code 400 (Bad Request)} if the subscriberStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subscriber-statuses")
    public ResponseEntity<SubscriberStatusDTO> createSubscriberStatus(@RequestBody SubscriberStatusDTO subscriberStatusDTO) throws URISyntaxException {
        log.debug("REST request to save SubscriberStatus : {}", subscriberStatusDTO);
        if (subscriberStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new subscriberStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubscriberStatusDTO result = subscriberStatusService.save(subscriberStatusDTO);
        return ResponseEntity.created(new URI("/api/subscriber-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subscriber-statuses} : Updates an existing subscriberStatus.
     *
     * @param subscriberStatusDTO the subscriberStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subscriberStatusDTO,
     * or with status {@code 400 (Bad Request)} if the subscriberStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subscriberStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subscriber-statuses")
    public ResponseEntity<SubscriberStatusDTO> updateSubscriberStatus(@RequestBody SubscriberStatusDTO subscriberStatusDTO) throws URISyntaxException {
        log.debug("REST request to update SubscriberStatus : {}", subscriberStatusDTO);
        if (subscriberStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubscriberStatusDTO result = subscriberStatusService.save(subscriberStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subscriberStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subscriber-statuses} : get all the subscriberStatuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subscriberStatuses in body.
     */
    @GetMapping("/subscriber-statuses")
    public ResponseEntity<List<SubscriberStatusDTO>> getAllSubscriberStatuses(SubscriberStatusCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SubscriberStatuses by criteria: {}", criteria);
        Page<SubscriberStatusDTO> page = subscriberStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subscriber-statuses/count} : count all the subscriberStatuses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/subscriber-statuses/count")
    public ResponseEntity<Long> countSubscriberStatuses(SubscriberStatusCriteria criteria) {
        log.debug("REST request to count SubscriberStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(subscriberStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /subscriber-statuses/:id} : get the "id" subscriberStatus.
     *
     * @param id the id of the subscriberStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subscriberStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subscriber-statuses/{id}")
    public ResponseEntity<SubscriberStatusDTO> getSubscriberStatus(@PathVariable Long id) {
        log.debug("REST request to get SubscriberStatus : {}", id);
        Optional<SubscriberStatusDTO> subscriberStatusDTO = subscriberStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subscriberStatusDTO);
    }

    /**
     * {@code DELETE  /subscriber-statuses/:id} : delete the "id" subscriberStatus.
     *
     * @param id the id of the subscriberStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subscriber-statuses/{id}")
    public ResponseEntity<Void> deleteSubscriberStatus(@PathVariable Long id) {
        log.debug("REST request to delete SubscriberStatus : {}", id);
        subscriberStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
