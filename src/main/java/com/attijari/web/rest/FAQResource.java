package com.attijari.web.rest;

import com.attijari.domain.FAQ;
import com.attijari.service.FAQQueryService;
import com.attijari.service.dto.FAQCriteria;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.security.AuthoritiesConstants;
import com.attijari.service.FAQService;
import com.attijari.service.dto.FAQDTO;

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
 * REST controller for managing {@link FAQ}.
 */
@RestController
@RequestMapping("/api")
public class FAQResource {

    private final Logger log = LoggerFactory.getLogger(FAQResource.class);

    private static final String ENTITY_NAME = "fAQ";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FAQService fAQService;

    private final FAQQueryService fAQQueryService;

    public FAQResource(FAQService fAQService, FAQQueryService fAQQueryService) {
        this.fAQService = fAQService;
        this.fAQQueryService = fAQQueryService;
    }

    /**
     * {@code POST  /faqs} : Create a new fAQ.
     *
     * @param fAQDTO the fAQDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fAQDTO, or with status {@code 400 (Bad Request)} if the fAQ has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/faqs")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<FAQDTO> createFAQ(@RequestBody FAQDTO fAQDTO) throws URISyntaxException {
        log.debug("REST request to save FAQ : {}", fAQDTO);
        if (fAQDTO.getId() != null) {
            throw new BadRequestAlertException("A new fAQ cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FAQDTO result = fAQService.save(fAQDTO);
        return ResponseEntity.created(new URI("/api/faqs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /faqs} : Updates an existing fAQ.
     *
     * @param fAQDTO the fAQDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fAQDTO,
     * or with status {@code 400 (Bad Request)} if the fAQDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fAQDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/faqs")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<FAQDTO> updateFAQ(@RequestBody FAQDTO fAQDTO) throws URISyntaxException {
        log.debug("REST request to update FAQ : {}", fAQDTO);
        if (fAQDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FAQDTO result = fAQService.save(fAQDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fAQDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /faqs} : get all the fAQS.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fAQS in body.
     */
    @GetMapping("/faqs")
    public ResponseEntity<List<FAQDTO>> getAllFAQS(FAQCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FAQS by criteria: {}", criteria);
        Page<FAQDTO> page = fAQQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /faqs/count} : count all the fAQS.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/faqs/count")
    public ResponseEntity<Long> countFAQS(FAQCriteria criteria) {
        log.debug("REST request to count FAQS by criteria: {}", criteria);
        return ResponseEntity.ok().body(fAQQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /faqs/:id} : get the "id" fAQ.
     *
     * @param id the id of the fAQDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fAQDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/faqs/{id}")
    public ResponseEntity<FAQDTO> getFAQ(@PathVariable Long id) {
        log.debug("REST request to get FAQ : {}", id);
        Optional<FAQDTO> fAQDTO = fAQService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fAQDTO);
    }

    /**
     * {@code DELETE  /faqs/:id} : delete the "id" fAQ.
     *
     * @param id the id of the fAQDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/faqs/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteFAQ(@PathVariable Long id) {
        log.debug("REST request to delete FAQ : {}", id);
        fAQService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
