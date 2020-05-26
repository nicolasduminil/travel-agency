package fr.simplex_software.travel_agency.service.mapper;


import fr.simplex_software.travel_agency.domain.*;
import fr.simplex_software.travel_agency.service.dto.ServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Service} and its DTO {@link ServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealMapper.class, PackMapper.class, AccomodationMapper.class, ActivityMapper.class})
public interface ServiceMapper extends EntityMapper<ServiceDTO, Service> {


    @Mapping(target = "removeDeals", ignore = true)
    @Mapping(target = "removePackages", ignore = true)
    @Mapping(target = "removeAccomodations", ignore = true)
    @Mapping(target = "removeActivities", ignore = true)
    @Mapping(target = "transports", ignore = true)
    @Mapping(target = "removeTransport", ignore = true)
    Service toEntity(ServiceDTO serviceDTO);

    default Service fromId(Long id) {
        if (id == null) {
            return null;
        }
        Service service = new Service();
        service.setId(id);
        return service;
    }
}
