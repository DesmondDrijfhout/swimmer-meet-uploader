package com.test400.site.services;

import com.test400.site.models.Swimstyle;
import com.test400.site.repositories.SwimstyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SwimstyleService {

    @Autowired
    private SwimstyleRepository swimstyleRepository;
    @Autowired
    private AthleteService athleteService;

    public List<Swimstyle> findAll() {
        var swimstyles = swimstyleRepository.findAll();
        var swimstyleList = new ArrayList<Swimstyle>();
        swimstyles.forEach(swimstyleList::add);
        return swimstyleList;
    }

    public Iterable<Swimstyle> findSwimstylesByAthleteId(Long id) {
        return swimstyleRepository.findAllByAthletes_Id(id);
    }
}
