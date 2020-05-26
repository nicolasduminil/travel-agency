package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.TravelAgencyApp;
import fr.simplex_software.travel_agency.domain.Deal;
import fr.simplex_software.travel_agency.repository.DealRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DealResource} REST controller.
 */
@SpringBootTest(classes = TravelAgencyApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealResourceIT {

    private static final String DEFAULT_DEAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEAL_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DEAL_BOOK_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEAL_BOOK_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealMockMvc;

    private Deal deal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deal createEntity(EntityManager em) {
        Deal deal = new Deal()
            .dealName(DEFAULT_DEAL_NAME)
            .dealBookDate(DEFAULT_DEAL_BOOK_DATE);
        return deal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deal createUpdatedEntity(EntityManager em) {
        Deal deal = new Deal()
            .dealName(UPDATED_DEAL_NAME)
            .dealBookDate(UPDATED_DEAL_BOOK_DATE);
        return deal;
    }

    @BeforeEach
    public void initTest() {
        deal = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeal() throws Exception {
        int databaseSizeBeforeCreate = dealRepository.findAll().size();
        // Create the Deal
        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deal)))
            .andExpect(status().isCreated());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeCreate + 1);
        Deal testDeal = dealList.get(dealList.size() - 1);
        assertThat(testDeal.getDealName()).isEqualTo(DEFAULT_DEAL_NAME);
        assertThat(testDeal.getDealBookDate()).isEqualTo(DEFAULT_DEAL_BOOK_DATE);
    }

    @Test
    @Transactional
    public void createDealWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealRepository.findAll().size();

        // Create the Deal with an existing ID
        deal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deal)))
            .andExpect(status().isBadRequest());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDealNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealRepository.findAll().size();
        // set the field null
        deal.setDealName(null);

        // Create the Deal, which fails.


        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deal)))
            .andExpect(status().isBadRequest());

        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDealBookDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealRepository.findAll().size();
        // set the field null
        deal.setDealBookDate(null);

        // Create the Deal, which fails.


        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deal)))
            .andExpect(status().isBadRequest());

        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeals() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        // Get all the dealList
        restDealMockMvc.perform(get("/api/deals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deal.getId().intValue())))
            .andExpect(jsonPath("$.[*].dealName").value(hasItem(DEFAULT_DEAL_NAME)))
            .andExpect(jsonPath("$.[*].dealBookDate").value(hasItem(DEFAULT_DEAL_BOOK_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getDeal() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        // Get the deal
        restDealMockMvc.perform(get("/api/deals/{id}", deal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deal.getId().intValue()))
            .andExpect(jsonPath("$.dealName").value(DEFAULT_DEAL_NAME))
            .andExpect(jsonPath("$.dealBookDate").value(DEFAULT_DEAL_BOOK_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDeal() throws Exception {
        // Get the deal
        restDealMockMvc.perform(get("/api/deals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeal() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        int databaseSizeBeforeUpdate = dealRepository.findAll().size();

        // Update the deal
        Deal updatedDeal = dealRepository.findById(deal.getId()).get();
        // Disconnect from session so that the updates on updatedDeal are not directly saved in db
        em.detach(updatedDeal);
        updatedDeal
            .dealName(UPDATED_DEAL_NAME)
            .dealBookDate(UPDATED_DEAL_BOOK_DATE);

        restDealMockMvc.perform(put("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDeal)))
            .andExpect(status().isOk());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeUpdate);
        Deal testDeal = dealList.get(dealList.size() - 1);
        assertThat(testDeal.getDealName()).isEqualTo(UPDATED_DEAL_NAME);
        assertThat(testDeal.getDealBookDate()).isEqualTo(UPDATED_DEAL_BOOK_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDeal() throws Exception {
        int databaseSizeBeforeUpdate = dealRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealMockMvc.perform(put("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deal)))
            .andExpect(status().isBadRequest());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeal() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        int databaseSizeBeforeDelete = dealRepository.findAll().size();

        // Delete the deal
        restDealMockMvc.perform(delete("/api/deals/{id}", deal.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
