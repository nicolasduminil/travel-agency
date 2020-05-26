package fr.simplex_software.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Pack.
 */
@Entity
@Table(name = "pack")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "package_name", nullable = false)
    private String packageName;

    @NotNull
    @Column(name = "package_description", nullable = false)
    private String packageDescription;

    @Column(name = "package_discount", precision = 21, scale = 2)
    private BigDecimal packageDiscount;

    @NotNull
    @Column(name = "package_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal packagePrice;

    @ManyToMany(mappedBy = "packages")
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

    public String getPackageName() {
        return packageName;
    }

    public Pack packageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public Pack packageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
        return this;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public BigDecimal getPackageDiscount() {
        return packageDiscount;
    }

    public Pack packageDiscount(BigDecimal packageDiscount) {
        this.packageDiscount = packageDiscount;
        return this;
    }

    public void setPackageDiscount(BigDecimal packageDiscount) {
        this.packageDiscount = packageDiscount;
    }

    public BigDecimal getPackagePrice() {
        return packagePrice;
    }

    public Pack packagePrice(BigDecimal packagePrice) {
        this.packagePrice = packagePrice;
        return this;
    }

    public void setPackagePrice(BigDecimal packagePrice) {
        this.packagePrice = packagePrice;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Pack services(Set<Service> services) {
        this.services = services;
        return this;
    }

    public Pack addServices(Service service) {
        this.services.add(service);
        service.getPackages().add(this);
        return this;
    }

    public Pack removeServices(Service service) {
        this.services.remove(service);
        service.getPackages().remove(this);
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
        if (!(o instanceof Pack)) {
            return false;
        }
        return id != null && id.equals(((Pack) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pack{" +
            "id=" + getId() +
            ", packageName='" + getPackageName() + "'" +
            ", packageDescription='" + getPackageDescription() + "'" +
            ", packageDiscount=" + getPackageDiscount() +
            ", packagePrice=" + getPackagePrice() +
            "}";
    }
}
