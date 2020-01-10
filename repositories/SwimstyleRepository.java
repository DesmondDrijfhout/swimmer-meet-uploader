package com.test400.site.repositories;

import com.test400.site.models.Athlete;
import com.test400.site.models.Swimstyle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "swimstyle")
public interface SwimstyleRepository extends CrudRepository<Swimstyle, Long> {
    Swimstyle findSwimstyleByDistanceAndStrokeAndGender(String distance, String stroke, String gender);
    List<Swimstyle> findAllByAthletes_Id(Long id);
}
