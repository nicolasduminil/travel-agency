package fr.simplex_software.travel_agency.service;

import fr.simplex_software.travel_agency.domain.Accomodation;
import fr.simplex_software.travel_agency.repository.AccomodationRepository;
import fr.simplex_software.travel_agency.service.dto.AccomodationDTO;
import fr.simplex_software.travel_agency.service.mapper.AccomodationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Accomodation}.
 */
@Service
@Transactional
public class AccomodationService {

    private final Logger log = LoggerFactory.getLogger(AccomodationService.class);

    private final AccomodationRepository accomodationRepository;

    private final AccomodationMapper accomodationMapper;

    public AccomodationService(AccomodationRepository accomodationRepository, AccomodationMapper accomodationMapper) {
        this.accomodationRepository = accomodationRepository;
        this.accomodationMapper = accomodationMapper;
    }

    /**
     * Save a accomodation.
     *
     * @param accomodationDTO the entity to save.
     * @return the persisted entity.
     */
    public AccomodationDTO save(AccomodationDTO accomodationDTO) {
        log.debug("Request to save Accomodation : {}", accomodationDTO);
        Accomodation accomodation = accomodationMapper.toEntity(accomodationDTO);
        accomodation = accomodationRepository.save(accomodation);
        return accomodationMapper.toDto(accomodation);
    }

    /**
     * Get all the accomodations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AccomodationDTO> findAll() {
        log.debug("Request to get all Accomodations");
        return accomodationRepository.findAll().stream()
            .map(accomodationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one accomodation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AccomodationDTO> findOne(Long id) {
        log.debug("Request to get Accomodation : {}", id);
        return accomodationRepository.findById(id)
            .map(accomodationMapper::toDto);
    }

    /**
     * Delete the accomodation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Accomodation : {}", id);

        accomodationRepository.deleteById(id);
    }
}
