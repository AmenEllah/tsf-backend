package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.FAQ;
import com.attijari.repository.FAQRepository;
import com.attijari.service.FAQService;
import com.attijari.service.dto.FAQDTO;
import com.attijari.service.mapper.FAQMapper;
import com.attijari.service.FAQQueryService;

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
 * Integration tests for the {@link FAQResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FAQResourceIT {

    private static final String DEFAULT_QUESTION = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION = "BBBBBBBBBB";

    private static final String DEFAULT_ANSWER = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER = "BBBBBBBBBB";

    @Autowired
    private FAQRepository fAQRepository;

    @Autowired
    private FAQMapper fAQMapper;

    @Autowired
    private FAQService fAQService;

    @Autowired
    private FAQQueryService fAQQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFAQMockMvc;

    private FAQ fAQ;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FAQ createEntity(EntityManager em) {
        FAQ fAQ = new FAQ()
            .question(DEFAULT_QUESTION)
            .answer(DEFAULT_ANSWER);
        return fAQ;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FAQ createUpdatedEntity(EntityManager em) {
        FAQ fAQ = new FAQ()
            .question(UPDATED_QUESTION)
            .answer(UPDATED_ANSWER);
        return fAQ;
    }

    @BeforeEach
    public void initTest() {
        fAQ = createEntity(em);
    }

    @Test
    @Transactional
    public void createFAQ() throws Exception {
        int databaseSizeBeforeCreate = fAQRepository.findAll().size();
        // Create the FAQ
        FAQDTO fAQDTO = fAQMapper.toDto(fAQ);
        restFAQMockMvc.perform(post("/api/faqs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fAQDTO)))
            .andExpect(status().isCreated());

        // Validate the FAQ in the database
        List<FAQ> fAQList = fAQRepository.findAll();
        assertThat(fAQList).hasSize(databaseSizeBeforeCreate + 1);
        FAQ testFAQ = fAQList.get(fAQList.size() - 1);
        assertThat(testFAQ.getQuestion()).isEqualTo(DEFAULT_QUESTION);
        assertThat(testFAQ.getAnswer()).isEqualTo(DEFAULT_ANSWER);
    }

    @Test
    @Transactional
    public void createFAQWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fAQRepository.findAll().size();

        // Create the FAQ with an existing ID
        fAQ.setId(1L);
        FAQDTO fAQDTO = fAQMapper.toDto(fAQ);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFAQMockMvc.perform(post("/api/faqs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fAQDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FAQ in the database
        List<FAQ> fAQList = fAQRepository.findAll();
        assertThat(fAQList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFAQS() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList
        restFAQMockMvc.perform(get("/api/faqs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fAQ.getId().intValue())))
            .andExpect(jsonPath("$.[*].question").value(hasItem(DEFAULT_QUESTION)))
            .andExpect(jsonPath("$.[*].answer").value(hasItem(DEFAULT_ANSWER)));
    }

    @Test
    @Transactional
    public void getFAQ() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get the fAQ
        restFAQMockMvc.perform(get("/api/faqs/{id}", fAQ.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fAQ.getId().intValue()))
            .andExpect(jsonPath("$.question").value(DEFAULT_QUESTION))
            .andExpect(jsonPath("$.answer").value(DEFAULT_ANSWER));
    }


    @Test
    @Transactional
    public void getFAQSByIdFiltering() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        Long id = fAQ.getId();

        defaultFAQShouldBeFound("id.equals=" + id);
        defaultFAQShouldNotBeFound("id.notEquals=" + id);

        defaultFAQShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFAQShouldNotBeFound("id.greaterThan=" + id);

        defaultFAQShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFAQShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFAQSByQuestionIsEqualToSomething() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where question equals to DEFAULT_QUESTION
        defaultFAQShouldBeFound("question.equals=" + DEFAULT_QUESTION);

        // Get all the fAQList where question equals to UPDATED_QUESTION
        defaultFAQShouldNotBeFound("question.equals=" + UPDATED_QUESTION);
    }

    @Test
    @Transactional
    public void getAllFAQSByQuestionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where question not equals to DEFAULT_QUESTION
        defaultFAQShouldNotBeFound("question.notEquals=" + DEFAULT_QUESTION);

        // Get all the fAQList where question not equals to UPDATED_QUESTION
        defaultFAQShouldBeFound("question.notEquals=" + UPDATED_QUESTION);
    }

    @Test
    @Transactional
    public void getAllFAQSByQuestionIsInShouldWork() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where question in DEFAULT_QUESTION or UPDATED_QUESTION
        defaultFAQShouldBeFound("question.in=" + DEFAULT_QUESTION + "," + UPDATED_QUESTION);

        // Get all the fAQList where question equals to UPDATED_QUESTION
        defaultFAQShouldNotBeFound("question.in=" + UPDATED_QUESTION);
    }

    @Test
    @Transactional
    public void getAllFAQSByQuestionIsNullOrNotNull() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where question is not null
        defaultFAQShouldBeFound("question.specified=true");

        // Get all the fAQList where question is null
        defaultFAQShouldNotBeFound("question.specified=false");
    }
                @Test
    @Transactional
    public void getAllFAQSByQuestionContainsSomething() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where question contains DEFAULT_QUESTION
        defaultFAQShouldBeFound("question.contains=" + DEFAULT_QUESTION);

        // Get all the fAQList where question contains UPDATED_QUESTION
        defaultFAQShouldNotBeFound("question.contains=" + UPDATED_QUESTION);
    }

    @Test
    @Transactional
    public void getAllFAQSByQuestionNotContainsSomething() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where question does not contain DEFAULT_QUESTION
        defaultFAQShouldNotBeFound("question.doesNotContain=" + DEFAULT_QUESTION);

        // Get all the fAQList where question does not contain UPDATED_QUESTION
        defaultFAQShouldBeFound("question.doesNotContain=" + UPDATED_QUESTION);
    }


    @Test
    @Transactional
    public void getAllFAQSByAnswerIsEqualToSomething() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where answer equals to DEFAULT_ANSWER
        defaultFAQShouldBeFound("answer.equals=" + DEFAULT_ANSWER);

        // Get all the fAQList where answer equals to UPDATED_ANSWER
        defaultFAQShouldNotBeFound("answer.equals=" + UPDATED_ANSWER);
    }

    @Test
    @Transactional
    public void getAllFAQSByAnswerIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where answer not equals to DEFAULT_ANSWER
        defaultFAQShouldNotBeFound("answer.notEquals=" + DEFAULT_ANSWER);

        // Get all the fAQList where answer not equals to UPDATED_ANSWER
        defaultFAQShouldBeFound("answer.notEquals=" + UPDATED_ANSWER);
    }

    @Test
    @Transactional
    public void getAllFAQSByAnswerIsInShouldWork() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where answer in DEFAULT_ANSWER or UPDATED_ANSWER
        defaultFAQShouldBeFound("answer.in=" + DEFAULT_ANSWER + "," + UPDATED_ANSWER);

        // Get all the fAQList where answer equals to UPDATED_ANSWER
        defaultFAQShouldNotBeFound("answer.in=" + UPDATED_ANSWER);
    }

    @Test
    @Transactional
    public void getAllFAQSByAnswerIsNullOrNotNull() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where answer is not null
        defaultFAQShouldBeFound("answer.specified=true");

        // Get all the fAQList where answer is null
        defaultFAQShouldNotBeFound("answer.specified=false");
    }
                @Test
    @Transactional
    public void getAllFAQSByAnswerContainsSomething() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where answer contains DEFAULT_ANSWER
        defaultFAQShouldBeFound("answer.contains=" + DEFAULT_ANSWER);

        // Get all the fAQList where answer contains UPDATED_ANSWER
        defaultFAQShouldNotBeFound("answer.contains=" + UPDATED_ANSWER);
    }

    @Test
    @Transactional
    public void getAllFAQSByAnswerNotContainsSomething() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        // Get all the fAQList where answer does not contain DEFAULT_ANSWER
        defaultFAQShouldNotBeFound("answer.doesNotContain=" + DEFAULT_ANSWER);

        // Get all the fAQList where answer does not contain UPDATED_ANSWER
        defaultFAQShouldBeFound("answer.doesNotContain=" + UPDATED_ANSWER);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFAQShouldBeFound(String filter) throws Exception {
        restFAQMockMvc.perform(get("/api/faqs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fAQ.getId().intValue())))
            .andExpect(jsonPath("$.[*].question").value(hasItem(DEFAULT_QUESTION)))
            .andExpect(jsonPath("$.[*].answer").value(hasItem(DEFAULT_ANSWER)));

        // Check, that the count call also returns 1
        restFAQMockMvc.perform(get("/api/faqs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFAQShouldNotBeFound(String filter) throws Exception {
        restFAQMockMvc.perform(get("/api/faqs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFAQMockMvc.perform(get("/api/faqs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFAQ() throws Exception {
        // Get the fAQ
        restFAQMockMvc.perform(get("/api/faqs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFAQ() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        int databaseSizeBeforeUpdate = fAQRepository.findAll().size();

        // Update the fAQ
        FAQ updatedFAQ = fAQRepository.findById(fAQ.getId()).get();
        // Disconnect from session so that the updates on updatedFAQ are not directly saved in db
        em.detach(updatedFAQ);
        updatedFAQ
            .question(UPDATED_QUESTION)
            .answer(UPDATED_ANSWER);
        FAQDTO fAQDTO = fAQMapper.toDto(updatedFAQ);

        restFAQMockMvc.perform(put("/api/faqs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fAQDTO)))
            .andExpect(status().isOk());

        // Validate the FAQ in the database
        List<FAQ> fAQList = fAQRepository.findAll();
        assertThat(fAQList).hasSize(databaseSizeBeforeUpdate);
        FAQ testFAQ = fAQList.get(fAQList.size() - 1);
        assertThat(testFAQ.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testFAQ.getAnswer()).isEqualTo(UPDATED_ANSWER);
    }

    @Test
    @Transactional
    public void updateNonExistingFAQ() throws Exception {
        int databaseSizeBeforeUpdate = fAQRepository.findAll().size();

        // Create the FAQ
        FAQDTO fAQDTO = fAQMapper.toDto(fAQ);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFAQMockMvc.perform(put("/api/faqs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fAQDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FAQ in the database
        List<FAQ> fAQList = fAQRepository.findAll();
        assertThat(fAQList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFAQ() throws Exception {
        // Initialize the database
        fAQRepository.saveAndFlush(fAQ);

        int databaseSizeBeforeDelete = fAQRepository.findAll().size();

        // Delete the fAQ
        restFAQMockMvc.perform(delete("/api/faqs/{id}", fAQ.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FAQ> fAQList = fAQRepository.findAll();
        assertThat(fAQList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
