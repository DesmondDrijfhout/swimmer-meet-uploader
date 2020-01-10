package com.test400.site.repositories;

import com.test400.site.models.TestClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<TestClass, Long> {
}
