package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.DetailOffer;
import com.attijari.domain.Offer;
import com.attijari.repository.DetailOfferRepository;
import com.attijari.service.DetailOfferService;
import com.attijari.service.dto.DetailOfferDTO;
import com.attijari.service.mapper.DetailOfferMapper;
import com.attijari.service.DetailOfferQueryService;

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
 * Integration tests for the {@link DetailOfferResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DetailOfferResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DetailOfferRepository detailOfferRepository;

    @Autowired
    private DetailOfferMapper detailOfferMapper;

    @Autowired
    private DetailOfferService detailOfferService;

    @Autowired
    private DetailOfferQueryService detailOfferQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetailOfferMockMvc;

    private DetailOffer detailOffer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailOffer createEntity(EntityManager em) {
        DetailOffer detailOffer = new DetailOffer()
            .description(DEFAULT_DESCRIPTION);
        return detailOffer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailOffer createUpdatedEntity(EntityManager em) {
        DetailOffer detailOffer = new DetailOffer()
            .description(UPDATED_DESCRIPTION);
        return detailOffer;
    }

    @BeforeEach
    public void initTest() {
        detailOffer = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetailOffer() throws Exception {
        int databaseSizeBeforeCreate = detailOfferRepository.findAll().size();
        // Create the DetailOffer
        DetailOfferDTO detailOfferDTO = detailOfferMapper.toDto(detailOffer);
        restDetailOfferMockMvc.perform(post("/api/detail-offers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailOfferDTO)))
            .andExpect(status().isCreated());

        // Validate the DetailOffer in the database
        List<DetailOffer> detailOfferList = detailOfferRepository.findAll();
        assertThat(detailOfferList).hasSize(databaseSizeBeforeCreate + 1);
        DetailOffer testDetailOffer = detailOfferList.get(detailOfferList.size() - 1);
        assertThat(testDetailOffer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDetailOfferWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detailOfferRepository.findAll().size();

        // Create the DetailOffer with an existing ID
        detailOffer.setId(1L);
        DetailOfferDTO detailOfferDTO = detailOfferMapper.toDto(detailOffer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailOfferMockMvc.perform(post("/api/detail-offers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailOfferDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailOffer in the database
        List<DetailOffer> detailOfferList = detailOfferRepository.findAll();
        assertThat(detailOfferList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDetailOffers() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        // Get all the detailOfferList
        restDetailOfferMockMvc.perform(get("/api/detail-offers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailOffer.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    public void getDetailOffer() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        // Get the detailOffer
        restDetailOfferMockMvc.perform(get("/api/detail-offers/{id}", detailOffer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailOffer.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDetailOffersByIdFiltering() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        Long id = detailOffer.getId();

        defaultDetailOfferShouldBeFound("id.equals=" + id);
        defaultDetailOfferShouldNotBeFound("id.notEquals=" + id);

        defaultDetailOfferShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDetailOfferShouldNotBeFound("id.greaterThan=" + id);

        defaultDetailOfferShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDetailOfferShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDetailOffersByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        // Get all the detailOfferList where description equals to DEFAULT_DESCRIPTION
        defaultDetailOfferShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the detailOfferList where description equals to UPDATED_DESCRIPTION
        defaultDetailOfferShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDetailOffersByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        // Get all the detailOfferList where description not equals to DEFAULT_DESCRIPTION
        defaultDetailOfferShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the detailOfferList where description not equals to UPDATED_DESCRIPTION
        defaultDetailOfferShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDetailOffersByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        // Get all the detailOfferList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultDetailOfferShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the detailOfferList where description equals to UPDATED_DESCRIPTION
        defaultDetailOfferShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDetailOffersByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        // Get all the detailOfferList where description is not null
        defaultDetailOfferShouldBeFound("description.specified=true");

        // Get all the detailOfferList where description is null
        defaultDetailOfferShouldNotBeFound("description.specified=false");
    }
                @Test
    @Transactional
    public void getAllDetailOffersByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        // Get all the detailOfferList where description contains DEFAULT_DESCRIPTION
        defaultDetailOfferShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the detailOfferList where description contains UPDATED_DESCRIPTION
        defaultDetailOfferShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDetailOffersByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        // Get all the detailOfferList where description does not contain DEFAULT_DESCRIPTION
        defaultDetailOfferShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the detailOfferList where description does not contain UPDATED_DESCRIPTION
        defaultDetailOfferShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllDetailOffersByOfferIsEqualToSomething() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);
        Offer offer = OfferResourceIT.createEntity(em);
        em.persist(offer);
        em.flush();
        detailOffer.addOffer(offer);
        detailOfferRepository.saveAndFlush(detailOffer);
        Long offerId = offer.getId();

        // Get all the detailOfferList where offer equals to offerId
        defaultDetailOfferShouldBeFound("offerId.equals=" + offerId);

        // Get all the detailOfferList where offer equals to offerId + 1
        defaultDetailOfferShouldNotBeFound("offerId.equals=" + (offerId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDetailOfferShouldBeFound(String filter) throws Exception {
        restDetailOfferMockMvc.perform(get("/api/detail-offers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailOffer.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDetailOfferMockMvc.perform(get("/api/detail-offers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDetailOfferShouldNotBeFound(String filter) throws Exception {
        restDetailOfferMockMvc.perform(get("/api/detail-offers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDetailOfferMockMvc.perform(get("/api/detail-offers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingDetailOffer() throws Exception {
        // Get the detailOffer
        restDetailOfferMockMvc.perform(get("/api/detail-offers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetailOffer() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        int databaseSizeBeforeUpdate = detailOfferRepository.findAll().size();

        // Update the detailOffer
        DetailOffer updatedDetailOffer = detailOfferRepository.findById(detailOffer.getId()).get();
        // Disconnect from session so that the updates on updatedDetailOffer are not directly saved in db
        em.detach(updatedDetailOffer);
        updatedDetailOffer
            .description(UPDATED_DESCRIPTION);
        DetailOfferDTO detailOfferDTO = detailOfferMapper.toDto(updatedDetailOffer);

        restDetailOfferMockMvc.perform(put("/api/detail-offers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailOfferDTO)))
            .andExpect(status().isOk());

        // Validate the DetailOffer in the database
        List<DetailOffer> detailOfferList = detailOfferRepository.findAll();
        assertThat(detailOfferList).hasSize(databaseSizeBeforeUpdate);
        DetailOffer testDetailOffer = detailOfferList.get(detailOfferList.size() - 1);
        assertThat(testDetailOffer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDetailOffer() throws Exception {
        int databaseSizeBeforeUpdate = detailOfferRepository.findAll().size();

        // Create the DetailOffer
        DetailOfferDTO detailOfferDTO = detailOfferMapper.toDto(detailOffer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailOfferMockMvc.perform(put("/api/detail-offers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailOfferDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailOffer in the database
        List<DetailOffer> detailOfferList = detailOfferRepository.findAll();
        assertThat(detailOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetailOffer() throws Exception {
        // Initialize the database
        detailOfferRepository.saveAndFlush(detailOffer);

        int databaseSizeBeforeDelete = detailOfferRepository.findAll().size();

        // Delete the detailOffer
        restDetailOfferMockMvc.perform(delete("/api/detail-offers/{id}", detailOffer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailOffer> detailOfferList = detailOfferRepository.findAll();
        assertThat(detailOfferList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
