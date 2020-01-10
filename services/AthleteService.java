package com.test400.site.services;

import com.test400.site.models.Athlete;
import com.test400.site.models.Result;
import com.test400.site.repositories.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    public List<Athlete> findAll(){
        var athletes = athleteRepository.findAll();
        var athletesList = new ArrayList<Athlete>();
        athletes.forEach(athletesList::add);
        return athletesList;
    }

    public Athlete findAthleteByLicence(String licence) {
        return athleteRepository.findByLicense(licence);
    }

    public Long count() {
        return athleteRepository.count();
    }

    public void deleteById(Long id) {
        athleteRepository.deleteById(id);
    }

    public void add(Athlete athlete) {
        athleteRepository.save(athlete);
    }

    public Athlete findAthleteByLastname(String lastname) {
        return athleteRepository.findByLastname(lastname);
    }

    public Athlete findAthleteByLicense(String license) {
        return athleteRepository.findByLicense(license);
    }

    public Athlete findAthleteById(Long id) {
        Athlete athlete = athleteRepository.findById(id).orElseThrow();
        return athlete;
    }
}
