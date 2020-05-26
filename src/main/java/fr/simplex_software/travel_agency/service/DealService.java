package fr.simplex_software.travel_agency.service;

import fr.simplex_software.travel_agency.domain.Deal;
import fr.simplex_software.travel_agency.repository.DealRepository;
import fr.simplex_software.travel_agency.service.dto.DealDTO;
import fr.simplex_software.travel_agency.service.mapper.DealMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Deal}.
 */
@Service
@Transactional
public class DealService {

    private final Logger log = LoggerFactory.getLogger(DealService.class);

    private final DealRepository dealRepository;

    private final DealMapper dealMapper;

    public DealService(DealRepository dealRepository, DealMapper dealMapper) {
        this.dealRepository = dealRepository;
        this.dealMapper = dealMapper;
    }

    /**
     * Save a deal.
     *
     * @param dealDTO the entity to save.
     * @return the persisted entity.
     */
    public DealDTO save(DealDTO dealDTO) {
        log.debug("Request to save Deal : {}", dealDTO);
        Deal deal = dealMapper.toEntity(dealDTO);
        deal = dealRepository.save(deal);
        return dealMapper.toDto(deal);
    }

    /**
     * Get all the deals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DealDTO> findAll() {
        log.debug("Request to get all Deals");
        return dealRepository.findAll().stream()
            .map(dealMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one deal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealDTO> findOne(Long id) {
        log.debug("Request to get Deal : {}", id);
        return dealRepository.findById(id)
            .map(dealMapper::toDto);
    }

    /**
     * Delete the deal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Deal : {}", id);

        dealRepository.deleteById(id);
    }
}
