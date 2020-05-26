package fr.simplex_software.travel_agency.service.mapper;


import fr.simplex_software.travel_agency.domain.*;
import fr.simplex_software.travel_agency.service.dto.AccomodationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Accomodation} and its DTO {@link AccomodationDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface AccomodationMapper extends EntityMapper<AccomodationDTO, Accomodation> {

    @Mapping(source = "location.id", target = "locationId")
    AccomodationDTO toDto(Accomodation accomodation);

    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "removeServices", ignore = true)
    Accomodation toEntity(AccomodationDTO accomodationDTO);

    default Accomodation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Accomodation accomodation = new Accomodation();
        accomodation.setId(id);
        return accomodation;
    }
}
