package fr.simplex_software.travel_agency.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import fr.simplex_software.travel_agency.domain.enumeration.Salutation;

/**
 * A DTO for the {@link fr.simplex_software.travel_agency.domain.Contact} entity.
 */
public class ContactDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String contactName;

    @NotNull
    private String contactFirstName;

    @NotNull
    private String contactLastName;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    private String contactEmailAddress;

    @NotNull
    private String contactWebSite;

    @NotNull
    private Salutation contactSalutation;

    @NotNull
    private String contactJobTitle;

    @NotNull
    private String contactPhoneNumber;

    private String contactFaxNumber;


    private Long addressId;

    private Long activityId;

    private Long customerId;

    private Long dealId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public String getContactWebSite() {
        return contactWebSite;
    }

    public void setContactWebSite(String contactWebSite) {
        this.contactWebSite = contactWebSite;
    }

    public Salutation getContactSalutation() {
        return contactSalutation;
    }

    public void setContactSalutation(Salutation contactSalutation) {
        this.contactSalutation = contactSalutation;
    }

    public String getContactJobTitle() {
        return contactJobTitle;
    }

    public void setContactJobTitle(String contactJobTitle) {
        this.contactJobTitle = contactJobTitle;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactFaxNumber() {
        return contactFaxNumber;
    }

    public void setContactFaxNumber(String contactFaxNumber) {
        this.contactFaxNumber = contactFaxNumber;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long locationId) {
        this.addressId = locationId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactDTO)) {
            return false;
        }

        return id != null && id.equals(((ContactDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + getId() +
            ", contactName='" + getContactName() + "'" +
            ", contactFirstName='" + getContactFirstName() + "'" +
            ", contactLastName='" + getContactLastName() + "'" +
            ", contactEmailAddress='" + getContactEmailAddress() + "'" +
            ", contactWebSite='" + getContactWebSite() + "'" +
            ", contactSalutation='" + getContactSalutation() + "'" +
            ", contactJobTitle='" + getContactJobTitle() + "'" +
            ", contactPhoneNumber='" + getContactPhoneNumber() + "'" +
            ", contactFaxNumber='" + getContactFaxNumber() + "'" +
            ", addressId=" + getAddressId() +
            ", activityId=" + getActivityId() +
            ", customerId=" + getCustomerId() +
            ", dealId=" + getDealId() +
            "}";
    }
}
