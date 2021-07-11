package com.attijari.web.rest;

import com.attijari.domain.MonthlyNetIncome;
import com.attijari.service.MonthlyNetIncomeQueryService;
import com.attijari.service.dto.MonthlyNetIncomeCriteria;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.security.AuthoritiesConstants;
import com.attijari.service.MonthlyNetIncomeService;
import com.attijari.service.dto.MonthlyNetIncomeDTO;

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
 * REST controller for managing {@link MonthlyNetIncome}.
 */
@RestController
@RequestMapping("/api")
public class MonthlyNetIncomeResource {

    private final Logger log = LoggerFactory.getLogger(MonthlyNetIncomeResource.class);

    private static final String ENTITY_NAME = "monthlyNetIncome";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MonthlyNetIncomeService monthlyNetIncomeService;

    private final MonthlyNetIncomeQueryService monthlyNetIncomeQueryService;

    public MonthlyNetIncomeResource(MonthlyNetIncomeService monthlyNetIncomeService, MonthlyNetIncomeQueryService monthlyNetIncomeQueryService) {
        this.monthlyNetIncomeService = monthlyNetIncomeService;
        this.monthlyNetIncomeQueryService = monthlyNetIncomeQueryService;
    }

    /**
     * {@code POST  /monthly-net-incomes} : Create a new monthlyNetIncome.
     *
     * @param monthlyNetIncomeDTO the monthlyNetIncomeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new monthlyNetIncomeDTO, or with status {@code 400 (Bad Request)} if the monthlyNetIncome has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/monthly-net-incomes")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<MonthlyNetIncomeDTO> createMonthlyNetIncome(@RequestBody MonthlyNetIncomeDTO monthlyNetIncomeDTO) throws URISyntaxException {
        log.debug("REST request to save MonthlyNetIncome : {}", monthlyNetIncomeDTO);
        if (monthlyNetIncomeDTO.getId() != null) {
            throw new BadRequestAlertException("A new monthlyNetIncome cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MonthlyNetIncomeDTO result = monthlyNetIncomeService.save(monthlyNetIncomeDTO);
        return ResponseEntity.created(new URI("/api/monthly-net-incomes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /monthly-net-incomes} : Updates an existing monthlyNetIncome.
     *
     * @param monthlyNetIncomeDTO the monthlyNetIncomeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyNetIncomeDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyNetIncomeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the monthlyNetIncomeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/monthly-net-incomes")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<MonthlyNetIncomeDTO> updateMonthlyNetIncome(@RequestBody MonthlyNetIncomeDTO monthlyNetIncomeDTO) throws URISyntaxException {
        log.debug("REST request to update MonthlyNetIncome : {}", monthlyNetIncomeDTO);
        if (monthlyNetIncomeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MonthlyNetIncomeDTO result = monthlyNetIncomeService.save(monthlyNetIncomeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, monthlyNetIncomeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /monthly-net-incomes} : get all the monthlyNetIncomes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of monthlyNetIncomes in body.
     */
    @GetMapping("/monthly-net-incomes")
    public ResponseEntity<List<MonthlyNetIncomeDTO>> getAllMonthlyNetIncomes(MonthlyNetIncomeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MonthlyNetIncomes by criteria: {}", criteria);
        Page<MonthlyNetIncomeDTO> page = monthlyNetIncomeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /monthly-net-incomes/count} : count all the monthlyNetIncomes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/monthly-net-incomes/count")
    public ResponseEntity<Long> countMonthlyNetIncomes(MonthlyNetIncomeCriteria criteria) {
        log.debug("REST request to count MonthlyNetIncomes by criteria: {}", criteria);
        return ResponseEntity.ok().body(monthlyNetIncomeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /monthly-net-incomes/:id} : get the "id" monthlyNetIncome.
     *
     * @param id the id of the monthlyNetIncomeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the monthlyNetIncomeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/monthly-net-incomes/{id}")
    public ResponseEntity<MonthlyNetIncomeDTO> getMonthlyNetIncome(@PathVariable Long id) {
        log.debug("REST request to get MonthlyNetIncome : {}", id);
        Optional<MonthlyNetIncomeDTO> monthlyNetIncomeDTO = monthlyNetIncomeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monthlyNetIncomeDTO);
    }

    /**
     * {@code DELETE  /monthly-net-incomes/:id} : delete the "id" monthlyNetIncome.
     *
     * @param id the id of the monthlyNetIncomeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/monthly-net-incomes/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteMonthlyNetIncome(@PathVariable Long id) {
        log.debug("REST request to delete MonthlyNetIncome : {}", id);
        monthlyNetIncomeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
