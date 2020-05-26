package fr.simplex_software.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Service.
 */
@Entity
@Table(name = "service")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "service_description", nullable = false)
    private String serviceDescription;

    @NotNull
    @Column(name = "service_start_date", nullable = false)
    private LocalDate serviceStartDate;

    @NotNull
    @Column(name = "service_end_date", nullable = false)
    private LocalDate serviceEndDate;

    @NotNull
    @Column(name = "service_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal servicePrice;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "service_deals",
               joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "deals_id", referencedColumnName = "id"))
    private Set<Deal> deals = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "service_packages",
               joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "packages_id", referencedColumnName = "id"))
    private Set<Pack> packages = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "service_accomodations",
               joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "accomodations_id", referencedColumnName = "id"))
    private Set<Accomodation> accomodations = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "service_activities",
               joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "activities_id", referencedColumnName = "id"))
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(mappedBy = "services")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Transport> transports = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public Service serviceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
        return this;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public LocalDate getServiceStartDate() {
        return serviceStartDate;
    }

    public Service serviceStartDate(LocalDate serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
        return this;
    }

    public void setServiceStartDate(LocalDate serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public LocalDate getServiceEndDate() {
        return serviceEndDate;
    }

    public Service serviceEndDate(LocalDate serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
        return this;
    }

    public void setServiceEndDate(LocalDate serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public Service servicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
        return this;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Set<Deal> getDeals() {
        return deals;
    }

    public Service deals(Set<Deal> deals) {
        this.deals = deals;
        return this;
    }

    public Service addDeals(Deal deal) {
        this.deals.add(deal);
        deal.getServices().add(this);
        return this;
    }

    public Service removeDeals(Deal deal) {
        this.deals.remove(deal);
        deal.getServices().remove(this);
        return this;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }

    public Set<Pack> getPackages() {
        return packages;
    }

    public Service packages(Set<Pack> packs) {
        this.packages = packs;
        return this;
    }

    public Service addPackages(Pack pack) {
        this.packages.add(pack);
        pack.getServices().add(this);
        return this;
    }

    public Service removePackages(Pack pack) {
        this.packages.remove(pack);
        pack.getServices().remove(this);
        return this;
    }

    public void setPackages(Set<Pack> packs) {
        this.packages = packs;
    }

    public Set<Accomodation> getAccomodations() {
        return accomodations;
    }

    public Service accomodations(Set<Accomodation> accomodations) {
        this.accomodations = accomodations;
        return this;
    }

    public Service addAccomodations(Accomodation accomodation) {
        this.accomodations.add(accomodation);
        accomodation.getServices().add(this);
        return this;
    }

    public Service removeAccomodations(Accomodation accomodation) {
        this.accomodations.remove(accomodation);
        accomodation.getServices().remove(this);
        return this;
    }

    public void setAccomodations(Set<Accomodation> accomodations) {
        this.accomodations = accomodations;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public Service activities(Set<Activity> activities) {
        this.activities = activities;
        return this;
    }

    public Service addActivities(Activity activity) {
        this.activities.add(activity);
        activity.getServices().add(this);
        return this;
    }

    public Service removeActivities(Activity activity) {
        this.activities.remove(activity);
        activity.getServices().remove(this);
        return this;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<Transport> getTransports() {
        return transports;
    }

    public Service transports(Set<Transport> transports) {
        this.transports = transports;
        return this;
    }

    public Service addTransport(Transport transport) {
        this.transports.add(transport);
        transport.getServices().add(this);
        return this;
    }

    public Service removeTransport(Transport transport) {
        this.transports.remove(transport);
        transport.getServices().remove(this);
        return this;
    }

    public void setTransports(Set<Transport> transports) {
        this.transports = transports;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Service)) {
            return false;
        }
        return id != null && id.equals(((Service) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Service{" +
            "id=" + getId() +
            ", serviceDescription='" + getServiceDescription() + "'" +
            ", serviceStartDate='" + getServiceStartDate() + "'" +
            ", serviceEndDate='" + getServiceEndDate() + "'" +
            ", servicePrice=" + getServicePrice() +
            "}";
    }
}
