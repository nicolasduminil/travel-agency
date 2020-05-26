package fr.simplex_software.travel_agency.service.mapper;


import fr.simplex_software.travel_agency.domain.*;
import fr.simplex_software.travel_agency.service.dto.PackDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pack} and its DTO {@link PackDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PackMapper extends EntityMapper<PackDTO, Pack> {


    @Mapping(target = "services", ignore = true)
    @Mapping(target = "removeServices", ignore = true)
    Pack toEntity(PackDTO packDTO);

    default Pack fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pack pack = new Pack();
        pack.setId(id);
        return pack;
    }
}
