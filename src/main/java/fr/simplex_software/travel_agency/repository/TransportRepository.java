package fr.simplex_software.travel_agency.repository;

import fr.simplex_software.travel_agency.domain.Transport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Transport entity.
 */
@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    @Query(value = "select distinct transport from Transport transport left join fetch transport.services",
        countQuery = "select count(distinct transport) from Transport transport")
    Page<Transport> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct transport from Transport transport left join fetch transport.services")
    List<Transport> findAllWithEagerRelationships();

    @Query("select transport from Transport transport left join fetch transport.services where transport.id =:id")
    Optional<Transport> findOneWithEagerRelationships(@Param("id") Long id);
}
