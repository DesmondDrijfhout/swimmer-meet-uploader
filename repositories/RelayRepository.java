package com.test400.site.repositories;

import com.test400.site.models.Relay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayRepository extends JpaRepository<Relay, Long> {
}
