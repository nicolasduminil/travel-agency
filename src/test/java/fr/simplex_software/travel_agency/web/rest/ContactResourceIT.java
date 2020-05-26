package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.TravelAgencyApp;
import fr.simplex_software.travel_agency.domain.Contact;
import fr.simplex_software.travel_agency.repository.ContactRepository;

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

import fr.simplex_software.travel_agency.domain.enumeration.Salutation;
/**
 * Integration tests for the {@link ContactResource} REST controller.
 */
@SpringBootTest(classes = TravelAgencyApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ContactResourceIT {

    private static final String DEFAULT_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_EMAIL_ADDRESS = "6A@B[.4+lu";
    private static final String UPDATED_CONTACT_EMAIL_ADDRESS = "[@&_.('";

    private static final String DEFAULT_CONTACT_WEB_SITE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_WEB_SITE = "BBBBBBBBBB";

    private static final Salutation DEFAULT_CONTACT_SALUTATION = Salutation.MADAME;
    private static final Salutation UPDATED_CONTACT_SALUTATION = Salutation.SIR;

    private static final String DEFAULT_CONTACT_JOB_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_JOB_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_FAX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_FAX_NUMBER = "BBBBBBBBBB";

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContactMockMvc;

    private Contact contact;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contact createEntity(EntityManager em) {
        Contact contact = new Contact()
            .contactName(DEFAULT_CONTACT_NAME)
            .contactFirstName(DEFAULT_CONTACT_FIRST_NAME)
            .contactLastName(DEFAULT_CONTACT_LAST_NAME)
            .contactEmailAddress(DEFAULT_CONTACT_EMAIL_ADDRESS)
            .contactWebSite(DEFAULT_CONTACT_WEB_SITE)
            .contactSalutation(DEFAULT_CONTACT_SALUTATION)
            .contactJobTitle(DEFAULT_CONTACT_JOB_TITLE)
            .contactPhoneNumber(DEFAULT_CONTACT_PHONE_NUMBER)
            .contactFaxNumber(DEFAULT_CONTACT_FAX_NUMBER);
        return contact;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contact createUpdatedEntity(EntityManager em) {
        Contact contact = new Contact()
            .contactName(UPDATED_CONTACT_NAME)
            .contactFirstName(UPDATED_CONTACT_FIRST_NAME)
            .contactLastName(UPDATED_CONTACT_LAST_NAME)
            .contactEmailAddress(UPDATED_CONTACT_EMAIL_ADDRESS)
            .contactWebSite(UPDATED_CONTACT_WEB_SITE)
            .contactSalutation(UPDATED_CONTACT_SALUTATION)
            .contactJobTitle(UPDATED_CONTACT_JOB_TITLE)
            .contactPhoneNumber(UPDATED_CONTACT_PHONE_NUMBER)
            .contactFaxNumber(UPDATED_CONTACT_FAX_NUMBER);
        return contact;
    }

    @BeforeEach
    public void initTest() {
        contact = createEntity(em);
    }

    @Test
    @Transactional
    public void createContact() throws Exception {
        int databaseSizeBeforeCreate = contactRepository.findAll().size();
        // Create the Contact
        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isCreated());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeCreate + 1);
        Contact testContact = contactList.get(contactList.size() - 1);
        assertThat(testContact.getContactName()).isEqualTo(DEFAULT_CONTACT_NAME);
        assertThat(testContact.getContactFirstName()).isEqualTo(DEFAULT_CONTACT_FIRST_NAME);
        assertThat(testContact.getContactLastName()).isEqualTo(DEFAULT_CONTACT_LAST_NAME);
        assertThat(testContact.getContactEmailAddress()).isEqualTo(DEFAULT_CONTACT_EMAIL_ADDRESS);
        assertThat(testContact.getContactWebSite()).isEqualTo(DEFAULT_CONTACT_WEB_SITE);
        assertThat(testContact.getContactSalutation()).isEqualTo(DEFAULT_CONTACT_SALUTATION);
        assertThat(testContact.getContactJobTitle()).isEqualTo(DEFAULT_CONTACT_JOB_TITLE);
        assertThat(testContact.getContactPhoneNumber()).isEqualTo(DEFAULT_CONTACT_PHONE_NUMBER);
        assertThat(testContact.getContactFaxNumber()).isEqualTo(DEFAULT_CONTACT_FAX_NUMBER);
    }

    @Test
    @Transactional
    public void createContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactRepository.findAll().size();

        // Create the Contact with an existing ID
        contact.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkContactNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactName(null);

        // Create the Contact, which fails.


        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactFirstName(null);

        // Create the Contact, which fails.


        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactLastName(null);

        // Create the Contact, which fails.


        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactEmailAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactEmailAddress(null);

        // Create the Contact, which fails.


        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactWebSiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactWebSite(null);

        // Create the Contact, which fails.


        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactSalutationIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactSalutation(null);

        // Create the Contact, which fails.


        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactJobTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactJobTitle(null);

        // Create the Contact, which fails.


        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactPhoneNumber(null);

        // Create the Contact, which fails.


        restContactMockMvc.perform(post("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContacts() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Get all the contactList
        restContactMockMvc.perform(get("/api/contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contact.getId().intValue())))
            .andExpect(jsonPath("$.[*].contactName").value(hasItem(DEFAULT_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].contactFirstName").value(hasItem(DEFAULT_CONTACT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].contactLastName").value(hasItem(DEFAULT_CONTACT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].contactEmailAddress").value(hasItem(DEFAULT_CONTACT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].contactWebSite").value(hasItem(DEFAULT_CONTACT_WEB_SITE)))
            .andExpect(jsonPath("$.[*].contactSalutation").value(hasItem(DEFAULT_CONTACT_SALUTATION.toString())))
            .andExpect(jsonPath("$.[*].contactJobTitle").value(hasItem(DEFAULT_CONTACT_JOB_TITLE)))
            .andExpect(jsonPath("$.[*].contactPhoneNumber").value(hasItem(DEFAULT_CONTACT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].contactFaxNumber").value(hasItem(DEFAULT_CONTACT_FAX_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Get the contact
        restContactMockMvc.perform(get("/api/contacts/{id}", contact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contact.getId().intValue()))
            .andExpect(jsonPath("$.contactName").value(DEFAULT_CONTACT_NAME))
            .andExpect(jsonPath("$.contactFirstName").value(DEFAULT_CONTACT_FIRST_NAME))
            .andExpect(jsonPath("$.contactLastName").value(DEFAULT_CONTACT_LAST_NAME))
            .andExpect(jsonPath("$.contactEmailAddress").value(DEFAULT_CONTACT_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.contactWebSite").value(DEFAULT_CONTACT_WEB_SITE))
            .andExpect(jsonPath("$.contactSalutation").value(DEFAULT_CONTACT_SALUTATION.toString()))
            .andExpect(jsonPath("$.contactJobTitle").value(DEFAULT_CONTACT_JOB_TITLE))
            .andExpect(jsonPath("$.contactPhoneNumber").value(DEFAULT_CONTACT_PHONE_NUMBER))
            .andExpect(jsonPath("$.contactFaxNumber").value(DEFAULT_CONTACT_FAX_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingContact() throws Exception {
        // Get the contact
        restContactMockMvc.perform(get("/api/contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        int databaseSizeBeforeUpdate = contactRepository.findAll().size();

        // Update the contact
        Contact updatedContact = contactRepository.findById(contact.getId()).get();
        // Disconnect from session so that the updates on updatedContact are not directly saved in db
        em.detach(updatedContact);
        updatedContact
            .contactName(UPDATED_CONTACT_NAME)
            .contactFirstName(UPDATED_CONTACT_FIRST_NAME)
            .contactLastName(UPDATED_CONTACT_LAST_NAME)
            .contactEmailAddress(UPDATED_CONTACT_EMAIL_ADDRESS)
            .contactWebSite(UPDATED_CONTACT_WEB_SITE)
            .contactSalutation(UPDATED_CONTACT_SALUTATION)
            .contactJobTitle(UPDATED_CONTACT_JOB_TITLE)
            .contactPhoneNumber(UPDATED_CONTACT_PHONE_NUMBER)
            .contactFaxNumber(UPDATED_CONTACT_FAX_NUMBER);

        restContactMockMvc.perform(put("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedContact)))
            .andExpect(status().isOk());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeUpdate);
        Contact testContact = contactList.get(contactList.size() - 1);
        assertThat(testContact.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testContact.getContactFirstName()).isEqualTo(UPDATED_CONTACT_FIRST_NAME);
        assertThat(testContact.getContactLastName()).isEqualTo(UPDATED_CONTACT_LAST_NAME);
        assertThat(testContact.getContactEmailAddress()).isEqualTo(UPDATED_CONTACT_EMAIL_ADDRESS);
        assertThat(testContact.getContactWebSite()).isEqualTo(UPDATED_CONTACT_WEB_SITE);
        assertThat(testContact.getContactSalutation()).isEqualTo(UPDATED_CONTACT_SALUTATION);
        assertThat(testContact.getContactJobTitle()).isEqualTo(UPDATED_CONTACT_JOB_TITLE);
        assertThat(testContact.getContactPhoneNumber()).isEqualTo(UPDATED_CONTACT_PHONE_NUMBER);
        assertThat(testContact.getContactFaxNumber()).isEqualTo(UPDATED_CONTACT_FAX_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingContact() throws Exception {
        int databaseSizeBeforeUpdate = contactRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactMockMvc.perform(put("/api/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        int databaseSizeBeforeDelete = contactRepository.findAll().size();

        // Delete the contact
        restContactMockMvc.perform(delete("/api/contacts/{id}", contact.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
