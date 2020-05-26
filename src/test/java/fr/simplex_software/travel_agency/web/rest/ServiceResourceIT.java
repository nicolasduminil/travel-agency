package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.TravelAgencyApp;
import fr.simplex_software.travel_agency.domain.Service;
import fr.simplex_software.travel_agency.repository.ServiceRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ServiceResource} REST controller.
 */
@SpringBootTest(classes = TravelAgencyApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ServiceResourceIT {

    private static final String DEFAULT_SERVICE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SERVICE_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SERVICE_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_SERVICE_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SERVICE_PRICE = new BigDecimal(2);

    @Autowired
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceRepository serviceRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceMockMvc;

    private Service service;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Service createEntity(EntityManager em) {
        Service service = new Service()
            .serviceDescription(DEFAULT_SERVICE_DESCRIPTION)
            .serviceStartDate(DEFAULT_SERVICE_START_DATE)
            .serviceEndDate(DEFAULT_SERVICE_END_DATE)
            .servicePrice(DEFAULT_SERVICE_PRICE);
        return service;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Service createUpdatedEntity(EntityManager em) {
        Service service = new Service()
            .serviceDescription(UPDATED_SERVICE_DESCRIPTION)
            .serviceStartDate(UPDATED_SERVICE_START_DATE)
            .serviceEndDate(UPDATED_SERVICE_END_DATE)
            .servicePrice(UPDATED_SERVICE_PRICE);
        return service;
    }

    @BeforeEach
    public void initTest() {
        service = createEntity(em);
    }

    @Test
    @Transactional
    public void createService() throws Exception {
        int databaseSizeBeforeCreate = serviceRepository.findAll().size();
        // Create the Service
        restServiceMockMvc.perform(post("/api/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(service)))
            .andExpect(status().isCreated());

        // Validate the Service in the database
        List<Service> serviceList = serviceRepository.findAll();
        assertThat(serviceList).hasSize(databaseSizeBeforeCreate + 1);
        Service testService = serviceList.get(serviceList.size() - 1);
        assertThat(testService.getServiceDescription()).isEqualTo(DEFAULT_SERVICE_DESCRIPTION);
        assertThat(testService.getServiceStartDate()).isEqualTo(DEFAULT_SERVICE_START_DATE);
        assertThat(testService.getServiceEndDate()).isEqualTo(DEFAULT_SERVICE_END_DATE);
        assertThat(testService.getServicePrice()).isEqualTo(DEFAULT_SERVICE_PRICE);
    }

    @Test
    @Transactional
    public void createServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceRepository.findAll().size();

        // Create the Service with an existing ID
        service.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceMockMvc.perform(post("/api/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(service)))
            .andExpect(status().isBadRequest());

        // Validate the Service in the database
        List<Service> serviceList = serviceRepository.findAll();
        assertThat(serviceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkServiceDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceRepository.findAll().size();
        // set the field null
        service.setServiceDescription(null);

        // Create the Service, which fails.


        restServiceMockMvc.perform(post("/api/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(service)))
            .andExpect(status().isBadRequest());

        List<Service> serviceList = serviceRepository.findAll();
        assertThat(serviceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkServiceStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceRepository.findAll().size();
        // set the field null
        service.setServiceStartDate(null);

        // Create the Service, which fails.


        restServiceMockMvc.perform(post("/api/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(service)))
            .andExpect(status().isBadRequest());

        List<Service> serviceList = serviceRepository.findAll();
        assertThat(serviceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkServiceEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceRepository.findAll().size();
        // set the field null
        service.setServiceEndDate(null);

        // Create the Service, which fails.


        restServiceMockMvc.perform(post("/api/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(service)))
            .andExpect(status().isBadRequest());

        List<Service> serviceList = serviceRepository.findAll();
        assertThat(serviceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkServicePriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceRepository.findAll().size();
        // set the field null
        service.setServicePrice(null);

        // Create the Service, which fails.


        restServiceMockMvc.perform(post("/api/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(service)))
            .andExpect(status().isBadRequest());

        List<Service> serviceList = serviceRepository.findAll();
        assertThat(serviceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllServices() throws Exception {
        // Initialize the database
        serviceRepository.saveAndFlush(service);

        // Get all the serviceList
        restServiceMockMvc.perform(get("/api/services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(service.getId().intValue())))
            .andExpect(jsonPath("$.[*].serviceDescription").value(hasItem(DEFAULT_SERVICE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].serviceStartDate").value(hasItem(DEFAULT_SERVICE_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].serviceEndDate").value(hasItem(DEFAULT_SERVICE_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].servicePrice").value(hasItem(DEFAULT_SERVICE_PRICE.intValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllServicesWithEagerRelationshipsIsEnabled() throws Exception {
        when(serviceRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restServiceMockMvc.perform(get("/api/services?eagerload=true"))
            .andExpect(status().isOk());

        verify(serviceRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllServicesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(serviceRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restServiceMockMvc.perform(get("/api/services?eagerload=true"))
            .andExpect(status().isOk());

        verify(serviceRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getService() throws Exception {
        // Initialize the database
        serviceRepository.saveAndFlush(service);

        // Get the service
        restServiceMockMvc.perform(get("/api/services/{id}", service.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(service.getId().intValue()))
            .andExpect(jsonPath("$.serviceDescription").value(DEFAULT_SERVICE_DESCRIPTION))
            .andExpect(jsonPath("$.serviceStartDate").value(DEFAULT_SERVICE_START_DATE.toString()))
            .andExpect(jsonPath("$.serviceEndDate").value(DEFAULT_SERVICE_END_DATE.toString()))
            .andExpect(jsonPath("$.servicePrice").value(DEFAULT_SERVICE_PRICE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingService() throws Exception {
        // Get the service
        restServiceMockMvc.perform(get("/api/services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateService() throws Exception {
        // Initialize the database
        serviceRepository.saveAndFlush(service);

        int databaseSizeBeforeUpdate = serviceRepository.findAll().size();

        // Update the service
        Service updatedService = serviceRepository.findById(service.getId()).get();
        // Disconnect from session so that the updates on updatedService are not directly saved in db
        em.detach(updatedService);
        updatedService
            .serviceDescription(UPDATED_SERVICE_DESCRIPTION)
            .serviceStartDate(UPDATED_SERVICE_START_DATE)
            .serviceEndDate(UPDATED_SERVICE_END_DATE)
            .servicePrice(UPDATED_SERVICE_PRICE);

        restServiceMockMvc.perform(put("/api/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedService)))
            .andExpect(status().isOk());

        // Validate the Service in the database
        List<Service> serviceList = serviceRepository.findAll();
        assertThat(serviceList).hasSize(databaseSizeBeforeUpdate);
        Service testService = serviceList.get(serviceList.size() - 1);
        assertThat(testService.getServiceDescription()).isEqualTo(UPDATED_SERVICE_DESCRIPTION);
        assertThat(testService.getServiceStartDate()).isEqualTo(UPDATED_SERVICE_START_DATE);
        assertThat(testService.getServiceEndDate()).isEqualTo(UPDATED_SERVICE_END_DATE);
        assertThat(testService.getServicePrice()).isEqualTo(UPDATED_SERVICE_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingService() throws Exception {
        int databaseSizeBeforeUpdate = serviceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceMockMvc.perform(put("/api/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(service)))
            .andExpect(status().isBadRequest());

        // Validate the Service in the database
        List<Service> serviceList = serviceRepository.findAll();
        assertThat(serviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteService() throws Exception {
        // Initialize the database
        serviceRepository.saveAndFlush(service);

        int databaseSizeBeforeDelete = serviceRepository.findAll().size();

        // Delete the service
        restServiceMockMvc.perform(delete("/api/services/{id}", service.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Service> serviceList = serviceRepository.findAll();
        assertThat(serviceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
