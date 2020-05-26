package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.service.AccomodationService;
import fr.simplex_software.travel_agency.web.rest.errors.BadRequestAlertException;
import fr.simplex_software.travel_agency.service.dto.AccomodationDTO;

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
 * REST controller for managing {@link fr.simplex_software.travel_agency.domain.Accomodation}.
 */
@RestController
@RequestMapping("/api")
public class AccomodationResource {

    private final Logger log = LoggerFactory.getLogger(AccomodationResource.class);

    private static final String ENTITY_NAME = "accomodation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccomodationService accomodationService;

    public AccomodationResource(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }

    /**
     * {@code POST  /accomodations} : Create a new accomodation.
     *
     * @param accomodationDTO the accomodationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accomodationDTO, or with status {@code 400 (Bad Request)} if the accomodation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/accomodations")
    public ResponseEntity<AccomodationDTO> createAccomodation(@Valid @RequestBody AccomodationDTO accomodationDTO) throws URISyntaxException {
        log.debug("REST request to save Accomodation : {}", accomodationDTO);
        if (accomodationDTO.getId() != null) {
            throw new BadRequestAlertException("A new accomodation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccomodationDTO result = accomodationService.save(accomodationDTO);
        return ResponseEntity.created(new URI("/api/accomodations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /accomodations} : Updates an existing accomodation.
     *
     * @param accomodationDTO the accomodationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accomodationDTO,
     * or with status {@code 400 (Bad Request)} if the accomodationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accomodationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/accomodations")
    public ResponseEntity<AccomodationDTO> updateAccomodation(@Valid @RequestBody AccomodationDTO accomodationDTO) throws URISyntaxException {
        log.debug("REST request to update Accomodation : {}", accomodationDTO);
        if (accomodationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccomodationDTO result = accomodationService.save(accomodationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accomodationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /accomodations} : get all the accomodations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accomodations in body.
     */
    @GetMapping("/accomodations")
    public List<AccomodationDTO> getAllAccomodations() {
        log.debug("REST request to get all Accomodations");
        return accomodationService.findAll();
    }

    /**
     * {@code GET  /accomodations/:id} : get the "id" accomodation.
     *
     * @param id the id of the accomodationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accomodationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/accomodations/{id}")
    public ResponseEntity<AccomodationDTO> getAccomodation(@PathVariable Long id) {
        log.debug("REST request to get Accomodation : {}", id);
        Optional<AccomodationDTO> accomodationDTO = accomodationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accomodationDTO);
    }

    /**
     * {@code DELETE  /accomodations/:id} : delete the "id" accomodation.
     *
     * @param id the id of the accomodationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/accomodations/{id}")
    public ResponseEntity<Void> deleteAccomodation(@PathVariable Long id) {
        log.debug("REST request to delete Accomodation : {}", id);

        accomodationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
