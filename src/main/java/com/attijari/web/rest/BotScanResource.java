package com.attijari.web.rest;

import com.attijari.domain.BotScan;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.service.BotScanService;
import com.attijari.service.dto.BotScanDTO;
import com.attijari.service.dto.BotScanCriteria;
import com.attijari.service.BotScanQueryService;

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
 * REST controller for managing {@link BotScan}.
 */
@RestController
@RequestMapping("/api")
public class BotScanResource {

    private final Logger log = LoggerFactory.getLogger(BotScanResource.class);

    private static final String ENTITY_NAME = "botScan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BotScanService botScanService;

    private final BotScanQueryService botScanQueryService;

    public BotScanResource(BotScanService botScanService, BotScanQueryService botScanQueryService) {
        this.botScanService = botScanService;
        this.botScanQueryService = botScanQueryService;
    }

    /**
     * {@code POST  /bot-scans} : Create a new botScan.
     *
     * @param botScanDTO the botScanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new botScanDTO, or with status {@code 400 (Bad Request)} if the botScan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bot-scans")
    public ResponseEntity<BotScanDTO> createBotScan(@RequestBody BotScanDTO botScanDTO) throws URISyntaxException {
        log.debug("REST request to save BotScan : {}", botScanDTO);
        if (botScanDTO.getId() != null) {
            throw new BadRequestAlertException("A new botScan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BotScanDTO result = botScanService.save(botScanDTO);
        return ResponseEntity.created(new URI("/api/bot-scans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bot-scans} : Updates an existing botScan.
     *
     * @param botScanDTO the botScanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated botScanDTO,
     * or with status {@code 400 (Bad Request)} if the botScanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the botScanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bot-scans")
    public ResponseEntity<BotScanDTO> updateBotScan(@RequestBody BotScanDTO botScanDTO) throws URISyntaxException {
        log.debug("REST request to update BotScan : {}", botScanDTO);
        if (botScanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BotScanDTO result = botScanService.save(botScanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, botScanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bot-scans} : get all the botScans.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of botScans in body.
     */
    @GetMapping("/bot-scans")
    public ResponseEntity<List<BotScanDTO>> getAllBotScans(BotScanCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BotScans by criteria: {}", criteria);
        Page<BotScanDTO> page = botScanQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bot-scans/count} : count all the botScans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/bot-scans/count")
    public ResponseEntity<Long> countBotScans(BotScanCriteria criteria) {
        log.debug("REST request to count BotScans by criteria: {}", criteria);
        return ResponseEntity.ok().body(botScanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bot-scans/:id} : get the "id" botScan.
     *
     * @param id the id of the botScanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the botScanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bot-scans/{id}")
    public ResponseEntity<BotScanDTO> getBotScan(@PathVariable Long id) {
        log.debug("REST request to get BotScan : {}", id);
        Optional<BotScanDTO> botScanDTO = botScanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(botScanDTO);
    }

    /**
     * {@code DELETE  /bot-scans/:id} : delete the "id" botScan.
     *
     * @param id the id of the botScanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bot-scans/{id}")
    public ResponseEntity<Void> deleteBotScan(@PathVariable Long id) {
        log.debug("REST request to delete BotScan : {}", id);
        botScanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
