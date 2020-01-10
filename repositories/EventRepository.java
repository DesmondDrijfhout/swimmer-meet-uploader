package com.test400.site.repositories;


import com.test400.site.models.Event;
import com.test400.site.models.Meet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "event")
public interface EventRepository extends CrudRepository<Event, Long> {
    Event findByEventidAndMeet(String eventid, Meet meet);
}
