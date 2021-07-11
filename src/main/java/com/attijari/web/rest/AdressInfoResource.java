package com.attijari.web.rest;

import com.attijari.domain.AdressInfo;
import com.attijari.service.AdressInfoQueryService;
import com.attijari.service.dto.AdressInfoCriteria;
import com.attijari.service.AdressInfoService;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.dto.AdressInfoDTO;

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
 * REST controller for managing {@link AdressInfo}.
 */
@RestController
@RequestMapping("/api")
public class AdressInfoResource {

    private final Logger log = LoggerFactory.getLogger(AdressInfoResource.class);

    private static final String ENTITY_NAME = "adressInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdressInfoService adressInfoService;

    private final AdressInfoQueryService adressInfoQueryService;

    public AdressInfoResource(AdressInfoService adressInfoService, AdressInfoQueryService adressInfoQueryService) {
        this.adressInfoService = adressInfoService;
        this.adressInfoQueryService = adressInfoQueryService;
    }

    /**
     * {@code POST  /adress-infos} : Create a new adressInfo.
     *
     * @param adressInfoDTO the adressInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adressInfoDTO, or with status {@code 400 (Bad Request)} if the adressInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adress-infos")
    public ResponseEntity<AdressInfoDTO> createAdressInfo(@RequestBody AdressInfoDTO adressInfoDTO) throws URISyntaxException {
        log.debug("REST request to save AdressInfo : {}", adressInfoDTO);
        if (adressInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new adressInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdressInfoDTO result = adressInfoService.save(adressInfoDTO);
        return ResponseEntity.created(new URI("/api/adress-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adress-infos} : Updates an existing adressInfo.
     *
     * @param adressInfoDTO the adressInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adressInfoDTO,
     * or with status {@code 400 (Bad Request)} if the adressInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adressInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adress-infos")
    public ResponseEntity<AdressInfoDTO> updateAdressInfo(@RequestBody AdressInfoDTO adressInfoDTO) throws URISyntaxException {
        log.debug("REST request to update AdressInfo : {}", adressInfoDTO);
        if (adressInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdressInfoDTO result = adressInfoService.save(adressInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adressInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adress-infos} : get all the adressInfos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adressInfos in body.
     */
    @GetMapping("/adress-infos")
    public ResponseEntity<List<AdressInfoDTO>> getAllAdressInfos(AdressInfoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AdressInfos by criteria: {}", criteria);
        Page<AdressInfoDTO> page = adressInfoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adress-infos/count} : count all the adressInfos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/adress-infos/count")
    public ResponseEntity<Long> countAdressInfos(AdressInfoCriteria criteria) {
        log.debug("REST request to count AdressInfos by criteria: {}", criteria);
        return ResponseEntity.ok().body(adressInfoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /adress-infos/:id} : get the "id" adressInfo.
     *
     * @param id the id of the adressInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adressInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adress-infos/{id}")
    public ResponseEntity<AdressInfoDTO> getAdressInfo(@PathVariable Long id) {
        log.debug("REST request to get AdressInfo : {}", id);
        Optional<AdressInfoDTO> adressInfoDTO = adressInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adressInfoDTO);
    }

    /**
     * {@code DELETE  /adress-infos/:id} : delete the "id" adressInfo.
     *
     * @param id the id of the adressInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adress-infos/{id}")
    public ResponseEntity<Void> deleteAdressInfo(@PathVariable Long id) {
        log.debug("REST request to delete AdressInfo : {}", id);
        adressInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
