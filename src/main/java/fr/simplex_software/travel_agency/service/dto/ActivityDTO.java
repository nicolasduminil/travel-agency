package fr.simplex_software.travel_agency.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import fr.simplex_software.travel_agency.domain.enumeration.ActivityType;

/**
 * A DTO for the {@link fr.simplex_software.travel_agency.domain.Activity} entity.
 */
public class ActivityDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String activityDescription;

    @NotNull
    private ActivityType activityType;


    private Long locationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
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
        if (!(o instanceof ActivityDTO)) {
            return false;
        }

        return id != null && id.equals(((ActivityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActivityDTO{" +
            "id=" + getId() +
            ", activityDescription='" + getActivityDescription() + "'" +
            ", activityType='" + getActivityType() + "'" +
            ", locationId=" + getLocationId() +
            "}";
    }
}
