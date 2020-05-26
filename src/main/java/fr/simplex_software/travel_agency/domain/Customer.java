package fr.simplex_software.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fr.simplex_software.travel_agency.domain.enumeration.Gender;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_gender")
    private Gender customerGender;

    @Column(name = "customer_birth_date")
    private LocalDate customerBirthDate;

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Location> locations = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Contact> contacts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "customers", allowSetters = true)
    private Deal deal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Customer customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Gender getCustomerGender() {
        return customerGender;
    }

    public Customer customerGender(Gender customerGender) {
        this.customerGender = customerGender;
        return this;
    }

    public void setCustomerGender(Gender customerGender) {
        this.customerGender = customerGender;
    }

    public LocalDate getCustomerBirthDate() {
        return customerBirthDate;
    }

    public Customer customerBirthDate(LocalDate customerBirthDate) {
        this.customerBirthDate = customerBirthDate;
        return this;
    }

    public void setCustomerBirthDate(LocalDate customerBirthDate) {
        this.customerBirthDate = customerBirthDate;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public Customer locations(Set<Location> locations) {
        this.locations = locations;
        return this;
    }

    public Customer addLocations(Location location) {
        this.locations.add(location);
        location.setCustomer(this);
        return this;
    }

    public Customer removeLocations(Location location) {
        this.locations.remove(location);
        location.setCustomer(null);
        return this;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public Customer contacts(Set<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public Customer addContacts(Contact contact) {
        this.contacts.add(contact);
        contact.setCustomer(this);
        return this;
    }

    public Customer removeContacts(Contact contact) {
        this.contacts.remove(contact);
        contact.setCustomer(null);
        return this;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Deal getDeal() {
        return deal;
    }

    public Customer deal(Deal deal) {
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
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", customerName='" + getCustomerName() + "'" +
            ", customerGender='" + getCustomerGender() + "'" +
            ", customerBirthDate='" + getCustomerBirthDate() + "'" +
            "}";
    }
}
