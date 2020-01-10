package com.test400.site.repositories;
import com.test400.site.models.Athlete;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "athlete")
public interface AthleteRepository extends CrudRepository<Athlete, Long> {
    Athlete findByFirstname(String firstname);
    Athlete findByLicense(String licence);
    Athlete findByLastname(String lastname);
}
