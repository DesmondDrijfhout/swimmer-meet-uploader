package com.test400.site.services;

import com.test400.site.models.Result;
import com.test400.site.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private AthleteService athleteService;

    public List<Result> findAll() {
        var results = resultRepository.findAll();
        var resultsList =  new ArrayList<Result>();
        results.forEach(resultsList::add);
        return resultsList;
    }

    public Long count() {
        return resultRepository.count();
    }

    public List<Result> findResultsByAthleteId(Long id) {
        return resultRepository.findAllByAthlete_Id(id);
    }
}
