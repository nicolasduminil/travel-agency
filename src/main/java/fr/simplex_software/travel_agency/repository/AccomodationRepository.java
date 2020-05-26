package fr.simplex_software.travel_agency.repository;

import fr.simplex_software.travel_agency.domain.Accomodation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Accomodation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {
}
