package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.service.DealService;
import fr.simplex_software.travel_agency.web.rest.errors.BadRequestAlertException;
import fr.simplex_software.travel_agency.service.dto.DealDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.simplex_software.travel_agency.domain.Deal}.
 */
@RestController
@RequestMapping("/api")
public class DealResource {

    private final Logger log = LoggerFactory.getLogger(DealResource.class);

    private static final String ENTITY_NAME = "deal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealService dealService;

    public DealResource(DealService dealService) {
        this.dealService = dealService;
    }

    /**
     * {@code POST  /deals} : Create a new deal.
     *
     * @param dealDTO the dealDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealDTO, or with status {@code 400 (Bad Request)} if the deal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deals")
    public ResponseEntity<DealDTO> createDeal(@Valid @RequestBody DealDTO dealDTO) throws URISyntaxException {
        log.debug("REST request to save Deal : {}", dealDTO);
        if (dealDTO.getId() != null) {
            throw new BadRequestAlertException("A new deal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealDTO result = dealService.save(dealDTO);
        return ResponseEntity.created(new URI("/api/deals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deals} : Updates an existing deal.
     *
     * @param dealDTO the dealDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealDTO,
     * or with status {@code 400 (Bad Request)} if the dealDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deals")
    public ResponseEntity<DealDTO> updateDeal(@Valid @RequestBody DealDTO dealDTO) throws URISyntaxException {
        log.debug("REST request to update Deal : {}", dealDTO);
        if (dealDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealDTO result = dealService.save(dealDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dealDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deals} : get all the deals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deals in body.
     */
    @GetMapping("/deals")
    public List<DealDTO> getAllDeals() {
        log.debug("REST request to get all Deals");
        return dealService.findAll();
    }

    /**
     * {@code GET  /deals/:id} : get the "id" deal.
     *
     * @param id the id of the dealDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deals/{id}")
    public ResponseEntity<DealDTO> getDeal(@PathVariable Long id) {
        log.debug("REST request to get Deal : {}", id);
        Optional<DealDTO> dealDTO = dealService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dealDTO);
    }

    /**
     * {@code DELETE  /deals/:id} : delete the "id" deal.
     *
     * @param id the id of the dealDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deals/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable Long id) {
        log.debug("REST request to delete Deal : {}", id);

        dealService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
