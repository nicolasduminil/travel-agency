package fr.simplex_software.travel_agency.service.mapper;


import fr.simplex_software.travel_agency.domain.*;
import fr.simplex_software.travel_agency.service.dto.DealDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Deal} and its DTO {@link DealDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DealMapper extends EntityMapper<DealDTO, Deal> {


    @Mapping(target = "agents", ignore = true)
    @Mapping(target = "removeAgents", ignore = true)
    @Mapping(target = "customers", ignore = true)
    @Mapping(target = "removeCustomers", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "removeServices", ignore = true)
    Deal toEntity(DealDTO dealDTO);

    default Deal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Deal deal = new Deal();
        deal.setId(id);
        return deal;
    }
}
