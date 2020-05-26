package fr.simplex_software.travel_agency.repository;

import fr.simplex_software.travel_agency.domain.Pack;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Pack entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PackRepository extends JpaRepository<Pack, Long> {
}
