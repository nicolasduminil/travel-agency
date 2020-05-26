package fr.simplex_software.travel_agency.repository;

import fr.simplex_software.travel_agency.domain.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Service entity.
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query(value = "select distinct service from Service service left join fetch service.deals left join fetch service.packages left join fetch service.accomodations left join fetch service.activities",
        countQuery = "select count(distinct service) from Service service")
    Page<Service> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct service from Service service left join fetch service.deals left join fetch service.packages left join fetch service.accomodations left join fetch service.activities")
    List<Service> findAllWithEagerRelationships();

    @Query("select service from Service service left join fetch service.deals left join fetch service.packages left join fetch service.accomodations left join fetch service.activities where service.id =:id")
    Optional<Service> findOneWithEagerRelationships(@Param("id") Long id);
}
