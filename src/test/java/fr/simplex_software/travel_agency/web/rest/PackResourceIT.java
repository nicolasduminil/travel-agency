package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.TravelAgencyApp;
import fr.simplex_software.travel_agency.domain.Pack;
import fr.simplex_software.travel_agency.repository.PackRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PackResource} REST controller.
 */
@SpringBootTest(classes = TravelAgencyApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PackResourceIT {

    private static final String DEFAULT_PACKAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PACKAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PACKAGE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PACKAGE_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PACKAGE_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PACKAGE_DISCOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PACKAGE_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PACKAGE_PRICE = new BigDecimal(2);

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPackMockMvc;

    private Pack pack;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pack createEntity(EntityManager em) {
        Pack pack = new Pack()
            .packageName(DEFAULT_PACKAGE_NAME)
            .packageDescription(DEFAULT_PACKAGE_DESCRIPTION)
            .packageDiscount(DEFAULT_PACKAGE_DISCOUNT)
            .packagePrice(DEFAULT_PACKAGE_PRICE);
        return pack;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pack createUpdatedEntity(EntityManager em) {
        Pack pack = new Pack()
            .packageName(UPDATED_PACKAGE_NAME)
            .packageDescription(UPDATED_PACKAGE_DESCRIPTION)
            .packageDiscount(UPDATED_PACKAGE_DISCOUNT)
            .packagePrice(UPDATED_PACKAGE_PRICE);
        return pack;
    }

    @BeforeEach
    public void initTest() {
        pack = createEntity(em);
    }

    @Test
    @Transactional
    public void createPack() throws Exception {
        int databaseSizeBeforeCreate = packRepository.findAll().size();
        // Create the Pack
        restPackMockMvc.perform(post("/api/packs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pack)))
            .andExpect(status().isCreated());

        // Validate the Pack in the database
        List<Pack> packList = packRepository.findAll();
        assertThat(packList).hasSize(databaseSizeBeforeCreate + 1);
        Pack testPack = packList.get(packList.size() - 1);
        assertThat(testPack.getPackageName()).isEqualTo(DEFAULT_PACKAGE_NAME);
        assertThat(testPack.getPackageDescription()).isEqualTo(DEFAULT_PACKAGE_DESCRIPTION);
        assertThat(testPack.getPackageDiscount()).isEqualTo(DEFAULT_PACKAGE_DISCOUNT);
        assertThat(testPack.getPackagePrice()).isEqualTo(DEFAULT_PACKAGE_PRICE);
    }

    @Test
    @Transactional
    public void createPackWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = packRepository.findAll().size();

        // Create the Pack with an existing ID
        pack.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPackMockMvc.perform(post("/api/packs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pack)))
            .andExpect(status().isBadRequest());

        // Validate the Pack in the database
        List<Pack> packList = packRepository.findAll();
        assertThat(packList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPackageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = packRepository.findAll().size();
        // set the field null
        pack.setPackageName(null);

        // Create the Pack, which fails.


        restPackMockMvc.perform(post("/api/packs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pack)))
            .andExpect(status().isBadRequest());

        List<Pack> packList = packRepository.findAll();
        assertThat(packList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPackageDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = packRepository.findAll().size();
        // set the field null
        pack.setPackageDescription(null);

        // Create the Pack, which fails.


        restPackMockMvc.perform(post("/api/packs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pack)))
            .andExpect(status().isBadRequest());

        List<Pack> packList = packRepository.findAll();
        assertThat(packList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPackagePriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = packRepository.findAll().size();
        // set the field null
        pack.setPackagePrice(null);

        // Create the Pack, which fails.


        restPackMockMvc.perform(post("/api/packs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pack)))
            .andExpect(status().isBadRequest());

        List<Pack> packList = packRepository.findAll();
        assertThat(packList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPacks() throws Exception {
        // Initialize the database
        packRepository.saveAndFlush(pack);

        // Get all the packList
        restPackMockMvc.perform(get("/api/packs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pack.getId().intValue())))
            .andExpect(jsonPath("$.[*].packageName").value(hasItem(DEFAULT_PACKAGE_NAME)))
            .andExpect(jsonPath("$.[*].packageDescription").value(hasItem(DEFAULT_PACKAGE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].packageDiscount").value(hasItem(DEFAULT_PACKAGE_DISCOUNT.intValue())))
            .andExpect(jsonPath("$.[*].packagePrice").value(hasItem(DEFAULT_PACKAGE_PRICE.intValue())));
    }
    
    @Test
    @Transactional
    public void getPack() throws Exception {
        // Initialize the database
        packRepository.saveAndFlush(pack);

        // Get the pack
        restPackMockMvc.perform(get("/api/packs/{id}", pack.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pack.getId().intValue()))
            .andExpect(jsonPath("$.packageName").value(DEFAULT_PACKAGE_NAME))
            .andExpect(jsonPath("$.packageDescription").value(DEFAULT_PACKAGE_DESCRIPTION))
            .andExpect(jsonPath("$.packageDiscount").value(DEFAULT_PACKAGE_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.packagePrice").value(DEFAULT_PACKAGE_PRICE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPack() throws Exception {
        // Get the pack
        restPackMockMvc.perform(get("/api/packs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePack() throws Exception {
        // Initialize the database
        packRepository.saveAndFlush(pack);

        int databaseSizeBeforeUpdate = packRepository.findAll().size();

        // Update the pack
        Pack updatedPack = packRepository.findById(pack.getId()).get();
        // Disconnect from session so that the updates on updatedPack are not directly saved in db
        em.detach(updatedPack);
        updatedPack
            .packageName(UPDATED_PACKAGE_NAME)
            .packageDescription(UPDATED_PACKAGE_DESCRIPTION)
            .packageDiscount(UPDATED_PACKAGE_DISCOUNT)
            .packagePrice(UPDATED_PACKAGE_PRICE);

        restPackMockMvc.perform(put("/api/packs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPack)))
            .andExpect(status().isOk());

        // Validate the Pack in the database
        List<Pack> packList = packRepository.findAll();
        assertThat(packList).hasSize(databaseSizeBeforeUpdate);
        Pack testPack = packList.get(packList.size() - 1);
        assertThat(testPack.getPackageName()).isEqualTo(UPDATED_PACKAGE_NAME);
        assertThat(testPack.getPackageDescription()).isEqualTo(UPDATED_PACKAGE_DESCRIPTION);
        assertThat(testPack.getPackageDiscount()).isEqualTo(UPDATED_PACKAGE_DISCOUNT);
        assertThat(testPack.getPackagePrice()).isEqualTo(UPDATED_PACKAGE_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingPack() throws Exception {
        int databaseSizeBeforeUpdate = packRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPackMockMvc.perform(put("/api/packs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pack)))
            .andExpect(status().isBadRequest());

        // Validate the Pack in the database
        List<Pack> packList = packRepository.findAll();
        assertThat(packList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePack() throws Exception {
        // Initialize the database
        packRepository.saveAndFlush(pack);

        int databaseSizeBeforeDelete = packRepository.findAll().size();

        // Delete the pack
        restPackMockMvc.perform(delete("/api/packs/{id}", pack.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Pack> packList = packRepository.findAll();
        assertThat(packList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
