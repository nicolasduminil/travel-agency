package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.service.TransportService;
import fr.simplex_software.travel_agency.web.rest.errors.BadRequestAlertException;
import fr.simplex_software.travel_agency.service.dto.TransportDTO;

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
 * REST controller for managing {@link fr.simplex_software.travel_agency.domain.Transport}.
 */
@RestController
@RequestMapping("/api")
public class TransportResource {

    private final Logger log = LoggerFactory.getLogger(TransportResource.class);

    private static final String ENTITY_NAME = "transport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransportService transportService;

    public TransportResource(TransportService transportService) {
        this.transportService = transportService;
    }

    /**
     * {@code POST  /transports} : Create a new transport.
     *
     * @param transportDTO the transportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transportDTO, or with status {@code 400 (Bad Request)} if the transport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transports")
    public ResponseEntity<TransportDTO> createTransport(@Valid @RequestBody TransportDTO transportDTO) throws URISyntaxException {
        log.debug("REST request to save Transport : {}", transportDTO);
        if (transportDTO.getId() != null) {
            throw new BadRequestAlertException("A new transport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransportDTO result = transportService.save(transportDTO);
        return ResponseEntity.created(new URI("/api/transports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transports} : Updates an existing transport.
     *
     * @param transportDTO the transportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transportDTO,
     * or with status {@code 400 (Bad Request)} if the transportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transports")
    public ResponseEntity<TransportDTO> updateTransport(@Valid @RequestBody TransportDTO transportDTO) throws URISyntaxException {
        log.debug("REST request to update Transport : {}", transportDTO);
        if (transportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransportDTO result = transportService.save(transportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transports} : get all the transports.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transports in body.
     */
    @GetMapping("/transports")
    public List<TransportDTO> getAllTransports(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Transports");
        return transportService.findAll();
    }

    /**
     * {@code GET  /transports/:id} : get the "id" transport.
     *
     * @param id the id of the transportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transports/{id}")
    public ResponseEntity<TransportDTO> getTransport(@PathVariable Long id) {
        log.debug("REST request to get Transport : {}", id);
        Optional<TransportDTO> transportDTO = transportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transportDTO);
    }

    /**
     * {@code DELETE  /transports/:id} : delete the "id" transport.
     *
     * @param id the id of the transportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transports/{id}")
    public ResponseEntity<Void> deleteTransport(@PathVariable Long id) {
        log.debug("REST request to delete Transport : {}", id);

        transportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
