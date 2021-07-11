package com.attijari.web.rest;

import com.attijari.domain.DetailOffer;
import com.attijari.service.DetailOfferQueryService;
import com.attijari.service.dto.DetailOfferCriteria;
import com.attijari.security.AuthoritiesConstants;
import com.attijari.service.DetailOfferService;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.dto.DetailOfferDTO;

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
 * REST controller for managing {@link DetailOffer}.
 */
@RestController
@RequestMapping("/api")
public class DetailOfferResource {

    private final Logger log = LoggerFactory.getLogger(DetailOfferResource.class);

    private static final String ENTITY_NAME = "detailOffer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailOfferService detailOfferService;

    private final DetailOfferQueryService detailOfferQueryService;

    public DetailOfferResource(DetailOfferService detailOfferService, DetailOfferQueryService detailOfferQueryService) {
        this.detailOfferService = detailOfferService;
        this.detailOfferQueryService = detailOfferQueryService;
    }

    /**
     * {@code POST  /detail-offers} : Create a new detailOffer.
     *
     * @param detailOfferDTO the detailOfferDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detailOfferDTO, or with status {@code 400 (Bad Request)} if the detailOffer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detail-offers")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<DetailOfferDTO> createDetailOffer(@RequestBody DetailOfferDTO detailOfferDTO) throws URISyntaxException {
        log.debug("REST request to save DetailOffer : {}", detailOfferDTO);
        if (detailOfferDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailOffer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailOfferDTO result = detailOfferService.save(detailOfferDTO);
        return ResponseEntity.created(new URI("/api/detail-offers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /detail-offers} : Updates an existing detailOffer.
     *
     * @param detailOfferDTO the detailOfferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailOfferDTO,
     * or with status {@code 400 (Bad Request)} if the detailOfferDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detailOfferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detail-offers")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<DetailOfferDTO> updateDetailOffer(@RequestBody DetailOfferDTO detailOfferDTO) throws URISyntaxException {
        log.debug("REST request to update DetailOffer : {}", detailOfferDTO);
        if (detailOfferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetailOfferDTO result = detailOfferService.save(detailOfferDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailOfferDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /detail-offers} : get all the detailOffers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detailOffers in body.
     */
    @GetMapping("/detail-offers")
    public ResponseEntity<List<DetailOfferDTO>> getAllDetailOffers(DetailOfferCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DetailOffers by criteria: {}", criteria);
        Page<DetailOfferDTO> page = detailOfferQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /detail-offers/count} : count all the detailOffers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/detail-offers/count")
    public ResponseEntity<Long> countDetailOffers(DetailOfferCriteria criteria) {
        log.debug("REST request to count DetailOffers by criteria: {}", criteria);
        return ResponseEntity.ok().body(detailOfferQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /detail-offers/:id} : get the "id" detailOffer.
     *
     * @param id the id of the detailOfferDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detailOfferDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detail-offers/{id}")
    public ResponseEntity<DetailOfferDTO> getDetailOffer(@PathVariable Long id) {
        log.debug("REST request to get DetailOffer : {}", id);
        Optional<DetailOfferDTO> detailOfferDTO = detailOfferService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailOfferDTO);
    }

    /**
     * {@code DELETE  /detail-offers/:id} : delete the "id" detailOffer.
     *
     * @param id the id of the detailOfferDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detail-offers/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteDetailOffer(@PathVariable Long id) {
        log.debug("REST request to delete DetailOffer : {}", id);
        detailOfferService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
