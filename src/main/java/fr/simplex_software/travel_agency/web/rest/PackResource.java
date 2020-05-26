package fr.simplex_software.travel_agency.web.rest;

import fr.simplex_software.travel_agency.domain.Pack;
import fr.simplex_software.travel_agency.repository.PackRepository;
import fr.simplex_software.travel_agency.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.simplex_software.travel_agency.domain.Pack}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PackResource {

    private final Logger log = LoggerFactory.getLogger(PackResource.class);

    private static final String ENTITY_NAME = "pack";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PackRepository packRepository;

    public PackResource(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    /**
     * {@code POST  /packs} : Create a new pack.
     *
     * @param pack the pack to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pack, or with status {@code 400 (Bad Request)} if the pack has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/packs")
    public ResponseEntity<Pack> createPack(@Valid @RequestBody Pack pack) throws URISyntaxException {
        log.debug("REST request to save Pack : {}", pack);
        if (pack.getId() != null) {
            throw new BadRequestAlertException("A new pack cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Pack result = packRepository.save(pack);
        return ResponseEntity.created(new URI("/api/packs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /packs} : Updates an existing pack.
     *
     * @param pack the pack to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pack,
     * or with status {@code 400 (Bad Request)} if the pack is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pack couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/packs")
    public ResponseEntity<Pack> updatePack(@Valid @RequestBody Pack pack) throws URISyntaxException {
        log.debug("REST request to update Pack : {}", pack);
        if (pack.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Pack result = packRepository.save(pack);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pack.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /packs} : get all the packs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of packs in body.
     */
    @GetMapping("/packs")
    public List<Pack> getAllPacks() {
        log.debug("REST request to get all Packs");
        return packRepository.findAll();
    }

    /**
     * {@code GET  /packs/:id} : get the "id" pack.
     *
     * @param id the id of the pack to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pack, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/packs/{id}")
    public ResponseEntity<Pack> getPack(@PathVariable Long id) {
        log.debug("REST request to get Pack : {}", id);
        Optional<Pack> pack = packRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pack);
    }

    /**
     * {@code DELETE  /packs/:id} : delete the "id" pack.
     *
     * @param id the id of the pack to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/packs/{id}")
    public ResponseEntity<Void> deletePack(@PathVariable Long id) {
        log.debug("REST request to delete Pack : {}", id);

        packRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
