package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.Offer;
import com.attijari.domain.Request;
import com.attijari.domain.DetailOffer;
import com.attijari.repository.OfferRepository;
import com.attijari.service.OfferService;
import com.attijari.service.dto.OfferDTO;
import com.attijari.service.mapper.OfferMapper;
import com.attijari.service.OfferQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OfferResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class OfferResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;
    private static final Double SMALLER_PRICE = 1D - 1D;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private OfferRepository offerRepository;

    @Mock
    private OfferRepository offerRepositoryMock;

    @Autowired
    private OfferMapper offerMapper;

    @Mock
    private OfferService offerServiceMock;

    @Autowired
    private OfferService offerService;

    @Autowired
    private OfferQueryService offerQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOfferMockMvc;

    private Offer offer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Offer createEntity(EntityManager em) {
        Offer offer = new Offer()
            .name(DEFAULT_NAME)
            .price(DEFAULT_PRICE)
            .url(DEFAULT_URL);
        return offer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Offer createUpdatedEntity(EntityManager em) {
        Offer offer = new Offer()
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .url(UPDATED_URL);
        return offer;
    }

    @BeforeEach
    public void initTest() {
        offer = createEntity(em);
    }

    @Test
    @Transactional
    public void createOffer() throws Exception {
        int databaseSizeBeforeCreate = offerRepository.findAll().size();
        // Create the Offer
        OfferDTO offerDTO = offerMapper.toDto(offer);
        restOfferMockMvc.perform(post("/api/offers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
            .andExpect(status().isCreated());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeCreate + 1);
        Offer testOffer = offerList.get(offerList.size() - 1);
        assertThat(testOffer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOffer.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testOffer.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createOfferWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerRepository.findAll().size();

        // Create the Offer with an existing ID
        offer.setId(1L);
        OfferDTO offerDTO = offerMapper.toDto(offer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferMockMvc.perform(post("/api/offers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOffers() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList
        restOfferMockMvc.perform(get("/api/offers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }

    @SuppressWarnings({"unchecked"})
    public void getAllOffersWithEagerRelationshipsIsEnabled() throws Exception {
        when(offerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOfferMockMvc.perform(get("/api/offers?eagerload=true"))
            .andExpect(status().isOk());

        verify(offerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllOffersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(offerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOfferMockMvc.perform(get("/api/offers?eagerload=true"))
            .andExpect(status().isOk());

        verify(offerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getOffer() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get the offer
        restOfferMockMvc.perform(get("/api/offers/{id}", offer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offer.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }


    @Test
    @Transactional
    public void getOffersByIdFiltering() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        Long id = offer.getId();

        defaultOfferShouldBeFound("id.equals=" + id);
        defaultOfferShouldNotBeFound("id.notEquals=" + id);

        defaultOfferShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultOfferShouldNotBeFound("id.greaterThan=" + id);

        defaultOfferShouldBeFound("id.lessThanOrEqual=" + id);
        defaultOfferShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllOffersByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where name equals to DEFAULT_NAME
        defaultOfferShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the offerList where name equals to UPDATED_NAME
        defaultOfferShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOffersByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where name not equals to DEFAULT_NAME
        defaultOfferShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the offerList where name not equals to UPDATED_NAME
        defaultOfferShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOffersByNameIsInShouldWork() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where name in DEFAULT_NAME or UPDATED_NAME
        defaultOfferShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the offerList where name equals to UPDATED_NAME
        defaultOfferShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOffersByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where name is not null
        defaultOfferShouldBeFound("name.specified=true");

        // Get all the offerList where name is null
        defaultOfferShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllOffersByNameContainsSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where name contains DEFAULT_NAME
        defaultOfferShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the offerList where name contains UPDATED_NAME
        defaultOfferShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOffersByNameNotContainsSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where name does not contain DEFAULT_NAME
        defaultOfferShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the offerList where name does not contain UPDATED_NAME
        defaultOfferShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllOffersByPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where price equals to DEFAULT_PRICE
        defaultOfferShouldBeFound("price.equals=" + DEFAULT_PRICE);

        // Get all the offerList where price equals to UPDATED_PRICE
        defaultOfferShouldNotBeFound("price.equals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllOffersByPriceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where price not equals to DEFAULT_PRICE
        defaultOfferShouldNotBeFound("price.notEquals=" + DEFAULT_PRICE);

        // Get all the offerList where price not equals to UPDATED_PRICE
        defaultOfferShouldBeFound("price.notEquals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllOffersByPriceIsInShouldWork() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where price in DEFAULT_PRICE or UPDATED_PRICE
        defaultOfferShouldBeFound("price.in=" + DEFAULT_PRICE + "," + UPDATED_PRICE);

        // Get all the offerList where price equals to UPDATED_PRICE
        defaultOfferShouldNotBeFound("price.in=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllOffersByPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where price is not null
        defaultOfferShouldBeFound("price.specified=true");

        // Get all the offerList where price is null
        defaultOfferShouldNotBeFound("price.specified=false");
    }

    @Test
    @Transactional
    public void getAllOffersByPriceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where price is greater than or equal to DEFAULT_PRICE
        defaultOfferShouldBeFound("price.greaterThanOrEqual=" + DEFAULT_PRICE);

        // Get all the offerList where price is greater than or equal to UPDATED_PRICE
        defaultOfferShouldNotBeFound("price.greaterThanOrEqual=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllOffersByPriceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where price is less than or equal to DEFAULT_PRICE
        defaultOfferShouldBeFound("price.lessThanOrEqual=" + DEFAULT_PRICE);

        // Get all the offerList where price is less than or equal to SMALLER_PRICE
        defaultOfferShouldNotBeFound("price.lessThanOrEqual=" + SMALLER_PRICE);
    }

    @Test
    @Transactional
    public void getAllOffersByPriceIsLessThanSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where price is less than DEFAULT_PRICE
        defaultOfferShouldNotBeFound("price.lessThan=" + DEFAULT_PRICE);

        // Get all the offerList where price is less than UPDATED_PRICE
        defaultOfferShouldBeFound("price.lessThan=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllOffersByPriceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where price is greater than DEFAULT_PRICE
        defaultOfferShouldNotBeFound("price.greaterThan=" + DEFAULT_PRICE);

        // Get all the offerList where price is greater than SMALLER_PRICE
        defaultOfferShouldBeFound("price.greaterThan=" + SMALLER_PRICE);
    }


    @Test
    @Transactional
    public void getAllOffersByUrlIsEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where url equals to DEFAULT_URL
        defaultOfferShouldBeFound("url.equals=" + DEFAULT_URL);

        // Get all the offerList where url equals to UPDATED_URL
        defaultOfferShouldNotBeFound("url.equals=" + UPDATED_URL);
    }

    @Test
    @Transactional
    public void getAllOffersByUrlIsNotEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where url not equals to DEFAULT_URL
        defaultOfferShouldNotBeFound("url.notEquals=" + DEFAULT_URL);

        // Get all the offerList where url not equals to UPDATED_URL
        defaultOfferShouldBeFound("url.notEquals=" + UPDATED_URL);
    }

    @Test
    @Transactional
    public void getAllOffersByUrlIsInShouldWork() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where url in DEFAULT_URL or UPDATED_URL
        defaultOfferShouldBeFound("url.in=" + DEFAULT_URL + "," + UPDATED_URL);

        // Get all the offerList where url equals to UPDATED_URL
        defaultOfferShouldNotBeFound("url.in=" + UPDATED_URL);
    }

    @Test
    @Transactional
    public void getAllOffersByUrlIsNullOrNotNull() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where url is not null
        defaultOfferShouldBeFound("url.specified=true");

        // Get all the offerList where url is null
        defaultOfferShouldNotBeFound("url.specified=false");
    }
                @Test
    @Transactional
    public void getAllOffersByUrlContainsSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where url contains DEFAULT_URL
        defaultOfferShouldBeFound("url.contains=" + DEFAULT_URL);

        // Get all the offerList where url contains UPDATED_URL
        defaultOfferShouldNotBeFound("url.contains=" + UPDATED_URL);
    }

    @Test
    @Transactional
    public void getAllOffersByUrlNotContainsSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList where url does not contain DEFAULT_URL
        defaultOfferShouldNotBeFound("url.doesNotContain=" + DEFAULT_URL);

        // Get all the offerList where url does not contain UPDATED_URL
        defaultOfferShouldBeFound("url.doesNotContain=" + UPDATED_URL);
    }


    @Test
    @Transactional
    public void getAllOffersByRequestIsEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);
        Request request = RequestResourceIT.createEntity(em);
        em.persist(request);
        em.flush();
        offer.addRequest(request);
        offerRepository.saveAndFlush(offer);
        Long requestId = request.getId();

        // Get all the offerList where request equals to requestId
        defaultOfferShouldBeFound("requestId.equals=" + requestId);

        // Get all the offerList where request equals to requestId + 1
        defaultOfferShouldNotBeFound("requestId.equals=" + (requestId + 1));
    }


    @Test
    @Transactional
    public void getAllOffersByDetailOfferIsEqualToSomething() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);
        DetailOffer detailOffer = DetailOfferResourceIT.createEntity(em);
        em.persist(detailOffer);
        em.flush();
        offer.addDetailOffer(detailOffer);
        offerRepository.saveAndFlush(offer);
        Long detailOfferId = detailOffer.getId();

        // Get all the offerList where detailOffer equals to detailOfferId
        defaultOfferShouldBeFound("detailOfferId.equals=" + detailOfferId);

        // Get all the offerList where detailOffer equals to detailOfferId + 1
        defaultOfferShouldNotBeFound("detailOfferId.equals=" + (detailOfferId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultOfferShouldBeFound(String filter) throws Exception {
        restOfferMockMvc.perform(get("/api/offers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));

        // Check, that the count call also returns 1
        restOfferMockMvc.perform(get("/api/offers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultOfferShouldNotBeFound(String filter) throws Exception {
        restOfferMockMvc.perform(get("/api/offers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOfferMockMvc.perform(get("/api/offers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingOffer() throws Exception {
        // Get the offer
        restOfferMockMvc.perform(get("/api/offers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOffer() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        int databaseSizeBeforeUpdate = offerRepository.findAll().size();

        // Update the offer
        Offer updatedOffer = offerRepository.findById(offer.getId()).get();
        // Disconnect from session so that the updates on updatedOffer are not directly saved in db
        em.detach(updatedOffer);
        updatedOffer
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .url(UPDATED_URL);
        OfferDTO offerDTO = offerMapper.toDto(updatedOffer);

        restOfferMockMvc.perform(put("/api/offers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
            .andExpect(status().isOk());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeUpdate);
        Offer testOffer = offerList.get(offerList.size() - 1);
        assertThat(testOffer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOffer.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testOffer.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingOffer() throws Exception {
        int databaseSizeBeforeUpdate = offerRepository.findAll().size();

        // Create the Offer
        OfferDTO offerDTO = offerMapper.toDto(offer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferMockMvc.perform(put("/api/offers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOffer() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        int databaseSizeBeforeDelete = offerRepository.findAll().size();

        // Delete the offer
        restOfferMockMvc.perform(delete("/api/offers/{id}", offer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
