package fr.simplex_software.travel_agency.service;

import fr.simplex_software.travel_agency.domain.Pack;
import fr.simplex_software.travel_agency.repository.PackRepository;
import fr.simplex_software.travel_agency.service.dto.PackDTO;
import fr.simplex_software.travel_agency.service.mapper.PackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Pack}.
 */
@Service
@Transactional
public class PackService {

    private final Logger log = LoggerFactory.getLogger(PackService.class);

    private final PackRepository packRepository;

    private final PackMapper packMapper;

    public PackService(PackRepository packRepository, PackMapper packMapper) {
        this.packRepository = packRepository;
        this.packMapper = packMapper;
    }

    /**
     * Save a pack.
     *
     * @param packDTO the entity to save.
     * @return the persisted entity.
     */
    public PackDTO save(PackDTO packDTO) {
        log.debug("Request to save Pack : {}", packDTO);
        Pack pack = packMapper.toEntity(packDTO);
        pack = packRepository.save(pack);
        return packMapper.toDto(pack);
    }

    /**
     * Get all the packs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PackDTO> findAll() {
        log.debug("Request to get all Packs");
        return packRepository.findAll().stream()
            .map(packMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one pack by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PackDTO> findOne(Long id) {
        log.debug("Request to get Pack : {}", id);
        return packRepository.findById(id)
            .map(packMapper::toDto);
    }

    /**
     * Delete the pack by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Pack : {}", id);

        packRepository.deleteById(id);
    }
}
