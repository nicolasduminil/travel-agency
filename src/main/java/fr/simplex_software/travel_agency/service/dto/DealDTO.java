package fr.simplex_software.travel_agency.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.simplex_software.travel_agency.domain.Deal} entity.
 */
public class DealDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String dealName;

    @NotNull
    private LocalDate dealBookDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public LocalDate getDealBookDate() {
        return dealBookDate;
    }

    public void setDealBookDate(LocalDate dealBookDate) {
        this.dealBookDate = dealBookDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealDTO)) {
            return false;
        }

        return id != null && id.equals(((DealDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealDTO{" +
            "id=" + getId() +
            ", dealName='" + getDealName() + "'" +
            ", dealBookDate='" + getDealBookDate() + "'" +
            "}";
    }
}
