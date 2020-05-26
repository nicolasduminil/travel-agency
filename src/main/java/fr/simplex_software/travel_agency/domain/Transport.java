package fr.simplex_software.travel_agency.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.simplex_software.travel_agency.domain.enumeration.TransportType;

/**
 * A Transport.
 */
@Entity
@Table(name = "transport")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Transport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "transport_type", nullable = false)
    private TransportType transportType;

    @NotNull
    @Column(name = "transport_name", nullable = false)
    private String transportName;

    @NotNull
    @Column(name = "transport_description", nullable = false)
    private String transportDescription;

    @OneToOne
    @JoinColumn(unique = true)
    private Location to;

    @OneToOne
    @JoinColumn(unique = true)
    private Location from;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "transport_service",
               joinColumns = @JoinColumn(name = "transport_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<Service> services = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public Transport transportType(TransportType transportType) {
        this.transportType = transportType;
        return this;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getTransportName() {
        return transportName;
    }

    public Transport transportName(String transportName) {
        this.transportName = transportName;
        return this;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getTransportDescription() {
        return transportDescription;
    }

    public Transport transportDescription(String transportDescription) {
        this.transportDescription = transportDescription;
        return this;
    }

    public void setTransportDescription(String transportDescription) {
        this.transportDescription = transportDescription;
    }

    public Location getTo() {
        return to;
    }

    public Transport to(Location location) {
        this.to = location;
        return this;
    }

    public void setTo(Location location) {
        this.to = location;
    }

    public Location getFrom() {
        return from;
    }

    public Transport from(Location location) {
        this.from = location;
        return this;
    }

    public void setFrom(Location location) {
        this.from = location;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Transport services(Set<Service> services) {
        this.services = services;
        return this;
    }

    public Transport addService(Service service) {
        this.services.add(service);
        service.getTransports().add(this);
        return this;
    }

    public Transport removeService(Service service) {
        this.services.remove(service);
        service.getTransports().remove(this);
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
        if (!(o instanceof Transport)) {
            return false;
        }
        return id != null && id.equals(((Transport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transport{" +
            "id=" + getId() +
            ", transportType='" + getTransportType() + "'" +
            ", transportName='" + getTransportName() + "'" +
            ", transportDescription='" + getTransportDescription() + "'" +
            "}";
    }
}
