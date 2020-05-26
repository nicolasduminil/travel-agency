package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.TravelAgencyApp;
import fr.simplex_software.travel_agency.domain.Transport;
import fr.simplex_software.travel_agency.repository.TransportRepository;
import fr.simplex_software.travel_agency.service.TransportService;
import fr.simplex_software.travel_agency.service.dto.TransportDTO;
import fr.simplex_software.travel_agency.service.mapper.TransportMapper;

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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.simplex_software.travel_agency.domain.enumeration.TransportType;
/**
 * Integration tests for the {@link TransportResource} REST controller.
 */
@SpringBootTest(classes = TravelAgencyApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TransportResourceIT {

    private static final TransportType DEFAULT_TRANSPORT_TYPE = TransportType.RAILWAY;
    private static final TransportType UPDATED_TRANSPORT_TYPE = TransportType.AIRWAY;

    private static final String DEFAULT_TRANSPORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRANSPORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSPORT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_TRANSPORT_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private TransportRepository transportRepository;

    @Mock
    private TransportRepository transportRepositoryMock;

    @Autowired
    private TransportMapper transportMapper;

    @Mock
    private TransportService transportServiceMock;

    @Autowired
    private TransportService transportService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransportMockMvc;

    private Transport transport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transport createEntity(EntityManager em) {
        Transport transport = new Transport()
            .transportType(DEFAULT_TRANSPORT_TYPE)
            .transportName(DEFAULT_TRANSPORT_NAME)
            .transportDescription(DEFAULT_TRANSPORT_DESCRIPTION);
        return transport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transport createUpdatedEntity(EntityManager em) {
        Transport transport = new Transport()
            .transportType(UPDATED_TRANSPORT_TYPE)
            .transportName(UPDATED_TRANSPORT_NAME)
            .transportDescription(UPDATED_TRANSPORT_DESCRIPTION);
        return transport;
    }

    @BeforeEach
    public void initTest() {
        transport = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransport() throws Exception {
        int databaseSizeBeforeCreate = transportRepository.findAll().size();
        // Create the Transport
        TransportDTO transportDTO = transportMapper.toDto(transport);
        restTransportMockMvc.perform(post("/api/transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportDTO)))
            .andExpect(status().isCreated());

        // Validate the Transport in the database
        List<Transport> transportList = transportRepository.findAll();
        assertThat(transportList).hasSize(databaseSizeBeforeCreate + 1);
        Transport testTransport = transportList.get(transportList.size() - 1);
        assertThat(testTransport.getTransportType()).isEqualTo(DEFAULT_TRANSPORT_TYPE);
        assertThat(testTransport.getTransportName()).isEqualTo(DEFAULT_TRANSPORT_NAME);
        assertThat(testTransport.getTransportDescription()).isEqualTo(DEFAULT_TRANSPORT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTransportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transportRepository.findAll().size();

        // Create the Transport with an existing ID
        transport.setId(1L);
        TransportDTO transportDTO = transportMapper.toDto(transport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransportMockMvc.perform(post("/api/transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transport in the database
        List<Transport> transportList = transportRepository.findAll();
        assertThat(transportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTransportTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportRepository.findAll().size();
        // set the field null
        transport.setTransportType(null);

        // Create the Transport, which fails.
        TransportDTO transportDTO = transportMapper.toDto(transport);


        restTransportMockMvc.perform(post("/api/transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportDTO)))
            .andExpect(status().isBadRequest());

        List<Transport> transportList = transportRepository.findAll();
        assertThat(transportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransportNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportRepository.findAll().size();
        // set the field null
        transport.setTransportName(null);

        // Create the Transport, which fails.
        TransportDTO transportDTO = transportMapper.toDto(transport);


        restTransportMockMvc.perform(post("/api/transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportDTO)))
            .andExpect(status().isBadRequest());

        List<Transport> transportList = transportRepository.findAll();
        assertThat(transportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransportDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportRepository.findAll().size();
        // set the field null
        transport.setTransportDescription(null);

        // Create the Transport, which fails.
        TransportDTO transportDTO = transportMapper.toDto(transport);


        restTransportMockMvc.perform(post("/api/transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportDTO)))
            .andExpect(status().isBadRequest());

        List<Transport> transportList = transportRepository.findAll();
        assertThat(transportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransports() throws Exception {
        // Initialize the database
        transportRepository.saveAndFlush(transport);

        // Get all the transportList
        restTransportMockMvc.perform(get("/api/transports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transport.getId().intValue())))
            .andExpect(jsonPath("$.[*].transportType").value(hasItem(DEFAULT_TRANSPORT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].transportName").value(hasItem(DEFAULT_TRANSPORT_NAME)))
            .andExpect(jsonPath("$.[*].transportDescription").value(hasItem(DEFAULT_TRANSPORT_DESCRIPTION)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllTransportsWithEagerRelationshipsIsEnabled() throws Exception {
        when(transportServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTransportMockMvc.perform(get("/api/transports?eagerload=true"))
            .andExpect(status().isOk());

        verify(transportServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllTransportsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(transportServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTransportMockMvc.perform(get("/api/transports?eagerload=true"))
            .andExpect(status().isOk());

        verify(transportServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getTransport() throws Exception {
        // Initialize the database
        transportRepository.saveAndFlush(transport);

        // Get the transport
        restTransportMockMvc.perform(get("/api/transports/{id}", transport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transport.getId().intValue()))
            .andExpect(jsonPath("$.transportType").value(DEFAULT_TRANSPORT_TYPE.toString()))
            .andExpect(jsonPath("$.transportName").value(DEFAULT_TRANSPORT_NAME))
            .andExpect(jsonPath("$.transportDescription").value(DEFAULT_TRANSPORT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingTransport() throws Exception {
        // Get the transport
        restTransportMockMvc.perform(get("/api/transports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransport() throws Exception {
        // Initialize the database
        transportRepository.saveAndFlush(transport);

        int databaseSizeBeforeUpdate = transportRepository.findAll().size();

        // Update the transport
        Transport updatedTransport = transportRepository.findById(transport.getId()).get();
        // Disconnect from session so that the updates on updatedTransport are not directly saved in db
        em.detach(updatedTransport);
        updatedTransport
            .transportType(UPDATED_TRANSPORT_TYPE)
            .transportName(UPDATED_TRANSPORT_NAME)
            .transportDescription(UPDATED_TRANSPORT_DESCRIPTION);
        TransportDTO transportDTO = transportMapper.toDto(updatedTransport);

        restTransportMockMvc.perform(put("/api/transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportDTO)))
            .andExpect(status().isOk());

        // Validate the Transport in the database
        List<Transport> transportList = transportRepository.findAll();
        assertThat(transportList).hasSize(databaseSizeBeforeUpdate);
        Transport testTransport = transportList.get(transportList.size() - 1);
        assertThat(testTransport.getTransportType()).isEqualTo(UPDATED_TRANSPORT_TYPE);
        assertThat(testTransport.getTransportName()).isEqualTo(UPDATED_TRANSPORT_NAME);
        assertThat(testTransport.getTransportDescription()).isEqualTo(UPDATED_TRANSPORT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingTransport() throws Exception {
        int databaseSizeBeforeUpdate = transportRepository.findAll().size();

        // Create the Transport
        TransportDTO transportDTO = transportMapper.toDto(transport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransportMockMvc.perform(put("/api/transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transport in the database
        List<Transport> transportList = transportRepository.findAll();
        assertThat(transportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransport() throws Exception {
        // Initialize the database
        transportRepository.saveAndFlush(transport);

        int databaseSizeBeforeDelete = transportRepository.findAll().size();

        // Delete the transport
        restTransportMockMvc.perform(delete("/api/transports/{id}", transport.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Transport> transportList = transportRepository.findAll();
        assertThat(transportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
