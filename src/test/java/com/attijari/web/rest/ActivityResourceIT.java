package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.Activity;
import com.attijari.domain.FinancialInfo;
import com.attijari.repository.ActivityRepository;
import com.attijari.service.ActivityService;
import com.attijari.service.dto.ActivityDTO;
import com.attijari.service.mapper.ActivityMapper;
import com.attijari.service.ActivityQueryService;

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
 * Integration tests for the {@link ActivityResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ActivityResourceIT {

    private static final String DEFAULT_ACTIVITY_NAME_FR = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_NAME_FR = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITY_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_NAME_EN = "BBBBBBBBBB";

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityQueryService activityQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restActivityMockMvc;

    private Activity activity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Activity createEntity(EntityManager em) {
        Activity activity = new Activity()
            .activityNameFR(DEFAULT_ACTIVITY_NAME_FR)
            .activityNameEN(DEFAULT_ACTIVITY_NAME_EN);
        return activity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Activity createUpdatedEntity(EntityManager em) {
        Activity activity = new Activity()
            .activityNameFR(UPDATED_ACTIVITY_NAME_FR)
            .activityNameEN(UPDATED_ACTIVITY_NAME_EN);
        return activity;
    }

    @BeforeEach
    public void initTest() {
        activity = createEntity(em);
    }

    @Test
    @Transactional
    public void createActivity() throws Exception {
        int databaseSizeBeforeCreate = activityRepository.findAll().size();
        // Create the Activity
        ActivityDTO activityDTO = activityMapper.toDto(activity);
        restActivityMockMvc.perform(post("/api/activities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activityDTO)))
            .andExpect(status().isCreated());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeCreate + 1);
        Activity testActivity = activityList.get(activityList.size() - 1);
        assertThat(testActivity.getActivityNameFR()).isEqualTo(DEFAULT_ACTIVITY_NAME_FR);
        assertThat(testActivity.getActivityNameEN()).isEqualTo(DEFAULT_ACTIVITY_NAME_EN);
    }

    @Test
    @Transactional
    public void createActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activityRepository.findAll().size();

        // Create the Activity with an existing ID
        activity.setId(1L);
        ActivityDTO activityDTO = activityMapper.toDto(activity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivityMockMvc.perform(post("/api/activities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllActivities() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList
        restActivityMockMvc.perform(get("/api/activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activity.getId().intValue())))
            .andExpect(jsonPath("$.[*].activityNameFR").value(hasItem(DEFAULT_ACTIVITY_NAME_FR)))
            .andExpect(jsonPath("$.[*].activityNameEN").value(hasItem(DEFAULT_ACTIVITY_NAME_EN)));
    }

    @Test
    @Transactional
    public void getActivity() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get the activity
        restActivityMockMvc.perform(get("/api/activities/{id}", activity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(activity.getId().intValue()))
            .andExpect(jsonPath("$.activityNameFR").value(DEFAULT_ACTIVITY_NAME_FR))
            .andExpect(jsonPath("$.activityNameEN").value(DEFAULT_ACTIVITY_NAME_EN));
    }


    @Test
    @Transactional
    public void getActivitiesByIdFiltering() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        Long id = activity.getId();

        defaultActivityShouldBeFound("id.equals=" + id);
        defaultActivityShouldNotBeFound("id.notEquals=" + id);

        defaultActivityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultActivityShouldNotBeFound("id.greaterThan=" + id);

        defaultActivityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultActivityShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllActivitiesByActivityNameFRIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameFR equals to DEFAULT_ACTIVITY_NAME_FR
        defaultActivityShouldBeFound("activityNameFR.equals=" + DEFAULT_ACTIVITY_NAME_FR);

        // Get all the activityList where activityNameFR equals to UPDATED_ACTIVITY_NAME_FR
        defaultActivityShouldNotBeFound("activityNameFR.equals=" + UPDATED_ACTIVITY_NAME_FR);
    }

    @Test
    @Transactional
    public void getAllActivitiesByActivityNameFRIsNotEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameFR not equals to DEFAULT_ACTIVITY_NAME_FR
        defaultActivityShouldNotBeFound("activityNameFR.notEquals=" + DEFAULT_ACTIVITY_NAME_FR);

        // Get all the activityList where activityNameFR not equals to UPDATED_ACTIVITY_NAME_FR
        defaultActivityShouldBeFound("activityNameFR.notEquals=" + UPDATED_ACTIVITY_NAME_FR);
    }

    @Test
    @Transactional
    public void getAllActivitiesByActivityNameFRIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameFR in DEFAULT_ACTIVITY_NAME_FR or UPDATED_ACTIVITY_NAME_FR
        defaultActivityShouldBeFound("activityNameFR.in=" + DEFAULT_ACTIVITY_NAME_FR + "," + UPDATED_ACTIVITY_NAME_FR);

        // Get all the activityList where activityNameFR equals to UPDATED_ACTIVITY_NAME_FR
        defaultActivityShouldNotBeFound("activityNameFR.in=" + UPDATED_ACTIVITY_NAME_FR);
    }

    @Test
    @Transactional
    public void getAllActivitiesByActivityNameFRIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameFR is not null
        defaultActivityShouldBeFound("activityNameFR.specified=true");

        // Get all the activityList where activityNameFR is null
        defaultActivityShouldNotBeFound("activityNameFR.specified=false");
    }
                @Test
    @Transactional
    public void getAllActivitiesByActivityNameFRContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameFR contains DEFAULT_ACTIVITY_NAME_FR
        defaultActivityShouldBeFound("activityNameFR.contains=" + DEFAULT_ACTIVITY_NAME_FR);

        // Get all the activityList where activityNameFR contains UPDATED_ACTIVITY_NAME_FR
        defaultActivityShouldNotBeFound("activityNameFR.contains=" + UPDATED_ACTIVITY_NAME_FR);
    }

    @Test
    @Transactional
    public void getAllActivitiesByActivityNameFRNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameFR does not contain DEFAULT_ACTIVITY_NAME_FR
        defaultActivityShouldNotBeFound("activityNameFR.doesNotContain=" + DEFAULT_ACTIVITY_NAME_FR);

        // Get all the activityList where activityNameFR does not contain UPDATED_ACTIVITY_NAME_FR
        defaultActivityShouldBeFound("activityNameFR.doesNotContain=" + UPDATED_ACTIVITY_NAME_FR);
    }


    @Test
    @Transactional
    public void getAllActivitiesByActivityNameENIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameEN equals to DEFAULT_ACTIVITY_NAME_EN
        defaultActivityShouldBeFound("activityNameEN.equals=" + DEFAULT_ACTIVITY_NAME_EN);

        // Get all the activityList where activityNameEN equals to UPDATED_ACTIVITY_NAME_EN
        defaultActivityShouldNotBeFound("activityNameEN.equals=" + UPDATED_ACTIVITY_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllActivitiesByActivityNameENIsNotEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameEN not equals to DEFAULT_ACTIVITY_NAME_EN
        defaultActivityShouldNotBeFound("activityNameEN.notEquals=" + DEFAULT_ACTIVITY_NAME_EN);

        // Get all the activityList where activityNameEN not equals to UPDATED_ACTIVITY_NAME_EN
        defaultActivityShouldBeFound("activityNameEN.notEquals=" + UPDATED_ACTIVITY_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllActivitiesByActivityNameENIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameEN in DEFAULT_ACTIVITY_NAME_EN or UPDATED_ACTIVITY_NAME_EN
        defaultActivityShouldBeFound("activityNameEN.in=" + DEFAULT_ACTIVITY_NAME_EN + "," + UPDATED_ACTIVITY_NAME_EN);

        // Get all the activityList where activityNameEN equals to UPDATED_ACTIVITY_NAME_EN
        defaultActivityShouldNotBeFound("activityNameEN.in=" + UPDATED_ACTIVITY_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllActivitiesByActivityNameENIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameEN is not null
        defaultActivityShouldBeFound("activityNameEN.specified=true");

        // Get all the activityList where activityNameEN is null
        defaultActivityShouldNotBeFound("activityNameEN.specified=false");
    }
                @Test
    @Transactional
    public void getAllActivitiesByActivityNameENContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameEN contains DEFAULT_ACTIVITY_NAME_EN
        defaultActivityShouldBeFound("activityNameEN.contains=" + DEFAULT_ACTIVITY_NAME_EN);

        // Get all the activityList where activityNameEN contains UPDATED_ACTIVITY_NAME_EN
        defaultActivityShouldNotBeFound("activityNameEN.contains=" + UPDATED_ACTIVITY_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllActivitiesByActivityNameENNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityNameEN does not contain DEFAULT_ACTIVITY_NAME_EN
        defaultActivityShouldNotBeFound("activityNameEN.doesNotContain=" + DEFAULT_ACTIVITY_NAME_EN);

        // Get all the activityList where activityNameEN does not contain UPDATED_ACTIVITY_NAME_EN
        defaultActivityShouldBeFound("activityNameEN.doesNotContain=" + UPDATED_ACTIVITY_NAME_EN);
    }


    @Test
    @Transactional
    public void getAllActivitiesByFinancialInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);
        FinancialInfo financialInfo = FinancialInfoResourceIT.createEntity(em);
        em.persist(financialInfo);
        em.flush();
        activityRepository.saveAndFlush(activity);
        Long financialInfoId = financialInfo.getId();

        // Get all the activityList where financialInfo equals to financialInfoId
        defaultActivityShouldBeFound("financialInfoId.equals=" + financialInfoId);

        // Get all the activityList where financialInfo equals to financialInfoId + 1
        defaultActivityShouldNotBeFound("financialInfoId.equals=" + (financialInfoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultActivityShouldBeFound(String filter) throws Exception {
        restActivityMockMvc.perform(get("/api/activities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activity.getId().intValue())))
            .andExpect(jsonPath("$.[*].activityNameFR").value(hasItem(DEFAULT_ACTIVITY_NAME_FR)))
            .andExpect(jsonPath("$.[*].activityNameEN").value(hasItem(DEFAULT_ACTIVITY_NAME_EN)));

        // Check, that the count call also returns 1
        restActivityMockMvc.perform(get("/api/activities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultActivityShouldNotBeFound(String filter) throws Exception {
        restActivityMockMvc.perform(get("/api/activities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restActivityMockMvc.perform(get("/api/activities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingActivity() throws Exception {
        // Get the activity
        restActivityMockMvc.perform(get("/api/activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActivity() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        int databaseSizeBeforeUpdate = activityRepository.findAll().size();

        // Update the activity
        Activity updatedActivity = activityRepository.findById(activity.getId()).get();
        // Disconnect from session so that the updates on updatedActivity are not directly saved in db
        em.detach(updatedActivity);
        updatedActivity
            .activityNameFR(UPDATED_ACTIVITY_NAME_FR)
            .activityNameEN(UPDATED_ACTIVITY_NAME_EN);
        ActivityDTO activityDTO = activityMapper.toDto(updatedActivity);

        restActivityMockMvc.perform(put("/api/activities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activityDTO)))
            .andExpect(status().isOk());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
        Activity testActivity = activityList.get(activityList.size() - 1);
        assertThat(testActivity.getActivityNameFR()).isEqualTo(UPDATED_ACTIVITY_NAME_FR);
        assertThat(testActivity.getActivityNameEN()).isEqualTo(UPDATED_ACTIVITY_NAME_EN);
    }

    @Test
    @Transactional
    public void updateNonExistingActivity() throws Exception {
        int databaseSizeBeforeUpdate = activityRepository.findAll().size();

        // Create the Activity
        ActivityDTO activityDTO = activityMapper.toDto(activity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActivityMockMvc.perform(put("/api/activities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActivity() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        int databaseSizeBeforeDelete = activityRepository.findAll().size();

        // Delete the activity
        restActivityMockMvc.perform(delete("/api/activities/{id}", activity.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
