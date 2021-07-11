package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.enumeration.RequestAffectation;
import com.attijari.service.dto.DerogationDTO;
import com.attijari.domain.Derogation;
import com.attijari.domain.Request;
import com.attijari.repository.DerogationRepository;
import com.attijari.service.DerogationService;
import com.attijari.service.mapper.DerogationMapper;
import com.attijari.service.DerogationQueryService;

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
 * Integration tests for the {@link DerogationResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DerogationResourceIT {

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final RequestAffectation DEFAULT_AFFECTATION = RequestAffectation.CONFORMITY_UNIT;
    private static final RequestAffectation UPDATED_AFFECTATION = RequestAffectation.REPOSITORY_UNIT;

    private static final String DEFAULT_MATRICULE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE = "BBBBBBBBBB";

    @Autowired
    private DerogationRepository derogationRepository;

    @Autowired
    private DerogationMapper derogationMapper;

    @Autowired
    private DerogationService derogationService;

    @Autowired
    private DerogationQueryService derogationQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDerogationMockMvc;

    private Derogation derogation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Derogation createEntity(EntityManager em) {
        Derogation derogation = new Derogation()
            .message(DEFAULT_MESSAGE)
            .affectation(DEFAULT_AFFECTATION)
            .matricule(DEFAULT_MATRICULE);
        return derogation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Derogation createUpdatedEntity(EntityManager em) {
        Derogation derogation = new Derogation()
            .message(UPDATED_MESSAGE)
            .affectation(UPDATED_AFFECTATION)
            .matricule(UPDATED_MATRICULE);
        return derogation;
    }

    @BeforeEach
    public void initTest() {
        derogation = createEntity(em);
    }

    @Test
    @Transactional
    public void createDerogation() throws Exception {
        int databaseSizeBeforeCreate = derogationRepository.findAll().size();
        // Create the Derogation
        DerogationDTO derogationDTO = derogationMapper.toDto(derogation);
        restDerogationMockMvc.perform(post("/api/derogations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(derogationDTO)))
            .andExpect(status().isCreated());

        // Validate the Derogation in the database
        List<Derogation> derogationList = derogationRepository.findAll();
        assertThat(derogationList).hasSize(databaseSizeBeforeCreate + 1);
        Derogation testDerogation = derogationList.get(derogationList.size() - 1);
        assertThat(testDerogation.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testDerogation.getAffectation()).isEqualTo(DEFAULT_AFFECTATION);
        assertThat(testDerogation.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
    }

    @Test
    @Transactional
    public void createDerogationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = derogationRepository.findAll().size();

        // Create the Derogation with an existing ID
        derogation.setId(1L);
        DerogationDTO derogationDTO = derogationMapper.toDto(derogation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDerogationMockMvc.perform(post("/api/derogations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(derogationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Derogation in the database
        List<Derogation> derogationList = derogationRepository.findAll();
        assertThat(derogationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDerogations() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList
        restDerogationMockMvc.perform(get("/api/derogations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(derogation.getId().intValue())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].affectation").value(hasItem(DEFAULT_AFFECTATION.toString())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)));
    }

    @Test
    @Transactional
    public void getDerogation() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get the derogation
        restDerogationMockMvc.perform(get("/api/derogations/{id}", derogation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(derogation.getId().intValue()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.affectation").value(DEFAULT_AFFECTATION.toString()))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE));
    }


    @Test
    @Transactional
    public void getDerogationsByIdFiltering() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        Long id = derogation.getId();

        defaultDerogationShouldBeFound("id.equals=" + id);
        defaultDerogationShouldNotBeFound("id.notEquals=" + id);

        defaultDerogationShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDerogationShouldNotBeFound("id.greaterThan=" + id);

        defaultDerogationShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDerogationShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDerogationsByMessageIsEqualToSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where message equals to DEFAULT_MESSAGE
        defaultDerogationShouldBeFound("message.equals=" + DEFAULT_MESSAGE);

        // Get all the derogationList where message equals to UPDATED_MESSAGE
        defaultDerogationShouldNotBeFound("message.equals=" + UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllDerogationsByMessageIsNotEqualToSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where message not equals to DEFAULT_MESSAGE
        defaultDerogationShouldNotBeFound("message.notEquals=" + DEFAULT_MESSAGE);

        // Get all the derogationList where message not equals to UPDATED_MESSAGE
        defaultDerogationShouldBeFound("message.notEquals=" + UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllDerogationsByMessageIsInShouldWork() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where message in DEFAULT_MESSAGE or UPDATED_MESSAGE
        defaultDerogationShouldBeFound("message.in=" + DEFAULT_MESSAGE + "," + UPDATED_MESSAGE);

        // Get all the derogationList where message equals to UPDATED_MESSAGE
        defaultDerogationShouldNotBeFound("message.in=" + UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllDerogationsByMessageIsNullOrNotNull() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where message is not null
        defaultDerogationShouldBeFound("message.specified=true");

        // Get all the derogationList where message is null
        defaultDerogationShouldNotBeFound("message.specified=false");
    }
                @Test
    @Transactional
    public void getAllDerogationsByMessageContainsSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where message contains DEFAULT_MESSAGE
        defaultDerogationShouldBeFound("message.contains=" + DEFAULT_MESSAGE);

        // Get all the derogationList where message contains UPDATED_MESSAGE
        defaultDerogationShouldNotBeFound("message.contains=" + UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllDerogationsByMessageNotContainsSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where message does not contain DEFAULT_MESSAGE
        defaultDerogationShouldNotBeFound("message.doesNotContain=" + DEFAULT_MESSAGE);

        // Get all the derogationList where message does not contain UPDATED_MESSAGE
        defaultDerogationShouldBeFound("message.doesNotContain=" + UPDATED_MESSAGE);
    }


    @Test
    @Transactional
    public void getAllDerogationsByAffectationIsEqualToSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where affectation equals to DEFAULT_AFFECTATION
        defaultDerogationShouldBeFound("affectation.equals=" + DEFAULT_AFFECTATION);

        // Get all the derogationList where affectation equals to UPDATED_AFFECTATION
        defaultDerogationShouldNotBeFound("affectation.equals=" + UPDATED_AFFECTATION);
    }

    @Test
    @Transactional
    public void getAllDerogationsByAffectationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where affectation not equals to DEFAULT_AFFECTATION
        defaultDerogationShouldNotBeFound("affectation.notEquals=" + DEFAULT_AFFECTATION);

        // Get all the derogationList where affectation not equals to UPDATED_AFFECTATION
        defaultDerogationShouldBeFound("affectation.notEquals=" + UPDATED_AFFECTATION);
    }

    @Test
    @Transactional
    public void getAllDerogationsByAffectationIsInShouldWork() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where affectation in DEFAULT_AFFECTATION or UPDATED_AFFECTATION
        defaultDerogationShouldBeFound("affectation.in=" + DEFAULT_AFFECTATION + "," + UPDATED_AFFECTATION);

        // Get all the derogationList where affectation equals to UPDATED_AFFECTATION
        defaultDerogationShouldNotBeFound("affectation.in=" + UPDATED_AFFECTATION);
    }

    @Test
    @Transactional
    public void getAllDerogationsByAffectationIsNullOrNotNull() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where affectation is not null
        defaultDerogationShouldBeFound("affectation.specified=true");

        // Get all the derogationList where affectation is null
        defaultDerogationShouldNotBeFound("affectation.specified=false");
    }

    @Test
    @Transactional
    public void getAllDerogationsByMatriculeIsEqualToSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where matricule equals to DEFAULT_MATRICULE
        defaultDerogationShouldBeFound("matricule.equals=" + DEFAULT_MATRICULE);

        // Get all the derogationList where matricule equals to UPDATED_MATRICULE
        defaultDerogationShouldNotBeFound("matricule.equals=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllDerogationsByMatriculeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where matricule not equals to DEFAULT_MATRICULE
        defaultDerogationShouldNotBeFound("matricule.notEquals=" + DEFAULT_MATRICULE);

        // Get all the derogationList where matricule not equals to UPDATED_MATRICULE
        defaultDerogationShouldBeFound("matricule.notEquals=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllDerogationsByMatriculeIsInShouldWork() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where matricule in DEFAULT_MATRICULE or UPDATED_MATRICULE
        defaultDerogationShouldBeFound("matricule.in=" + DEFAULT_MATRICULE + "," + UPDATED_MATRICULE);

        // Get all the derogationList where matricule equals to UPDATED_MATRICULE
        defaultDerogationShouldNotBeFound("matricule.in=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllDerogationsByMatriculeIsNullOrNotNull() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where matricule is not null
        defaultDerogationShouldBeFound("matricule.specified=true");

        // Get all the derogationList where matricule is null
        defaultDerogationShouldNotBeFound("matricule.specified=false");
    }
                @Test
    @Transactional
    public void getAllDerogationsByMatriculeContainsSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where matricule contains DEFAULT_MATRICULE
        defaultDerogationShouldBeFound("matricule.contains=" + DEFAULT_MATRICULE);

        // Get all the derogationList where matricule contains UPDATED_MATRICULE
        defaultDerogationShouldNotBeFound("matricule.contains=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllDerogationsByMatriculeNotContainsSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        // Get all the derogationList where matricule does not contain DEFAULT_MATRICULE
        defaultDerogationShouldNotBeFound("matricule.doesNotContain=" + DEFAULT_MATRICULE);

        // Get all the derogationList where matricule does not contain UPDATED_MATRICULE
        defaultDerogationShouldBeFound("matricule.doesNotContain=" + UPDATED_MATRICULE);
    }


    @Test
    @Transactional
    public void getAllDerogationsByRequestIsEqualToSomething() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);
        Request request = RequestResourceIT.createEntity(em);
        em.persist(request);
        em.flush();
        derogation.setRequest(request);
        derogationRepository.saveAndFlush(derogation);
        Long requestId = request.getId();

        // Get all the derogationList where request equals to requestId
        defaultDerogationShouldBeFound("requestId.equals=" + requestId);

        // Get all the derogationList where request equals to requestId + 1
        defaultDerogationShouldNotBeFound("requestId.equals=" + (requestId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDerogationShouldBeFound(String filter) throws Exception {
        restDerogationMockMvc.perform(get("/api/derogations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(derogation.getId().intValue())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].affectation").value(hasItem(DEFAULT_AFFECTATION.toString())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)));

        // Check, that the count call also returns 1
        restDerogationMockMvc.perform(get("/api/derogations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDerogationShouldNotBeFound(String filter) throws Exception {
        restDerogationMockMvc.perform(get("/api/derogations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDerogationMockMvc.perform(get("/api/derogations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingDerogation() throws Exception {
        // Get the derogation
        restDerogationMockMvc.perform(get("/api/derogations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDerogation() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        int databaseSizeBeforeUpdate = derogationRepository.findAll().size();

        // Update the derogation
        Derogation updatedDerogation = derogationRepository.findById(derogation.getId()).get();
        // Disconnect from session so that the updates on updatedDerogation are not directly saved in db
        em.detach(updatedDerogation);
        updatedDerogation
            .message(UPDATED_MESSAGE)
            .affectation(UPDATED_AFFECTATION)
            .matricule(UPDATED_MATRICULE);
        DerogationDTO derogationDTO = derogationMapper.toDto(updatedDerogation);

        restDerogationMockMvc.perform(put("/api/derogations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(derogationDTO)))
            .andExpect(status().isOk());

        // Validate the Derogation in the database
        List<Derogation> derogationList = derogationRepository.findAll();
        assertThat(derogationList).hasSize(databaseSizeBeforeUpdate);
        Derogation testDerogation = derogationList.get(derogationList.size() - 1);
        assertThat(testDerogation.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testDerogation.getAffectation()).isEqualTo(UPDATED_AFFECTATION);
        assertThat(testDerogation.getMatricule()).isEqualTo(UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void updateNonExistingDerogation() throws Exception {
        int databaseSizeBeforeUpdate = derogationRepository.findAll().size();

        // Create the Derogation
        DerogationDTO derogationDTO = derogationMapper.toDto(derogation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDerogationMockMvc.perform(put("/api/derogations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(derogationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Derogation in the database
        List<Derogation> derogationList = derogationRepository.findAll();
        assertThat(derogationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDerogation() throws Exception {
        // Initialize the database
        derogationRepository.saveAndFlush(derogation);

        int databaseSizeBeforeDelete = derogationRepository.findAll().size();

        // Delete the derogation
        restDerogationMockMvc.perform(delete("/api/derogations/{id}", derogation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Derogation> derogationList = derogationRepository.findAll();
        assertThat(derogationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
