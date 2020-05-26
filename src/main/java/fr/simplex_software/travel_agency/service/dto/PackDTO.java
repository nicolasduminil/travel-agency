package fr.simplex_software.travel_agency.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link fr.simplex_software.travel_agency.domain.Pack} entity.
 */
public class PackDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String packageName;

    @NotNull
    private String packageDescription;

    private BigDecimal packageDiscount;

    @NotNull
    private BigDecimal packagePrice;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public BigDecimal getPackageDiscount() {
        return packageDiscount;
    }

    public void setPackageDiscount(BigDecimal packageDiscount) {
        this.packageDiscount = packageDiscount;
    }

    public BigDecimal getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(BigDecimal packagePrice) {
        this.packagePrice = packagePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PackDTO)) {
            return false;
        }

        return id != null && id.equals(((PackDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PackDTO{" +
            "id=" + getId() +
            ", packageName='" + getPackageName() + "'" +
            ", packageDescription='" + getPackageDescription() + "'" +
            ", packageDiscount=" + getPackageDiscount() +
            ", packagePrice=" + getPackagePrice() +
            "}";
    }
}
