package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.BotScan;
import com.attijari.repository.BotScanRepository;
import com.attijari.service.BotScanQueryService;
import com.attijari.service.BotScanService;
import com.attijari.service.dto.BotScanDTO;
import com.attijari.service.mapper.BotScanMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BotScanResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BotScanResourceIT {

    private static final String DEFAULT_REF_DEMANDE = "AAAAAAAAAA";
    private static final String UPDATED_REF_DEMANDE = "BBBBBBBBBB";

    private static final String DEFAULT_CLI_DELTA = "AAAAAAAAAA";
    private static final String UPDATED_CLI_DELTA = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATURE = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPTE = "AAAAAAAAAA";
    private static final String UPDATED_COMPTE = "BBBBBBBBBB";

    @Autowired
    private BotScanRepository botScanRepository;

    @Autowired
    private BotScanMapper botScanMapper;

    @Autowired
    private BotScanService botScanService;

    @Autowired
    private BotScanQueryService botScanQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBotScanMockMvc;

    private BotScan botScan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BotScan createEntity(EntityManager em) {
        BotScan botScan = new BotScan()
            .ref_demande(DEFAULT_REF_DEMANDE)
            .cliDelta(DEFAULT_CLI_DELTA)
            .signature(DEFAULT_SIGNATURE)
            .compte(DEFAULT_COMPTE);
        return botScan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BotScan createUpdatedEntity(EntityManager em) {
        BotScan botScan = new BotScan()
            .ref_demande(UPDATED_REF_DEMANDE)
            .cliDelta(UPDATED_CLI_DELTA)
            .signature(UPDATED_SIGNATURE)
            .compte(UPDATED_COMPTE);
        return botScan;
    }

    @BeforeEach
    public void initTest() {
        botScan = createEntity(em);
    }

    @Test
    @Transactional
    public void createBotScan() throws Exception {
        int databaseSizeBeforeCreate = botScanRepository.findAll().size();
        // Create the BotScan
        BotScanDTO botScanDTO = botScanMapper.toDto(botScan);
        restBotScanMockMvc.perform(post("/api/bot-scans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(botScanDTO)))
            .andExpect(status().isCreated());

        // Validate the BotScan in the database
        List<BotScan> botScanList = botScanRepository.findAll();
        assertThat(botScanList).hasSize(databaseSizeBeforeCreate + 1);
        BotScan testBotScan = botScanList.get(botScanList.size() - 1);
        assertThat(testBotScan.getRef_demande()).isEqualTo(DEFAULT_REF_DEMANDE);
        assertThat(testBotScan.getCliDelta()).isEqualTo(DEFAULT_CLI_DELTA);
        assertThat(testBotScan.getSignature()).isEqualTo(DEFAULT_SIGNATURE);
        assertThat(testBotScan.getCompte()).isEqualTo(DEFAULT_COMPTE);
    }

    @Test
    @Transactional
    public void createBotScanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = botScanRepository.findAll().size();

        // Create the BotScan with an existing ID
        botScan.setId(1L);
        BotScanDTO botScanDTO = botScanMapper.toDto(botScan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBotScanMockMvc.perform(post("/api/bot-scans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(botScanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BotScan in the database
        List<BotScan> botScanList = botScanRepository.findAll();
        assertThat(botScanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBotScans() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList
        restBotScanMockMvc.perform(get("/api/bot-scans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(botScan.getId().intValue())))
            .andExpect(jsonPath("$.[*].ref_demande").value(hasItem(DEFAULT_REF_DEMANDE)))
            .andExpect(jsonPath("$.[*].cliDelta").value(hasItem(DEFAULT_CLI_DELTA)))
            .andExpect(jsonPath("$.[*].signature").value(hasItem(DEFAULT_SIGNATURE)))
            .andExpect(jsonPath("$.[*].compte").value(hasItem(DEFAULT_COMPTE)));
    }

    @Test
    @Transactional
    public void getBotScan() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get the botScan
        restBotScanMockMvc.perform(get("/api/bot-scans/{id}", botScan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(botScan.getId().intValue()))
            .andExpect(jsonPath("$.ref_demande").value(DEFAULT_REF_DEMANDE))
            .andExpect(jsonPath("$.cliDelta").value(DEFAULT_CLI_DELTA))
            .andExpect(jsonPath("$.signature").value(DEFAULT_SIGNATURE))
            .andExpect(jsonPath("$.compte").value(DEFAULT_COMPTE));
    }


    @Test
    @Transactional
    public void getBotScansByIdFiltering() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        Long id = botScan.getId();

        defaultBotScanShouldBeFound("id.equals=" + id);
        defaultBotScanShouldNotBeFound("id.notEquals=" + id);

        defaultBotScanShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBotScanShouldNotBeFound("id.greaterThan=" + id);

        defaultBotScanShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBotScanShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllBotScansByRef_demandeIsEqualToSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where ref_demande equals to DEFAULT_REF_DEMANDE
        defaultBotScanShouldBeFound("ref_demande.equals=" + DEFAULT_REF_DEMANDE);

        // Get all the botScanList where ref_demande equals to UPDATED_REF_DEMANDE
        defaultBotScanShouldNotBeFound("ref_demande.equals=" + UPDATED_REF_DEMANDE);
    }

    @Test
    @Transactional
    public void getAllBotScansByRef_demandeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where ref_demande not equals to DEFAULT_REF_DEMANDE
        defaultBotScanShouldNotBeFound("ref_demande.notEquals=" + DEFAULT_REF_DEMANDE);

        // Get all the botScanList where ref_demande not equals to UPDATED_REF_DEMANDE
        defaultBotScanShouldBeFound("ref_demande.notEquals=" + UPDATED_REF_DEMANDE);
    }

    @Test
    @Transactional
    public void getAllBotScansByRef_demandeIsInShouldWork() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where ref_demande in DEFAULT_REF_DEMANDE or UPDATED_REF_DEMANDE
        defaultBotScanShouldBeFound("ref_demande.in=" + DEFAULT_REF_DEMANDE + "," + UPDATED_REF_DEMANDE);

        // Get all the botScanList where ref_demande equals to UPDATED_REF_DEMANDE
        defaultBotScanShouldNotBeFound("ref_demande.in=" + UPDATED_REF_DEMANDE);
    }

    @Test
    @Transactional
    public void getAllBotScansByRef_demandeIsNullOrNotNull() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where ref_demande is not null
        defaultBotScanShouldBeFound("ref_demande.specified=true");

        // Get all the botScanList where ref_demande is null
        defaultBotScanShouldNotBeFound("ref_demande.specified=false");
    }
                @Test
    @Transactional
    public void getAllBotScansByRef_demandeContainsSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where ref_demande contains DEFAULT_REF_DEMANDE
        defaultBotScanShouldBeFound("ref_demande.contains=" + DEFAULT_REF_DEMANDE);

        // Get all the botScanList where ref_demande contains UPDATED_REF_DEMANDE
        defaultBotScanShouldNotBeFound("ref_demande.contains=" + UPDATED_REF_DEMANDE);
    }

    @Test
    @Transactional
    public void getAllBotScansByRef_demandeNotContainsSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where ref_demande does not contain DEFAULT_REF_DEMANDE
        defaultBotScanShouldNotBeFound("ref_demande.doesNotContain=" + DEFAULT_REF_DEMANDE);

        // Get all the botScanList where ref_demande does not contain UPDATED_REF_DEMANDE
        defaultBotScanShouldBeFound("ref_demande.doesNotContain=" + UPDATED_REF_DEMANDE);
    }


    @Test
    @Transactional
    public void getAllBotScansByCliDeltaIsEqualToSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where cliDelta equals to DEFAULT_CLI_DELTA
        defaultBotScanShouldBeFound("cliDelta.equals=" + DEFAULT_CLI_DELTA);

        // Get all the botScanList where cliDelta equals to UPDATED_CLI_DELTA
        defaultBotScanShouldNotBeFound("cliDelta.equals=" + UPDATED_CLI_DELTA);
    }

    @Test
    @Transactional
    public void getAllBotScansByCliDeltaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where cliDelta not equals to DEFAULT_CLI_DELTA
        defaultBotScanShouldNotBeFound("cliDelta.notEquals=" + DEFAULT_CLI_DELTA);

        // Get all the botScanList where cliDelta not equals to UPDATED_CLI_DELTA
        defaultBotScanShouldBeFound("cliDelta.notEquals=" + UPDATED_CLI_DELTA);
    }

    @Test
    @Transactional
    public void getAllBotScansByCliDeltaIsInShouldWork() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where cliDelta in DEFAULT_CLI_DELTA or UPDATED_CLI_DELTA
        defaultBotScanShouldBeFound("cliDelta.in=" + DEFAULT_CLI_DELTA + "," + UPDATED_CLI_DELTA);

        // Get all the botScanList where cliDelta equals to UPDATED_CLI_DELTA
        defaultBotScanShouldNotBeFound("cliDelta.in=" + UPDATED_CLI_DELTA);
    }

    @Test
    @Transactional
    public void getAllBotScansByCliDeltaIsNullOrNotNull() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where cliDelta is not null
        defaultBotScanShouldBeFound("cliDelta.specified=true");

        // Get all the botScanList where cliDelta is null
        defaultBotScanShouldNotBeFound("cliDelta.specified=false");
    }
                @Test
    @Transactional
    public void getAllBotScansByCliDeltaContainsSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where cliDelta contains DEFAULT_CLI_DELTA
        defaultBotScanShouldBeFound("cliDelta.contains=" + DEFAULT_CLI_DELTA);

        // Get all the botScanList where cliDelta contains UPDATED_CLI_DELTA
        defaultBotScanShouldNotBeFound("cliDelta.contains=" + UPDATED_CLI_DELTA);
    }

    @Test
    @Transactional
    public void getAllBotScansByCliDeltaNotContainsSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where cliDelta does not contain DEFAULT_CLI_DELTA
        defaultBotScanShouldNotBeFound("cliDelta.doesNotContain=" + DEFAULT_CLI_DELTA);

        // Get all the botScanList where cliDelta does not contain UPDATED_CLI_DELTA
        defaultBotScanShouldBeFound("cliDelta.doesNotContain=" + UPDATED_CLI_DELTA);
    }


    @Test
    @Transactional
    public void getAllBotScansBySignatureIsEqualToSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where signature equals to DEFAULT_SIGNATURE
        defaultBotScanShouldBeFound("signature.equals=" + DEFAULT_SIGNATURE);

        // Get all the botScanList where signature equals to UPDATED_SIGNATURE
        defaultBotScanShouldNotBeFound("signature.equals=" + UPDATED_SIGNATURE);
    }

    @Test
    @Transactional
    public void getAllBotScansBySignatureIsNotEqualToSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where signature not equals to DEFAULT_SIGNATURE
        defaultBotScanShouldNotBeFound("signature.notEquals=" + DEFAULT_SIGNATURE);

        // Get all the botScanList where signature not equals to UPDATED_SIGNATURE
        defaultBotScanShouldBeFound("signature.notEquals=" + UPDATED_SIGNATURE);
    }

    @Test
    @Transactional
    public void getAllBotScansBySignatureIsInShouldWork() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where signature in DEFAULT_SIGNATURE or UPDATED_SIGNATURE
        defaultBotScanShouldBeFound("signature.in=" + DEFAULT_SIGNATURE + "," + UPDATED_SIGNATURE);

        // Get all the botScanList where signature equals to UPDATED_SIGNATURE
        defaultBotScanShouldNotBeFound("signature.in=" + UPDATED_SIGNATURE);
    }

    @Test
    @Transactional
    public void getAllBotScansBySignatureIsNullOrNotNull() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where signature is not null
        defaultBotScanShouldBeFound("signature.specified=true");

        // Get all the botScanList where signature is null
        defaultBotScanShouldNotBeFound("signature.specified=false");
    }
                @Test
    @Transactional
    public void getAllBotScansBySignatureContainsSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where signature contains DEFAULT_SIGNATURE
        defaultBotScanShouldBeFound("signature.contains=" + DEFAULT_SIGNATURE);

        // Get all the botScanList where signature contains UPDATED_SIGNATURE
        defaultBotScanShouldNotBeFound("signature.contains=" + UPDATED_SIGNATURE);
    }

    @Test
    @Transactional
    public void getAllBotScansBySignatureNotContainsSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where signature does not contain DEFAULT_SIGNATURE
        defaultBotScanShouldNotBeFound("signature.doesNotContain=" + DEFAULT_SIGNATURE);

        // Get all the botScanList where signature does not contain UPDATED_SIGNATURE
        defaultBotScanShouldBeFound("signature.doesNotContain=" + UPDATED_SIGNATURE);
    }


    @Test
    @Transactional
    public void getAllBotScansByCompteIsEqualToSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where compte equals to DEFAULT_COMPTE
        defaultBotScanShouldBeFound("compte.equals=" + DEFAULT_COMPTE);

        // Get all the botScanList where compte equals to UPDATED_COMPTE
        defaultBotScanShouldNotBeFound("compte.equals=" + UPDATED_COMPTE);
    }

    @Test
    @Transactional
    public void getAllBotScansByCompteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where compte not equals to DEFAULT_COMPTE
        defaultBotScanShouldNotBeFound("compte.notEquals=" + DEFAULT_COMPTE);

        // Get all the botScanList where compte not equals to UPDATED_COMPTE
        defaultBotScanShouldBeFound("compte.notEquals=" + UPDATED_COMPTE);
    }

    @Test
    @Transactional
    public void getAllBotScansByCompteIsInShouldWork() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where compte in DEFAULT_COMPTE or UPDATED_COMPTE
        defaultBotScanShouldBeFound("compte.in=" + DEFAULT_COMPTE + "," + UPDATED_COMPTE);

        // Get all the botScanList where compte equals to UPDATED_COMPTE
        defaultBotScanShouldNotBeFound("compte.in=" + UPDATED_COMPTE);
    }

    @Test
    @Transactional
    public void getAllBotScansByCompteIsNullOrNotNull() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where compte is not null
        defaultBotScanShouldBeFound("compte.specified=true");

        // Get all the botScanList where compte is null
        defaultBotScanShouldNotBeFound("compte.specified=false");
    }
                @Test
    @Transactional
    public void getAllBotScansByCompteContainsSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where compte contains DEFAULT_COMPTE
        defaultBotScanShouldBeFound("compte.contains=" + DEFAULT_COMPTE);

        // Get all the botScanList where compte contains UPDATED_COMPTE
        defaultBotScanShouldNotBeFound("compte.contains=" + UPDATED_COMPTE);
    }

    @Test
    @Transactional
    public void getAllBotScansByCompteNotContainsSomething() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        // Get all the botScanList where compte does not contain DEFAULT_COMPTE
        defaultBotScanShouldNotBeFound("compte.doesNotContain=" + DEFAULT_COMPTE);

        // Get all the botScanList where compte does not contain UPDATED_COMPTE
        defaultBotScanShouldBeFound("compte.doesNotContain=" + UPDATED_COMPTE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBotScanShouldBeFound(String filter) throws Exception {
        restBotScanMockMvc.perform(get("/api/bot-scans?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(botScan.getId().intValue())))
            .andExpect(jsonPath("$.[*].ref_demande").value(hasItem(DEFAULT_REF_DEMANDE)))
            .andExpect(jsonPath("$.[*].cliDelta").value(hasItem(DEFAULT_CLI_DELTA)))
            .andExpect(jsonPath("$.[*].signature").value(hasItem(DEFAULT_SIGNATURE)))
            .andExpect(jsonPath("$.[*].compte").value(hasItem(DEFAULT_COMPTE)));

        // Check, that the count call also returns 1
        restBotScanMockMvc.perform(get("/api/bot-scans/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBotScanShouldNotBeFound(String filter) throws Exception {
        restBotScanMockMvc.perform(get("/api/bot-scans?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBotScanMockMvc.perform(get("/api/bot-scans/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingBotScan() throws Exception {
        // Get the botScan
        restBotScanMockMvc.perform(get("/api/bot-scans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBotScan() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        int databaseSizeBeforeUpdate = botScanRepository.findAll().size();

        // Update the botScan
        BotScan updatedBotScan = botScanRepository.findById(botScan.getId()).get();
        // Disconnect from session so that the updates on updatedBotScan are not directly saved in db
        em.detach(updatedBotScan);
        updatedBotScan
            .ref_demande(UPDATED_REF_DEMANDE)
            .cliDelta(UPDATED_CLI_DELTA)
            .signature(UPDATED_SIGNATURE)
            .compte(UPDATED_COMPTE);
        BotScanDTO botScanDTO = botScanMapper.toDto(updatedBotScan);

        restBotScanMockMvc.perform(put("/api/bot-scans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(botScanDTO)))
            .andExpect(status().isOk());

        // Validate the BotScan in the database
        List<BotScan> botScanList = botScanRepository.findAll();
        assertThat(botScanList).hasSize(databaseSizeBeforeUpdate);
        BotScan testBotScan = botScanList.get(botScanList.size() - 1);
        assertThat(testBotScan.getRef_demande()).isEqualTo(UPDATED_REF_DEMANDE);
        assertThat(testBotScan.getCliDelta()).isEqualTo(UPDATED_CLI_DELTA);
        assertThat(testBotScan.getSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testBotScan.getCompte()).isEqualTo(UPDATED_COMPTE);
    }

    @Test
    @Transactional
    public void updateNonExistingBotScan() throws Exception {
        int databaseSizeBeforeUpdate = botScanRepository.findAll().size();

        // Create the BotScan
        BotScanDTO botScanDTO = botScanMapper.toDto(botScan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBotScanMockMvc.perform(put("/api/bot-scans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(botScanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BotScan in the database
        List<BotScan> botScanList = botScanRepository.findAll();
        assertThat(botScanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBotScan() throws Exception {
        // Initialize the database
        botScanRepository.saveAndFlush(botScan);

        int databaseSizeBeforeDelete = botScanRepository.findAll().size();

        // Delete the botScan
        restBotScanMockMvc.perform(delete("/api/bot-scans/{id}", botScan.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BotScan> botScanList = botScanRepository.findAll();
        assertThat(botScanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
