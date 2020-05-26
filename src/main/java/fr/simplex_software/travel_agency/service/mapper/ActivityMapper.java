package fr.simplex_software.travel_agency.service.mapper;


import fr.simplex_software.travel_agency.domain.*;
import fr.simplex_software.travel_agency.service.dto.ActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Activity} and its DTO {@link ActivityDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface ActivityMapper extends EntityMapper<ActivityDTO, Activity> {

    @Mapping(source = "location.id", target = "locationId")
    ActivityDTO toDto(Activity activity);

    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "removeContacts", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "removeServices", ignore = true)
    Activity toEntity(ActivityDTO activityDTO);

    default Activity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activity activity = new Activity();
        activity.setId(id);
        return activity;
    }
}
