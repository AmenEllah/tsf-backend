package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.AppSettings;
import com.attijari.repository.AppSettingsRepository;
import com.attijari.service.AppSettingsService;
import com.attijari.service.AppSettingsQueryService;

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

import com.attijari.domain.enumeration.SenderProvider;
import com.attijari.domain.enumeration.SignerProvider;
/**
 * Integration tests for the {@link AppSettingsResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AppSettingsResourceIT {

    private static final SenderProvider DEFAULT_MAIL_SENDER_PROVIDER = SenderProvider.MIDDLEWARE;
    private static final SenderProvider UPDATED_MAIL_SENDER_PROVIDER = SenderProvider.SEND_GRID;

    private static final SignerProvider DEFAULT_SIGNER_PROVIDER = SignerProvider.NG_SIGN;
    private static final SignerProvider UPDATED_SIGNER_PROVIDER = SignerProvider.SIGN_ATTIJARI;

    @Autowired
    private AppSettingsRepository appSettingsRepository;

    @Autowired
    private AppSettingsService appSettingsService;

    @Autowired
    private AppSettingsQueryService appSettingsQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppSettingsMockMvc;

    private AppSettings appSettings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppSettings createEntity(EntityManager em) {
        AppSettings appSettings = new AppSettings()
            .mailSenderProvider(DEFAULT_MAIL_SENDER_PROVIDER)
            .signerProvider(DEFAULT_SIGNER_PROVIDER);
        return appSettings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppSettings createUpdatedEntity(EntityManager em) {
        AppSettings appSettings = new AppSettings()
            .mailSenderProvider(UPDATED_MAIL_SENDER_PROVIDER)
            .signerProvider(UPDATED_SIGNER_PROVIDER);
        return appSettings;
    }

    @BeforeEach
    public void initTest() {
        appSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppSettings() throws Exception {
        int databaseSizeBeforeCreate = appSettingsRepository.findAll().size();
        // Create the AppSettings
        restAppSettingsMockMvc.perform(post("/api/app-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appSettings)))
            .andExpect(status().isCreated());

        // Validate the AppSettings in the database
        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
        assertThat(appSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        AppSettings testAppSettings = appSettingsList.get(appSettingsList.size() - 1);
        assertThat(testAppSettings.getMailSenderProvider()).isEqualTo(DEFAULT_MAIL_SENDER_PROVIDER);
        assertThat(testAppSettings.getSignerProvider()).isEqualTo(DEFAULT_SIGNER_PROVIDER);
    }

    @Test
    @Transactional
    public void createAppSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appSettingsRepository.findAll().size();

        // Create the AppSettings with an existing ID
        appSettings.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppSettingsMockMvc.perform(post("/api/app-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appSettings)))
            .andExpect(status().isBadRequest());

        // Validate the AppSettings in the database
        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
        assertThat(appSettingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMailSenderProviderIsRequired() throws Exception {
        int databaseSizeBeforeTest = appSettingsRepository.findAll().size();
        // set the field null
        appSettings.setMailSenderProvider(null);

        // Create the AppSettings, which fails.


        restAppSettingsMockMvc.perform(post("/api/app-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appSettings)))
            .andExpect(status().isBadRequest());

        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
        assertThat(appSettingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSignerProviderIsRequired() throws Exception {
        int databaseSizeBeforeTest = appSettingsRepository.findAll().size();
        // set the field null
        appSettings.setSignerProvider(null);

        // Create the AppSettings, which fails.


        restAppSettingsMockMvc.perform(post("/api/app-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appSettings)))
            .andExpect(status().isBadRequest());

        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
        assertThat(appSettingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAppSettings() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList
        restAppSettingsMockMvc.perform(get("/api/app-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].mailSenderProvider").value(hasItem(DEFAULT_MAIL_SENDER_PROVIDER.toString())))
            .andExpect(jsonPath("$.[*].signerProvider").value(hasItem(DEFAULT_SIGNER_PROVIDER.toString())));
    }

    @Test
    @Transactional
    public void getAppSettings() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get the appSettings
        restAppSettingsMockMvc.perform(get("/api/app-settings/{id}", appSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appSettings.getId().intValue()))
            .andExpect(jsonPath("$.mailSenderProvider").value(DEFAULT_MAIL_SENDER_PROVIDER.toString()))
            .andExpect(jsonPath("$.signerProvider").value(DEFAULT_SIGNER_PROVIDER.toString()));
    }


    @Test
    @Transactional
    public void getAppSettingsByIdFiltering() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        Long id = appSettings.getId();

        defaultAppSettingsShouldBeFound("id.equals=" + id);
        defaultAppSettingsShouldNotBeFound("id.notEquals=" + id);

        defaultAppSettingsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAppSettingsShouldNotBeFound("id.greaterThan=" + id);

        defaultAppSettingsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAppSettingsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllAppSettingsByMailSenderProviderIsEqualToSomething() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where mailSenderProvider equals to DEFAULT_MAIL_SENDER_PROVIDER
        defaultAppSettingsShouldBeFound("mailSenderProvider.equals=" + DEFAULT_MAIL_SENDER_PROVIDER);

        // Get all the appSettingsList where mailSenderProvider equals to UPDATED_MAIL_SENDER_PROVIDER
        defaultAppSettingsShouldNotBeFound("mailSenderProvider.equals=" + UPDATED_MAIL_SENDER_PROVIDER);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByMailSenderProviderIsNotEqualToSomething() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where mailSenderProvider not equals to DEFAULT_MAIL_SENDER_PROVIDER
        defaultAppSettingsShouldNotBeFound("mailSenderProvider.notEquals=" + DEFAULT_MAIL_SENDER_PROVIDER);

        // Get all the appSettingsList where mailSenderProvider not equals to UPDATED_MAIL_SENDER_PROVIDER
        defaultAppSettingsShouldBeFound("mailSenderProvider.notEquals=" + UPDATED_MAIL_SENDER_PROVIDER);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByMailSenderProviderIsInShouldWork() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where mailSenderProvider in DEFAULT_MAIL_SENDER_PROVIDER or UPDATED_MAIL_SENDER_PROVIDER
        defaultAppSettingsShouldBeFound("mailSenderProvider.in=" + DEFAULT_MAIL_SENDER_PROVIDER + "," + UPDATED_MAIL_SENDER_PROVIDER);

        // Get all the appSettingsList where mailSenderProvider equals to UPDATED_MAIL_SENDER_PROVIDER
        defaultAppSettingsShouldNotBeFound("mailSenderProvider.in=" + UPDATED_MAIL_SENDER_PROVIDER);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByMailSenderProviderIsNullOrNotNull() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where mailSenderProvider is not null
        defaultAppSettingsShouldBeFound("mailSenderProvider.specified=true");

        // Get all the appSettingsList where mailSenderProvider is null
        defaultAppSettingsShouldNotBeFound("mailSenderProvider.specified=false");
    }

    @Test
    @Transactional
    public void getAllAppSettingsBySignerProviderIsEqualToSomething() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where signerProvider equals to DEFAULT_SIGNER_PROVIDER
        defaultAppSettingsShouldBeFound("signerProvider.equals=" + DEFAULT_SIGNER_PROVIDER);

        // Get all the appSettingsList where signerProvider equals to UPDATED_SIGNER_PROVIDER
        defaultAppSettingsShouldNotBeFound("signerProvider.equals=" + UPDATED_SIGNER_PROVIDER);
    }

    @Test
    @Transactional
    public void getAllAppSettingsBySignerProviderIsNotEqualToSomething() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where signerProvider not equals to DEFAULT_SIGNER_PROVIDER
        defaultAppSettingsShouldNotBeFound("signerProvider.notEquals=" + DEFAULT_SIGNER_PROVIDER);

        // Get all the appSettingsList where signerProvider not equals to UPDATED_SIGNER_PROVIDER
        defaultAppSettingsShouldBeFound("signerProvider.notEquals=" + UPDATED_SIGNER_PROVIDER);
    }

    @Test
    @Transactional
    public void getAllAppSettingsBySignerProviderIsInShouldWork() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where signerProvider in DEFAULT_SIGNER_PROVIDER or UPDATED_SIGNER_PROVIDER
        defaultAppSettingsShouldBeFound("signerProvider.in=" + DEFAULT_SIGNER_PROVIDER + "," + UPDATED_SIGNER_PROVIDER);

        // Get all the appSettingsList where signerProvider equals to UPDATED_SIGNER_PROVIDER
        defaultAppSettingsShouldNotBeFound("signerProvider.in=" + UPDATED_SIGNER_PROVIDER);
    }

    @Test
    @Transactional
    public void getAllAppSettingsBySignerProviderIsNullOrNotNull() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where signerProvider is not null
        defaultAppSettingsShouldBeFound("signerProvider.specified=true");

        // Get all the appSettingsList where signerProvider is null
        defaultAppSettingsShouldNotBeFound("signerProvider.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAppSettingsShouldBeFound(String filter) throws Exception {
        restAppSettingsMockMvc.perform(get("/api/app-settings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].mailSenderProvider").value(hasItem(DEFAULT_MAIL_SENDER_PROVIDER.toString())))
            .andExpect(jsonPath("$.[*].signerProvider").value(hasItem(DEFAULT_SIGNER_PROVIDER.toString())));

        // Check, that the count call also returns 1
        restAppSettingsMockMvc.perform(get("/api/app-settings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAppSettingsShouldNotBeFound(String filter) throws Exception {
        restAppSettingsMockMvc.perform(get("/api/app-settings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAppSettingsMockMvc.perform(get("/api/app-settings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingAppSettings() throws Exception {
        // Get the appSettings
        restAppSettingsMockMvc.perform(get("/api/app-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppSettings() throws Exception {
        // Initialize the database
        appSettingsService.save(appSettings);

        int databaseSizeBeforeUpdate = appSettingsRepository.findAll().size();

        // Update the appSettings
        AppSettings updatedAppSettings = appSettingsRepository.findById(appSettings.getId()).get();
        // Disconnect from session so that the updates on updatedAppSettings are not directly saved in db
        em.detach(updatedAppSettings);
        updatedAppSettings
            .mailSenderProvider(UPDATED_MAIL_SENDER_PROVIDER)
            .signerProvider(UPDATED_SIGNER_PROVIDER);

        restAppSettingsMockMvc.perform(put("/api/app-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAppSettings)))
            .andExpect(status().isOk());

        // Validate the AppSettings in the database
        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
        assertThat(appSettingsList).hasSize(databaseSizeBeforeUpdate);
        AppSettings testAppSettings = appSettingsList.get(appSettingsList.size() - 1);
        assertThat(testAppSettings.getMailSenderProvider()).isEqualTo(UPDATED_MAIL_SENDER_PROVIDER);
        assertThat(testAppSettings.getSignerProvider()).isEqualTo(UPDATED_SIGNER_PROVIDER);
    }

    @Test
    @Transactional
    public void updateNonExistingAppSettings() throws Exception {
        int databaseSizeBeforeUpdate = appSettingsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppSettingsMockMvc.perform(put("/api/app-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appSettings)))
            .andExpect(status().isBadRequest());

        // Validate the AppSettings in the database
        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
        assertThat(appSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppSettings() throws Exception {
        // Initialize the database
        appSettingsService.save(appSettings);

        int databaseSizeBeforeDelete = appSettingsRepository.findAll().size();

        // Delete the appSettings
        restAppSettingsMockMvc.perform(delete("/api/app-settings/{id}", appSettings.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
        assertThat(appSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
