package fr.simplex_software.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Deal.
 */
@Entity
@Table(name = "deal")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "deal_name", nullable = false)
    private String dealName;

    @NotNull
    @Column(name = "deal_book_date", nullable = false)
    private LocalDate dealBookDate;

    @OneToMany(mappedBy = "deal")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Contact> agents = new HashSet<>();

    @OneToMany(mappedBy = "deal")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Customer> customers = new HashSet<>();

    @ManyToMany(mappedBy = "deals")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Service> services = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealName() {
        return dealName;
    }

    public Deal dealName(String dealName) {
        this.dealName = dealName;
        return this;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public LocalDate getDealBookDate() {
        return dealBookDate;
    }

    public Deal dealBookDate(LocalDate dealBookDate) {
        this.dealBookDate = dealBookDate;
        return this;
    }

    public void setDealBookDate(LocalDate dealBookDate) {
        this.dealBookDate = dealBookDate;
    }

    public Set<Contact> getAgents() {
        return agents;
    }

    public Deal agents(Set<Contact> contacts) {
        this.agents = contacts;
        return this;
    }

    public Deal addAgents(Contact contact) {
        this.agents.add(contact);
        contact.setDeal(this);
        return this;
    }

    public Deal removeAgents(Contact contact) {
        this.agents.remove(contact);
        contact.setDeal(null);
        return this;
    }

    public void setAgents(Set<Contact> contacts) {
        this.agents = contacts;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Deal customers(Set<Customer> customers) {
        this.customers = customers;
        return this;
    }

    public Deal addCustomers(Customer customer) {
        this.customers.add(customer);
        customer.setDeal(this);
        return this;
    }

    public Deal removeCustomers(Customer customer) {
        this.customers.remove(customer);
        customer.setDeal(null);
        return this;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Deal services(Set<Service> services) {
        this.services = services;
        return this;
    }

    public Deal addServices(Service service) {
        this.services.add(service);
        service.getDeals().add(this);
        return this;
    }

    public Deal removeServices(Service service) {
        this.services.remove(service);
        service.getDeals().remove(this);
        return this;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deal)) {
            return false;
        }
        return id != null && id.equals(((Deal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Deal{" +
            "id=" + getId() +
            ", dealName='" + getDealName() + "'" +
            ", dealBookDate='" + getDealBookDate() + "'" +
            "}";
    }
}
