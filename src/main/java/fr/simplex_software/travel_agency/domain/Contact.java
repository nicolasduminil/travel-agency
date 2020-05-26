package fr.simplex_software.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import fr.simplex_software.travel_agency.domain.enumeration.Salutation;

/**
 * A Contact.
 */
@Entity
@Table(name = "contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @NotNull
    @Column(name = "contact_first_name", nullable = false)
    private String contactFirstName;

    @NotNull
    @Column(name = "contact_last_name", nullable = false)
    private String contactLastName;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "contact_email_address", nullable = false)
    private String contactEmailAddress;

    @NotNull
    @Column(name = "contact_web_site", nullable = false)
    private String contactWebSite;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_salutation", nullable = false)
    private Salutation contactSalutation;

    @NotNull
    @Column(name = "contact_job_title", nullable = false)
    private String contactJobTitle;

    @NotNull
    @Column(name = "contact_phone_number", nullable = false)
    private String contactPhoneNumber;

    @Column(name = "contact_fax_number")
    private String contactFaxNumber;

    @OneToOne
    @JoinColumn(unique = true)
    private Location address;

    @ManyToOne
    @JsonIgnoreProperties(value = "contacts", allowSetters = true)
    private Activity activity;

    @ManyToOne
    @JsonIgnoreProperties(value = "contacts", allowSetters = true)
    private Customer customer;

    @ManyToOne
    @JsonIgnoreProperties(value = "agents", allowSetters = true)
    private Deal deal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public Contact contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public Contact contactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
        return this;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public Contact contactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
        return this;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public Contact contactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
        return this;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public String getContactWebSite() {
        return contactWebSite;
    }

    public Contact contactWebSite(String contactWebSite) {
        this.contactWebSite = contactWebSite;
        return this;
    }

    public void setContactWebSite(String contactWebSite) {
        this.contactWebSite = contactWebSite;
    }

    public Salutation getContactSalutation() {
        return contactSalutation;
    }

    public Contact contactSalutation(Salutation contactSalutation) {
        this.contactSalutation = contactSalutation;
        return this;
    }

    public void setContactSalutation(Salutation contactSalutation) {
        this.contactSalutation = contactSalutation;
    }

    public String getContactJobTitle() {
        return contactJobTitle;
    }

    public Contact contactJobTitle(String contactJobTitle) {
        this.contactJobTitle = contactJobTitle;
        return this;
    }

    public void setContactJobTitle(String contactJobTitle) {
        this.contactJobTitle = contactJobTitle;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public Contact contactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
        return this;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactFaxNumber() {
        return contactFaxNumber;
    }

    public Contact contactFaxNumber(String contactFaxNumber) {
        this.contactFaxNumber = contactFaxNumber;
        return this;
    }

    public void setContactFaxNumber(String contactFaxNumber) {
        this.contactFaxNumber = contactFaxNumber;
    }

    public Location getAddress() {
        return address;
    }

    public Contact address(Location location) {
        this.address = location;
        return this;
    }

    public void setAddress(Location location) {
        this.address = location;
    }

    public Activity getActivity() {
        return activity;
    }

    public Contact activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Contact customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Deal getDeal() {
        return deal;
    }

    public Contact deal(Deal deal) {
        this.deal = deal;
        return this;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }
        return id != null && id.equals(((Contact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contact{" +
            "id=" + getId() +
            ", contactName='" + getContactName() + "'" +
            ", contactFirstName='" + getContactFirstName() + "'" +
            ", contactLastName='" + getContactLastName() + "'" +
            ", contactEmailAddress='" + getContactEmailAddress() + "'" +
            ", contactWebSite='" + getContactWebSite() + "'" +
            ", contactSalutation='" + getContactSalutation() + "'" +
            ", contactJobTitle='" + getContactJobTitle() + "'" +
            ", contactPhoneNumber='" + getContactPhoneNumber() + "'" +
            ", contactFaxNumber='" + getContactFaxNumber() + "'" +
            "}";
    }
}
