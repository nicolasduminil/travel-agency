package fr.simplex_software.travel_agency.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import fr.simplex_software.travel_agency.domain.enumeration.AccomodationType;
import fr.simplex_software.travel_agency.domain.enumeration.AccomodationClass;

/**
 * A DTO for the {@link fr.simplex_software.travel_agency.domain.Accomodation} entity.
 */
public class AccomodationDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String accomodationName;

    @NotNull
    private AccomodationType accomodationType;

    @NotNull
    private AccomodationClass accomodationClass;


    private Long locationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccomodationName() {
        return accomodationName;
    }

    public void setAccomodationName(String accomodationName) {
        this.accomodationName = accomodationName;
    }

    public AccomodationType getAccomodationType() {
        return accomodationType;
    }

    public void setAccomodationType(AccomodationType accomodationType) {
        this.accomodationType = accomodationType;
    }

    public AccomodationClass getAccomodationClass() {
        return accomodationClass;
    }

    public void setAccomodationClass(AccomodationClass accomodationClass) {
        this.accomodationClass = accomodationClass;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccomodationDTO)) {
            return false;
        }

        return id != null && id.equals(((AccomodationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccomodationDTO{" +
            "id=" + getId() +
            ", accomodationName='" + getAccomodationName() + "'" +
            ", accomodationType='" + getAccomodationType() + "'" +
            ", accomodationClass='" + getAccomodationClass() + "'" +
            ", locationId=" + getLocationId() +
            "}";
    }
}
