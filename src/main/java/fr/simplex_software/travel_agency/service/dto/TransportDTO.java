package fr.simplex_software.travel_agency.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import fr.simplex_software.travel_agency.domain.enumeration.TransportType;

/**
 * A DTO for the {@link fr.simplex_software.travel_agency.domain.Transport} entity.
 */
public class TransportDTO implements Serializable {
    
    private Long id;

    @NotNull
    private TransportType transportType;

    @NotNull
    private String transportName;

    @NotNull
    private String transportDescription;


    private Long toId;

    private Long fromId;
    private Set<ServiceDTO> services = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getTransportDescription() {
        return transportDescription;
    }

    public void setTransportDescription(String transportDescription) {
        this.transportDescription = transportDescription;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long locationId) {
        this.toId = locationId;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long locationId) {
        this.fromId = locationId;
    }

    public Set<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(Set<ServiceDTO> services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportDTO)) {
            return false;
        }

        return id != null && id.equals(((TransportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransportDTO{" +
            "id=" + getId() +
            ", transportType='" + getTransportType() + "'" +
            ", transportName='" + getTransportName() + "'" +
            ", transportDescription='" + getTransportDescription() + "'" +
            ", toId=" + getToId() +
            ", fromId=" + getFromId() +
            ", services='" + getServices() + "'" +
            "}";
    }
}
