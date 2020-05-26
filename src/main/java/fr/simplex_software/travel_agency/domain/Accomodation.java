package fr.simplex_software.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.simplex_software.travel_agency.domain.enumeration.AccomodationType;

import fr.simplex_software.travel_agency.domain.enumeration.AccomodationClass;

/**
 * A Accomodation.
 */
@Entity
@Table(name = "accomodation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Accomodation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "accomodation_name", nullable = false)
    private String accomodationName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "accomodation_type", nullable = false)
    private AccomodationType accomodationType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "accomodation_class", nullable = false)
    private AccomodationClass accomodationClass;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @ManyToMany(mappedBy = "accomodations")
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

    public String getAccomodationName() {
        return accomodationName;
    }

    public Accomodation accomodationName(String accomodationName) {
        this.accomodationName = accomodationName;
        return this;
    }

    public void setAccomodationName(String accomodationName) {
        this.accomodationName = accomodationName;
    }

    public AccomodationType getAccomodationType() {
        return accomodationType;
    }

    public Accomodation accomodationType(AccomodationType accomodationType) {
        this.accomodationType = accomodationType;
        return this;
    }

    public void setAccomodationType(AccomodationType accomodationType) {
        this.accomodationType = accomodationType;
    }

    public AccomodationClass getAccomodationClass() {
        return accomodationClass;
    }

    public Accomodation accomodationClass(AccomodationClass accomodationClass) {
        this.accomodationClass = accomodationClass;
        return this;
    }

    public void setAccomodationClass(AccomodationClass accomodationClass) {
        this.accomodationClass = accomodationClass;
    }

    public Location getLocation() {
        return location;
    }

    public Accomodation location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Accomodation services(Set<Service> services) {
        this.services = services;
        return this;
    }

    public Accomodation addServices(Service service) {
        this.services.add(service);
        service.getAccomodations().add(this);
        return this;
    }

    public Accomodation removeServices(Service service) {
        this.services.remove(service);
        service.getAccomodations().remove(this);
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
        if (!(o instanceof Accomodation)) {
            return false;
        }
        return id != null && id.equals(((Accomodation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Accomodation{" +
            "id=" + getId() +
            ", accomodationName='" + getAccomodationName() + "'" +
            ", accomodationType='" + getAccomodationType() + "'" +
            ", accomodationClass='" + getAccomodationClass() + "'" +
            "}";
    }
}
