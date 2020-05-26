package fr.simplex_software.travel_agency.service.mapper;


import fr.simplex_software.travel_agency.domain.*;
import fr.simplex_software.travel_agency.service.dto.LocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Location} and its DTO {@link LocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface LocationMapper extends EntityMapper<LocationDTO, Location> {

    @Mapping(source = "customer.id", target = "customerId")
    LocationDTO toDto(Location location);

    @Mapping(target = "accomodation", ignore = true)
    @Mapping(target = "contact", ignore = true)
    @Mapping(target = "activity", ignore = true)
    @Mapping(target = "transportTo", ignore = true)
    @Mapping(target = "transportFrom", ignore = true)
    @Mapping(source = "customerId", target = "customer")
    Location toEntity(LocationDTO locationDTO);

    default Location fromId(Long id) {
        if (id == null) {
            return null;
        }
        Location location = new Location();
        location.setId(id);
        return location;
    }
}
