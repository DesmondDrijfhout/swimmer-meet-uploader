package com.test400.site.repositories;

import com.test400.site.models.Split;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "split")
public interface SplitRepository extends CrudRepository<Split, Long> {
}
