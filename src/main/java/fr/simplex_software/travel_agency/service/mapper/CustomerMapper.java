package fr.simplex_software.travel_agency.service.mapper;


import fr.simplex_software.travel_agency.domain.*;
import fr.simplex_software.travel_agency.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    @Mapping(source = "deal.id", target = "dealId")
    CustomerDTO toDto(Customer customer);

    @Mapping(target = "locations", ignore = true)
    @Mapping(target = "removeLocations", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "removeContacts", ignore = true)
    @Mapping(source = "dealId", target = "deal")
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
