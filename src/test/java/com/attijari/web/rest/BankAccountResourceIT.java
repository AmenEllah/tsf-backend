package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.BankAccount;
import com.attijari.repository.BankAccountRepository;
import com.attijari.service.BankAccountService;
import com.attijari.service.dto.BankAccountDTO;
import com.attijari.service.mapper.BankAccountMapper;
import com.attijari.service.BankAccountQueryService;

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
 * Integration tests for the {@link BankAccountResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BankAccountResourceIT {

    private static final String DEFAULT_LIBELLE_FR = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_FR = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_EN = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_EN = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_FR = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_EN = "BBBBBBBBBB";

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountMapper bankAccountMapper;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountQueryService bankAccountQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBankAccountMockMvc;

    private BankAccount bankAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankAccount createEntity(EntityManager em) {
        BankAccount bankAccount = new BankAccount()
            .libelleFR(DEFAULT_LIBELLE_FR)
            .libelleEN(DEFAULT_LIBELLE_EN)
            .descriptionFR(DEFAULT_DESCRIPTION_FR)
            .descriptionEN(DEFAULT_DESCRIPTION_EN);
        return bankAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankAccount createUpdatedEntity(EntityManager em) {
        BankAccount bankAccount = new BankAccount()
            .libelleFR(UPDATED_LIBELLE_FR)
            .libelleEN(UPDATED_LIBELLE_EN)
            .descriptionFR(UPDATED_DESCRIPTION_FR)
            .descriptionEN(UPDATED_DESCRIPTION_EN);
        return bankAccount;
    }

    @BeforeEach
    public void initTest() {
        bankAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createBankAccount() throws Exception {
        int databaseSizeBeforeCreate = bankAccountRepository.findAll().size();
        // Create the BankAccount
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(bankAccount);
        restBankAccountMockMvc.perform(post("/api/bank-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the BankAccount in the database
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeCreate + 1);
        BankAccount testBankAccount = bankAccountList.get(bankAccountList.size() - 1);
        assertThat(testBankAccount.getLibelleFR()).isEqualTo(DEFAULT_LIBELLE_FR);
        assertThat(testBankAccount.getLibelleEN()).isEqualTo(DEFAULT_LIBELLE_EN);
        assertThat(testBankAccount.getDescriptionFR()).isEqualTo(DEFAULT_DESCRIPTION_FR);
        assertThat(testBankAccount.getDescriptionEN()).isEqualTo(DEFAULT_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void createBankAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankAccountRepository.findAll().size();

        // Create the BankAccount with an existing ID
        bankAccount.setId(1L);
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(bankAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankAccountMockMvc.perform(post("/api/bank-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BankAccount in the database
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBankAccounts() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList
        restBankAccountMockMvc.perform(get("/api/bank-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleFR").value(hasItem(DEFAULT_LIBELLE_FR)))
            .andExpect(jsonPath("$.[*].libelleEN").value(hasItem(DEFAULT_LIBELLE_EN)))
            .andExpect(jsonPath("$.[*].descriptionFR").value(hasItem(DEFAULT_DESCRIPTION_FR)))
            .andExpect(jsonPath("$.[*].descriptionEN").value(hasItem(DEFAULT_DESCRIPTION_EN)));
    }

    @Test
    @Transactional
    public void getBankAccount() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get the bankAccount
        restBankAccountMockMvc.perform(get("/api/bank-accounts/{id}", bankAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bankAccount.getId().intValue()))
            .andExpect(jsonPath("$.libelleFR").value(DEFAULT_LIBELLE_FR))
            .andExpect(jsonPath("$.libelleEN").value(DEFAULT_LIBELLE_EN))
            .andExpect(jsonPath("$.descriptionFR").value(DEFAULT_DESCRIPTION_FR))
            .andExpect(jsonPath("$.descriptionEN").value(DEFAULT_DESCRIPTION_EN));
    }


    @Test
    @Transactional
    public void getBankAccountsByIdFiltering() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        Long id = bankAccount.getId();

        defaultBankAccountShouldBeFound("id.equals=" + id);
        defaultBankAccountShouldNotBeFound("id.notEquals=" + id);

        defaultBankAccountShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBankAccountShouldNotBeFound("id.greaterThan=" + id);

        defaultBankAccountShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBankAccountShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllBankAccountsByLibelleFRIsEqualToSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleFR equals to DEFAULT_LIBELLE_FR
        defaultBankAccountShouldBeFound("libelleFR.equals=" + DEFAULT_LIBELLE_FR);

        // Get all the bankAccountList where libelleFR equals to UPDATED_LIBELLE_FR
        defaultBankAccountShouldNotBeFound("libelleFR.equals=" + UPDATED_LIBELLE_FR);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByLibelleFRIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleFR not equals to DEFAULT_LIBELLE_FR
        defaultBankAccountShouldNotBeFound("libelleFR.notEquals=" + DEFAULT_LIBELLE_FR);

        // Get all the bankAccountList where libelleFR not equals to UPDATED_LIBELLE_FR
        defaultBankAccountShouldBeFound("libelleFR.notEquals=" + UPDATED_LIBELLE_FR);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByLibelleFRIsInShouldWork() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleFR in DEFAULT_LIBELLE_FR or UPDATED_LIBELLE_FR
        defaultBankAccountShouldBeFound("libelleFR.in=" + DEFAULT_LIBELLE_FR + "," + UPDATED_LIBELLE_FR);

        // Get all the bankAccountList where libelleFR equals to UPDATED_LIBELLE_FR
        defaultBankAccountShouldNotBeFound("libelleFR.in=" + UPDATED_LIBELLE_FR);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByLibelleFRIsNullOrNotNull() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleFR is not null
        defaultBankAccountShouldBeFound("libelleFR.specified=true");

        // Get all the bankAccountList where libelleFR is null
        defaultBankAccountShouldNotBeFound("libelleFR.specified=false");
    }
                @Test
    @Transactional
    public void getAllBankAccountsByLibelleFRContainsSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleFR contains DEFAULT_LIBELLE_FR
        defaultBankAccountShouldBeFound("libelleFR.contains=" + DEFAULT_LIBELLE_FR);

        // Get all the bankAccountList where libelleFR contains UPDATED_LIBELLE_FR
        defaultBankAccountShouldNotBeFound("libelleFR.contains=" + UPDATED_LIBELLE_FR);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByLibelleFRNotContainsSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleFR does not contain DEFAULT_LIBELLE_FR
        defaultBankAccountShouldNotBeFound("libelleFR.doesNotContain=" + DEFAULT_LIBELLE_FR);

        // Get all the bankAccountList where libelleFR does not contain UPDATED_LIBELLE_FR
        defaultBankAccountShouldBeFound("libelleFR.doesNotContain=" + UPDATED_LIBELLE_FR);
    }


    @Test
    @Transactional
    public void getAllBankAccountsByLibelleENIsEqualToSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleEN equals to DEFAULT_LIBELLE_EN
        defaultBankAccountShouldBeFound("libelleEN.equals=" + DEFAULT_LIBELLE_EN);

        // Get all the bankAccountList where libelleEN equals to UPDATED_LIBELLE_EN
        defaultBankAccountShouldNotBeFound("libelleEN.equals=" + UPDATED_LIBELLE_EN);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByLibelleENIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleEN not equals to DEFAULT_LIBELLE_EN
        defaultBankAccountShouldNotBeFound("libelleEN.notEquals=" + DEFAULT_LIBELLE_EN);

        // Get all the bankAccountList where libelleEN not equals to UPDATED_LIBELLE_EN
        defaultBankAccountShouldBeFound("libelleEN.notEquals=" + UPDATED_LIBELLE_EN);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByLibelleENIsInShouldWork() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleEN in DEFAULT_LIBELLE_EN or UPDATED_LIBELLE_EN
        defaultBankAccountShouldBeFound("libelleEN.in=" + DEFAULT_LIBELLE_EN + "," + UPDATED_LIBELLE_EN);

        // Get all the bankAccountList where libelleEN equals to UPDATED_LIBELLE_EN
        defaultBankAccountShouldNotBeFound("libelleEN.in=" + UPDATED_LIBELLE_EN);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByLibelleENIsNullOrNotNull() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleEN is not null
        defaultBankAccountShouldBeFound("libelleEN.specified=true");

        // Get all the bankAccountList where libelleEN is null
        defaultBankAccountShouldNotBeFound("libelleEN.specified=false");
    }
                @Test
    @Transactional
    public void getAllBankAccountsByLibelleENContainsSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleEN contains DEFAULT_LIBELLE_EN
        defaultBankAccountShouldBeFound("libelleEN.contains=" + DEFAULT_LIBELLE_EN);

        // Get all the bankAccountList where libelleEN contains UPDATED_LIBELLE_EN
        defaultBankAccountShouldNotBeFound("libelleEN.contains=" + UPDATED_LIBELLE_EN);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByLibelleENNotContainsSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where libelleEN does not contain DEFAULT_LIBELLE_EN
        defaultBankAccountShouldNotBeFound("libelleEN.doesNotContain=" + DEFAULT_LIBELLE_EN);

        // Get all the bankAccountList where libelleEN does not contain UPDATED_LIBELLE_EN
        defaultBankAccountShouldBeFound("libelleEN.doesNotContain=" + UPDATED_LIBELLE_EN);
    }


    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionFRIsEqualToSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionFR equals to DEFAULT_DESCRIPTION_FR
        defaultBankAccountShouldBeFound("descriptionFR.equals=" + DEFAULT_DESCRIPTION_FR);

        // Get all the bankAccountList where descriptionFR equals to UPDATED_DESCRIPTION_FR
        defaultBankAccountShouldNotBeFound("descriptionFR.equals=" + UPDATED_DESCRIPTION_FR);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionFRIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionFR not equals to DEFAULT_DESCRIPTION_FR
        defaultBankAccountShouldNotBeFound("descriptionFR.notEquals=" + DEFAULT_DESCRIPTION_FR);

        // Get all the bankAccountList where descriptionFR not equals to UPDATED_DESCRIPTION_FR
        defaultBankAccountShouldBeFound("descriptionFR.notEquals=" + UPDATED_DESCRIPTION_FR);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionFRIsInShouldWork() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionFR in DEFAULT_DESCRIPTION_FR or UPDATED_DESCRIPTION_FR
        defaultBankAccountShouldBeFound("descriptionFR.in=" + DEFAULT_DESCRIPTION_FR + "," + UPDATED_DESCRIPTION_FR);

        // Get all the bankAccountList where descriptionFR equals to UPDATED_DESCRIPTION_FR
        defaultBankAccountShouldNotBeFound("descriptionFR.in=" + UPDATED_DESCRIPTION_FR);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionFRIsNullOrNotNull() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionFR is not null
        defaultBankAccountShouldBeFound("descriptionFR.specified=true");

        // Get all the bankAccountList where descriptionFR is null
        defaultBankAccountShouldNotBeFound("descriptionFR.specified=false");
    }
                @Test
    @Transactional
    public void getAllBankAccountsByDescriptionFRContainsSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionFR contains DEFAULT_DESCRIPTION_FR
        defaultBankAccountShouldBeFound("descriptionFR.contains=" + DEFAULT_DESCRIPTION_FR);

        // Get all the bankAccountList where descriptionFR contains UPDATED_DESCRIPTION_FR
        defaultBankAccountShouldNotBeFound("descriptionFR.contains=" + UPDATED_DESCRIPTION_FR);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionFRNotContainsSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionFR does not contain DEFAULT_DESCRIPTION_FR
        defaultBankAccountShouldNotBeFound("descriptionFR.doesNotContain=" + DEFAULT_DESCRIPTION_FR);

        // Get all the bankAccountList where descriptionFR does not contain UPDATED_DESCRIPTION_FR
        defaultBankAccountShouldBeFound("descriptionFR.doesNotContain=" + UPDATED_DESCRIPTION_FR);
    }


    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionENIsEqualToSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionEN equals to DEFAULT_DESCRIPTION_EN
        defaultBankAccountShouldBeFound("descriptionEN.equals=" + DEFAULT_DESCRIPTION_EN);

        // Get all the bankAccountList where descriptionEN equals to UPDATED_DESCRIPTION_EN
        defaultBankAccountShouldNotBeFound("descriptionEN.equals=" + UPDATED_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionENIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionEN not equals to DEFAULT_DESCRIPTION_EN
        defaultBankAccountShouldNotBeFound("descriptionEN.notEquals=" + DEFAULT_DESCRIPTION_EN);

        // Get all the bankAccountList where descriptionEN not equals to UPDATED_DESCRIPTION_EN
        defaultBankAccountShouldBeFound("descriptionEN.notEquals=" + UPDATED_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionENIsInShouldWork() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionEN in DEFAULT_DESCRIPTION_EN or UPDATED_DESCRIPTION_EN
        defaultBankAccountShouldBeFound("descriptionEN.in=" + DEFAULT_DESCRIPTION_EN + "," + UPDATED_DESCRIPTION_EN);

        // Get all the bankAccountList where descriptionEN equals to UPDATED_DESCRIPTION_EN
        defaultBankAccountShouldNotBeFound("descriptionEN.in=" + UPDATED_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionENIsNullOrNotNull() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionEN is not null
        defaultBankAccountShouldBeFound("descriptionEN.specified=true");

        // Get all the bankAccountList where descriptionEN is null
        defaultBankAccountShouldNotBeFound("descriptionEN.specified=false");
    }
                @Test
    @Transactional
    public void getAllBankAccountsByDescriptionENContainsSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionEN contains DEFAULT_DESCRIPTION_EN
        defaultBankAccountShouldBeFound("descriptionEN.contains=" + DEFAULT_DESCRIPTION_EN);

        // Get all the bankAccountList where descriptionEN contains UPDATED_DESCRIPTION_EN
        defaultBankAccountShouldNotBeFound("descriptionEN.contains=" + UPDATED_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void getAllBankAccountsByDescriptionENNotContainsSomething() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        // Get all the bankAccountList where descriptionEN does not contain DEFAULT_DESCRIPTION_EN
        defaultBankAccountShouldNotBeFound("descriptionEN.doesNotContain=" + DEFAULT_DESCRIPTION_EN);

        // Get all the bankAccountList where descriptionEN does not contain UPDATED_DESCRIPTION_EN
        defaultBankAccountShouldBeFound("descriptionEN.doesNotContain=" + UPDATED_DESCRIPTION_EN);
    }




    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBankAccountShouldBeFound(String filter) throws Exception {
        restBankAccountMockMvc.perform(get("/api/bank-accounts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleFR").value(hasItem(DEFAULT_LIBELLE_FR)))
            .andExpect(jsonPath("$.[*].libelleEN").value(hasItem(DEFAULT_LIBELLE_EN)))
            .andExpect(jsonPath("$.[*].descriptionFR").value(hasItem(DEFAULT_DESCRIPTION_FR)))
            .andExpect(jsonPath("$.[*].descriptionEN").value(hasItem(DEFAULT_DESCRIPTION_EN)));

        // Check, that the count call also returns 1
        restBankAccountMockMvc.perform(get("/api/bank-accounts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBankAccountShouldNotBeFound(String filter) throws Exception {
        restBankAccountMockMvc.perform(get("/api/bank-accounts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBankAccountMockMvc.perform(get("/api/bank-accounts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingBankAccount() throws Exception {
        // Get the bankAccount
        restBankAccountMockMvc.perform(get("/api/bank-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankAccount() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        int databaseSizeBeforeUpdate = bankAccountRepository.findAll().size();

        // Update the bankAccount
        BankAccount updatedBankAccount = bankAccountRepository.findById(bankAccount.getId()).get();
        // Disconnect from session so that the updates on updatedBankAccount are not directly saved in db
        em.detach(updatedBankAccount);
        updatedBankAccount
            .libelleFR(UPDATED_LIBELLE_FR)
            .libelleEN(UPDATED_LIBELLE_EN)
            .descriptionFR(UPDATED_DESCRIPTION_FR)
            .descriptionEN(UPDATED_DESCRIPTION_EN);
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(updatedBankAccount);

        restBankAccountMockMvc.perform(put("/api/bank-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isOk());

        // Validate the BankAccount in the database
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeUpdate);
        BankAccount testBankAccount = bankAccountList.get(bankAccountList.size() - 1);
        assertThat(testBankAccount.getLibelleFR()).isEqualTo(UPDATED_LIBELLE_FR);
        assertThat(testBankAccount.getLibelleEN()).isEqualTo(UPDATED_LIBELLE_EN);
        assertThat(testBankAccount.getDescriptionFR()).isEqualTo(UPDATED_DESCRIPTION_FR);
        assertThat(testBankAccount.getDescriptionEN()).isEqualTo(UPDATED_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void updateNonExistingBankAccount() throws Exception {
        int databaseSizeBeforeUpdate = bankAccountRepository.findAll().size();

        // Create the BankAccount
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(bankAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankAccountMockMvc.perform(put("/api/bank-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BankAccount in the database
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBankAccount() throws Exception {
        // Initialize the database
        bankAccountRepository.saveAndFlush(bankAccount);

        int databaseSizeBeforeDelete = bankAccountRepository.findAll().size();

        // Delete the bankAccount
        restBankAccountMockMvc.perform(delete("/api/bank-accounts/{id}", bankAccount.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
