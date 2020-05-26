package fr.simplex_software.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.simplex_software.travel_agency.domain.enumeration.ActivityType;

/**
 * A Activity.
 */
@Entity
@Table(name = "activity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "activity_description", nullable = false)
    private String activityDescription;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @OneToMany(mappedBy = "activity")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Contact> contacts = new HashSet<>();

    @ManyToMany(mappedBy = "activities")
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

    public String getActivityDescription() {
        return activityDescription;
    }

    public Activity activityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
        return this;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public Activity activityType(ActivityType activityType) {
        this.activityType = activityType;
        return this;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public Location getLocation() {
        return location;
    }

    public Activity location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public Activity contacts(Set<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public Activity addContacts(Contact contact) {
        this.contacts.add(contact);
        contact.setActivity(this);
        return this;
    }

    public Activity removeContacts(Contact contact) {
        this.contacts.remove(contact);
        contact.setActivity(null);
        return this;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Activity services(Set<Service> services) {
        this.services = services;
        return this;
    }

    public Activity addServices(Service service) {
        this.services.add(service);
        service.getActivities().add(this);
        return this;
    }

    public Activity removeServices(Service service) {
        this.services.remove(service);
        service.getActivities().remove(this);
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
        if (!(o instanceof Activity)) {
            return false;
        }
        return id != null && id.equals(((Activity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Activity{" +
            "id=" + getId() +
            ", activityDescription='" + getActivityDescription() + "'" +
            ", activityType='" + getActivityType() + "'" +
            "}";
    }
}
