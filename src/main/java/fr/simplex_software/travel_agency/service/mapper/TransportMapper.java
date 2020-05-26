package fr.simplex_software.travel_agency.service.mapper;


import fr.simplex_software.travel_agency.domain.*;
import fr.simplex_software.travel_agency.service.dto.TransportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transport} and its DTO {@link TransportDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, ServiceMapper.class})
public interface TransportMapper extends EntityMapper<TransportDTO, Transport> {

    @Mapping(source = "to.id", target = "toId")
    @Mapping(source = "from.id", target = "fromId")
    TransportDTO toDto(Transport transport);

    @Mapping(source = "toId", target = "to")
    @Mapping(source = "fromId", target = "from")
    @Mapping(target = "removeService", ignore = true)
    Transport toEntity(TransportDTO transportDTO);

    default Transport fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transport transport = new Transport();
        transport.setId(id);
        return transport;
    }
}
