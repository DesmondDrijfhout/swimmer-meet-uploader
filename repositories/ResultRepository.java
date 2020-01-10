package com.test400.site.repositories;

import com.test400.site.models.Athlete;
import com.test400.site.models.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(path = "result")
public interface ResultRepository extends CrudRepository<Result, Long> {
//    @Query("select r from Result r join r.athlete as a where a.id = :id")
    List<Result> findAllByAthlete_Id(Long id);
}
