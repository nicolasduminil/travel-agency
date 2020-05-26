package fr.simplex_software.travel_agency.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link fr.simplex_software.travel_agency.domain.Service} entity.
 */
public class ServiceDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String serviceDescription;

    @NotNull
    private LocalDate serviceStartDate;

    @NotNull
    private LocalDate serviceEndDate;

    @NotNull
    private BigDecimal servicePrice;

    private Set<DealDTO> deals = new HashSet<>();
    private Set<PackDTO> packages = new HashSet<>();
    private Set<AccomodationDTO> accomodations = new HashSet<>();
    private Set<ActivityDTO> activities = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public LocalDate getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(LocalDate serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public LocalDate getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(LocalDate serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Set<DealDTO> getDeals() {
        return deals;
    }

    public void setDeals(Set<DealDTO> deals) {
        this.deals = deals;
    }

    public Set<PackDTO> getPackages() {
        return packages;
    }

    public void setPackages(Set<PackDTO> packs) {
        this.packages = packs;
    }

    public Set<AccomodationDTO> getAccomodations() {
        return accomodations;
    }

    public void setAccomodations(Set<AccomodationDTO> accomodations) {
        this.accomodations = accomodations;
    }

    public Set<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(Set<ActivityDTO> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceDTO)) {
            return false;
        }

        return id != null && id.equals(((ServiceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceDTO{" +
            "id=" + getId() +
            ", serviceDescription='" + getServiceDescription() + "'" +
            ", serviceStartDate='" + getServiceStartDate() + "'" +
            ", serviceEndDate='" + getServiceEndDate() + "'" +
            ", servicePrice=" + getServicePrice() +
            ", deals='" + getDeals() + "'" +
            ", packages='" + getPackages() + "'" +
            ", accomodations='" + getAccomodations() + "'" +
            ", activities='" + getActivities() + "'" +
            "}";
    }
}
