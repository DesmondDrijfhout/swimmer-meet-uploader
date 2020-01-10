package com.test400.site.repositories;

import com.test400.site.models.Meet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "meet")
public interface MeetRepository extends CrudRepository<Meet, Long> {
    Meet findByNameAndDate(String name, String date);
}
