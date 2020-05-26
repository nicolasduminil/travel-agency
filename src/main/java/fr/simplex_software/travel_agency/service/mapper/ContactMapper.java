package fr.simplex_software.travel_agency.service.mapper;


import fr.simplex_software.travel_agency.domain.*;
import fr.simplex_software.travel_agency.service.dto.ContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contact} and its DTO {@link ContactDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, ActivityMapper.class, CustomerMapper.class, DealMapper.class})
public interface ContactMapper extends EntityMapper<ContactDTO, Contact> {

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "activity.id", target = "activityId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "deal.id", target = "dealId")
    ContactDTO toDto(Contact contact);

    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "activityId", target = "activity")
    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "dealId", target = "deal")
    Contact toEntity(ContactDTO contactDTO);

    default Contact fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
}
