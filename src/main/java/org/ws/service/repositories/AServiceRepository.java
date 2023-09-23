package org.ws.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.ws.service.entities.AService;

@RepositoryRestResource
public interface AServiceRepository extends JpaRepository<AService, Long> {

}
