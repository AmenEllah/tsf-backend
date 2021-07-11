package com.attijari.web.rest;

import com.attijari.domain.FinancialInfo;
import com.attijari.service.*;
import com.attijari.service.dto.FinancialInfoCriteria;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.dto.FinancialInfoDTO;

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
 * REST controller for managing {@link FinancialInfo}.
 */
@RestController
@RequestMapping("/api")
public class FinancialInfoResource {

    private final Logger log = LoggerFactory.getLogger(FinancialInfoResource.class);

    private static final String ENTITY_NAME = "financialInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinancialInfoService financialInfoService;

    private final ActivityService activityService;

    private final CategoryService categoryService;

    private final FinancialInfoQueryService financialInfoQueryService;
    private final MonthlyNetIncomeService monthlyNetIncomeService;

    public FinancialInfoResource(FinancialInfoService financialInfoService, ActivityService activityService, CategoryService categoryService, FinancialInfoQueryService financialInfoQueryService, MonthlyNetIncomeService monthlyNetIncomeService) {
        this.financialInfoService = financialInfoService;
        this.activityService = activityService;
        this.categoryService = categoryService;
        this.financialInfoQueryService = financialInfoQueryService;
        this.monthlyNetIncomeService = monthlyNetIncomeService;
    }

    /**
     * {@code POST  /financial-infos} : Create a new financialInfo.
     *
     * @param financialInfoDTO the financialInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new financialInfoDTO, or with status {@code 400 (Bad Request)} if the financialInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/financial-infos")
    public ResponseEntity<FinancialInfoDTO> createFinancialInfo(@RequestBody FinancialInfoDTO financialInfoDTO) throws URISyntaxException {
        log.debug("REST request to save FinancialInfo : {}", financialInfoDTO);
        if (financialInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new financialInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinancialInfoDTO result = financialInfoService.save(financialInfoDTO);
        return ResponseEntity.created(new URI("/api/financial-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /financial-infos} : Updates an existing financialInfo.
     *
     * @param financialInfoDTO the financialInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financialInfoDTO,
     * or with status {@code 400 (Bad Request)} if the financialInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the financialInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/financial-infos")
    public ResponseEntity<FinancialInfoDTO> updateFinancialInfo(@RequestBody FinancialInfoDTO financialInfoDTO) throws URISyntaxException {
        log.debug("REST request to update FinancialInfo : {}", financialInfoDTO);
        if (financialInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinancialInfoDTO result = financialInfoService.save(financialInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, financialInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /financial-infos} : get all the financialInfos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of financialInfos in body.
     */
    @GetMapping("/financial-infos")
    public ResponseEntity<List<FinancialInfoDTO>> getAllFinancialInfos(FinancialInfoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FinancialInfos by criteria: {}", criteria);
        Page<FinancialInfoDTO> page = financialInfoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /financial-infos/count} : count all the financialInfos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/financial-infos/count")
    public ResponseEntity<Long> countFinancialInfos(FinancialInfoCriteria criteria) {
        log.debug("REST request to count FinancialInfos by criteria: {}", criteria);
        return ResponseEntity.ok().body(financialInfoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /financial-infos/:id} : get the "id" financialInfo.
     *
     * @param id the id of the financialInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the financialInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/financial-infos/{id}")
    public ResponseEntity<FinancialInfoDTO> getFinancialInfo(@PathVariable Long id) {
        log.debug("REST request to get FinancialInfo : {}", id);
        Optional<FinancialInfoDTO> financialInfoDTO = financialInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(financialInfoDTO);
    }

    /**
     * {@code DELETE  /financial-infos/:id} : delete the "id" financialInfo.
     *
     * @param id the id of the financialInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/financial-infos/{id}")
    public ResponseEntity<Void> deleteFinancialInfo(@PathVariable Long id) {
        log.debug("REST request to delete FinancialInfo : {}", id);
        financialInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


}
