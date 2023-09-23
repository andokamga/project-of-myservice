package org.ws.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.ws.service.entities.Observation;

@RepositoryRestResource
public interface ObservationRepository extends JpaRepository<Observation, Long> {

}
