package fr.simplex_software.travel_agency.service;

import fr.simplex_software.travel_agency.domain.Location;
import fr.simplex_software.travel_agency.repository.LocationRepository;
import fr.simplex_software.travel_agency.service.dto.LocationDTO;
import fr.simplex_software.travel_agency.service.mapper.LocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Location}.
 */
@Service
@Transactional
public class LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationService.class);

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    /**
     * Save a location.
     *
     * @param locationDTO the entity to save.
     * @return the persisted entity.
     */
    public LocationDTO save(LocationDTO locationDTO) {
        log.debug("Request to save Location : {}", locationDTO);
        Location location = locationMapper.toEntity(locationDTO);
        location = locationRepository.save(location);
        return locationMapper.toDto(location);
    }

    /**
     * Get all the locations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LocationDTO> findAll() {
        log.debug("Request to get all Locations");
        return locationRepository.findAll().stream()
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the locations where Accomodation is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereAccomodationIsNull() {
        log.debug("Request to get all locations where Accomodation is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getAccomodation() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the locations where Contact is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereContactIsNull() {
        log.debug("Request to get all locations where Contact is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getContact() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the locations where Activity is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereActivityIsNull() {
        log.debug("Request to get all locations where Activity is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getActivity() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the locations where TransportTo is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereTransportToIsNull() {
        log.debug("Request to get all locations where TransportTo is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getTransportTo() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the locations where TransportFrom is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereTransportFromIsNull() {
        log.debug("Request to get all locations where TransportFrom is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getTransportFrom() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one location by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LocationDTO> findOne(Long id) {
        log.debug("Request to get Location : {}", id);
        return locationRepository.findById(id)
            .map(locationMapper::toDto);
    }

    /**
     * Delete the location by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Location : {}", id);

        locationRepository.deleteById(id);
    }
}
