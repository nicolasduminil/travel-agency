package fr.simplex_software.travel_agency.service;

import fr.simplex_software.travel_agency.domain.Transport;
import fr.simplex_software.travel_agency.repository.TransportRepository;
import fr.simplex_software.travel_agency.service.dto.TransportDTO;
import fr.simplex_software.travel_agency.service.mapper.TransportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Transport}.
 */
@Service
@Transactional
public class TransportService {

    private final Logger log = LoggerFactory.getLogger(TransportService.class);

    private final TransportRepository transportRepository;

    private final TransportMapper transportMapper;

    public TransportService(TransportRepository transportRepository, TransportMapper transportMapper) {
        this.transportRepository = transportRepository;
        this.transportMapper = transportMapper;
    }

    /**
     * Save a transport.
     *
     * @param transportDTO the entity to save.
     * @return the persisted entity.
     */
    public TransportDTO save(TransportDTO transportDTO) {
        log.debug("Request to save Transport : {}", transportDTO);
        Transport transport = transportMapper.toEntity(transportDTO);
        transport = transportRepository.save(transport);
        return transportMapper.toDto(transport);
    }

    /**
     * Get all the transports.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TransportDTO> findAll() {
        log.debug("Request to get all Transports");
        return transportRepository.findAllWithEagerRelationships().stream()
            .map(transportMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get all the transports with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<TransportDTO> findAllWithEagerRelationships(Pageable pageable) {
        return transportRepository.findAllWithEagerRelationships(pageable).map(transportMapper::toDto);
    }

    /**
     * Get one transport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TransportDTO> findOne(Long id) {
        log.debug("Request to get Transport : {}", id);
        return transportRepository.findOneWithEagerRelationships(id)
            .map(transportMapper::toDto);
    }

    /**
     * Delete the transport by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Transport : {}", id);

        transportRepository.deleteById(id);
    }
}
