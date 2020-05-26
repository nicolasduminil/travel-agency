package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.TravelAgencyApp;
import fr.simplex_software.travel_agency.domain.Accomodation;
import fr.simplex_software.travel_agency.repository.AccomodationRepository;

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

import fr.simplex_software.travel_agency.domain.enumeration.AccomodationType;
import fr.simplex_software.travel_agency.domain.enumeration.AccomodationClass;
/**
 * Integration tests for the {@link AccomodationResource} REST controller.
 */
@SpringBootTest(classes = TravelAgencyApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AccomodationResourceIT {

    private static final String DEFAULT_ACCOMODATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOMODATION_NAME = "BBBBBBBBBB";

    private static final AccomodationType DEFAULT_ACCOMODATION_TYPE = AccomodationType.HOTEL;
    private static final AccomodationType UPDATED_ACCOMODATION_TYPE = AccomodationType.APPARTMENT;

    private static final AccomodationClass DEFAULT_ACCOMODATION_CLASS = AccomodationClass.FIRST;
    private static final AccomodationClass UPDATED_ACCOMODATION_CLASS = AccomodationClass.BUSINESS;

    @Autowired
    private AccomodationRepository accomodationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccomodationMockMvc;

    private Accomodation accomodation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Accomodation createEntity(EntityManager em) {
        Accomodation accomodation = new Accomodation()
            .accomodationName(DEFAULT_ACCOMODATION_NAME)
            .accomodationType(DEFAULT_ACCOMODATION_TYPE)
            .accomodationClass(DEFAULT_ACCOMODATION_CLASS);
        return accomodation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Accomodation createUpdatedEntity(EntityManager em) {
        Accomodation accomodation = new Accomodation()
            .accomodationName(UPDATED_ACCOMODATION_NAME)
            .accomodationType(UPDATED_ACCOMODATION_TYPE)
            .accomodationClass(UPDATED_ACCOMODATION_CLASS);
        return accomodation;
    }

    @BeforeEach
    public void initTest() {
        accomodation = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccomodation() throws Exception {
        int databaseSizeBeforeCreate = accomodationRepository.findAll().size();
        // Create the Accomodation
        restAccomodationMockMvc.perform(post("/api/accomodations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accomodation)))
            .andExpect(status().isCreated());

        // Validate the Accomodation in the database
        List<Accomodation> accomodationList = accomodationRepository.findAll();
        assertThat(accomodationList).hasSize(databaseSizeBeforeCreate + 1);
        Accomodation testAccomodation = accomodationList.get(accomodationList.size() - 1);
        assertThat(testAccomodation.getAccomodationName()).isEqualTo(DEFAULT_ACCOMODATION_NAME);
        assertThat(testAccomodation.getAccomodationType()).isEqualTo(DEFAULT_ACCOMODATION_TYPE);
        assertThat(testAccomodation.getAccomodationClass()).isEqualTo(DEFAULT_ACCOMODATION_CLASS);
    }

    @Test
    @Transactional
    public void createAccomodationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accomodationRepository.findAll().size();

        // Create the Accomodation with an existing ID
        accomodation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccomodationMockMvc.perform(post("/api/accomodations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accomodation)))
            .andExpect(status().isBadRequest());

        // Validate the Accomodation in the database
        List<Accomodation> accomodationList = accomodationRepository.findAll();
        assertThat(accomodationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAccomodationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = accomodationRepository.findAll().size();
        // set the field null
        accomodation.setAccomodationName(null);

        // Create the Accomodation, which fails.


        restAccomodationMockMvc.perform(post("/api/accomodations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accomodation)))
            .andExpect(status().isBadRequest());

        List<Accomodation> accomodationList = accomodationRepository.findAll();
        assertThat(accomodationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccomodationTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = accomodationRepository.findAll().size();
        // set the field null
        accomodation.setAccomodationType(null);

        // Create the Accomodation, which fails.


        restAccomodationMockMvc.perform(post("/api/accomodations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accomodation)))
            .andExpect(status().isBadRequest());

        List<Accomodation> accomodationList = accomodationRepository.findAll();
        assertThat(accomodationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccomodationClassIsRequired() throws Exception {
        int databaseSizeBeforeTest = accomodationRepository.findAll().size();
        // set the field null
        accomodation.setAccomodationClass(null);

        // Create the Accomodation, which fails.


        restAccomodationMockMvc.perform(post("/api/accomodations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accomodation)))
            .andExpect(status().isBadRequest());

        List<Accomodation> accomodationList = accomodationRepository.findAll();
        assertThat(accomodationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAccomodations() throws Exception {
        // Initialize the database
        accomodationRepository.saveAndFlush(accomodation);

        // Get all the accomodationList
        restAccomodationMockMvc.perform(get("/api/accomodations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accomodation.getId().intValue())))
            .andExpect(jsonPath("$.[*].accomodationName").value(hasItem(DEFAULT_ACCOMODATION_NAME)))
            .andExpect(jsonPath("$.[*].accomodationType").value(hasItem(DEFAULT_ACCOMODATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].accomodationClass").value(hasItem(DEFAULT_ACCOMODATION_CLASS.toString())));
    }
    
    @Test
    @Transactional
    public void getAccomodation() throws Exception {
        // Initialize the database
        accomodationRepository.saveAndFlush(accomodation);

        // Get the accomodation
        restAccomodationMockMvc.perform(get("/api/accomodations/{id}", accomodation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accomodation.getId().intValue()))
            .andExpect(jsonPath("$.accomodationName").value(DEFAULT_ACCOMODATION_NAME))
            .andExpect(jsonPath("$.accomodationType").value(DEFAULT_ACCOMODATION_TYPE.toString()))
            .andExpect(jsonPath("$.accomodationClass").value(DEFAULT_ACCOMODATION_CLASS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAccomodation() throws Exception {
        // Get the accomodation
        restAccomodationMockMvc.perform(get("/api/accomodations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccomodation() throws Exception {
        // Initialize the database
        accomodationRepository.saveAndFlush(accomodation);

        int databaseSizeBeforeUpdate = accomodationRepository.findAll().size();

        // Update the accomodation
        Accomodation updatedAccomodation = accomodationRepository.findById(accomodation.getId()).get();
        // Disconnect from session so that the updates on updatedAccomodation are not directly saved in db
        em.detach(updatedAccomodation);
        updatedAccomodation
            .accomodationName(UPDATED_ACCOMODATION_NAME)
            .accomodationType(UPDATED_ACCOMODATION_TYPE)
            .accomodationClass(UPDATED_ACCOMODATION_CLASS);

        restAccomodationMockMvc.perform(put("/api/accomodations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAccomodation)))
            .andExpect(status().isOk());

        // Validate the Accomodation in the database
        List<Accomodation> accomodationList = accomodationRepository.findAll();
        assertThat(accomodationList).hasSize(databaseSizeBeforeUpdate);
        Accomodation testAccomodation = accomodationList.get(accomodationList.size() - 1);
        assertThat(testAccomodation.getAccomodationName()).isEqualTo(UPDATED_ACCOMODATION_NAME);
        assertThat(testAccomodation.getAccomodationType()).isEqualTo(UPDATED_ACCOMODATION_TYPE);
        assertThat(testAccomodation.getAccomodationClass()).isEqualTo(UPDATED_ACCOMODATION_CLASS);
    }

    @Test
    @Transactional
    public void updateNonExistingAccomodation() throws Exception {
        int databaseSizeBeforeUpdate = accomodationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccomodationMockMvc.perform(put("/api/accomodations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accomodation)))
            .andExpect(status().isBadRequest());

        // Validate the Accomodation in the database
        List<Accomodation> accomodationList = accomodationRepository.findAll();
        assertThat(accomodationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccomodation() throws Exception {
        // Initialize the database
        accomodationRepository.saveAndFlush(accomodation);

        int databaseSizeBeforeDelete = accomodationRepository.findAll().size();

        // Delete the accomodation
        restAccomodationMockMvc.perform(delete("/api/accomodations/{id}", accomodation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Accomodation> accomodationList = accomodationRepository.findAll();
        assertThat(accomodationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
