package fr.simplex_software.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Location.
 */
@Entity
@Table(name = "location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @NotNull
    @Column(name = "street_number", nullable = false)
    private String streetNumber;

    @NotNull
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @Column(name = "state_province", nullable = false)
    private String stateProvince;

    @NotNull
    @Column(name = "country_name", nullable = false)
    private String countryName;

    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private Accomodation accomodation;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Contact contact;

    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private Activity activity;

    @OneToOne(mappedBy = "to")
    @JsonIgnore
    private Transport transport;

    @OneToOne(mappedBy = "from")
    @JsonIgnore
    private Transport transport;

    @ManyToOne
    @JsonIgnoreProperties(value = "locations", allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public Location streetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public Location streetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Location postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public Location city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public Location stateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
        return this;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCountryName() {
        return countryName;
    }

    public Location countryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Accomodation getAccomodation() {
        return accomodation;
    }

    public Location accomodation(Accomodation accomodation) {
        this.accomodation = accomodation;
        return this;
    }

    public void setAccomodation(Accomodation accomodation) {
        this.accomodation = accomodation;
    }

    public Contact getContact() {
        return contact;
    }

    public Location contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Activity getActivity() {
        return activity;
    }

    public Location activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Transport getTransport() {
        return transport;
    }

    public Location transport(Transport transport) {
        this.transport = transport;
        return this;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Transport getTransport() {
        return transport;
    }

    public Location transport(Transport transport) {
        this.transport = transport;
        return this;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Location customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        return id != null && id.equals(((Location) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", streetAddress='" + getStreetAddress() + "'" +
            ", streetNumber='" + getStreetNumber() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", city='" + getCity() + "'" +
            ", stateProvince='" + getStateProvince() + "'" +
            ", countryName='" + getCountryName() + "'" +
            "}";
    }
}
