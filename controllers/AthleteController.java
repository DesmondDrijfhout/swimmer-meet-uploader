package com.test400.site.controllers;

import com.sun.tools.jconsole.JConsoleContext;
import com.test400.site.models.Athlete;
import com.test400.site.models.Result;
import com.test400.site.models.Swimstyle;
import com.test400.site.services.AthleteService;
import com.test400.site.services.ResultService;
import com.test400.site.services.SwimstyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/athletes")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private SwimstyleService swimstyleService;

    @GetMapping("")
    public List<Athlete>  allAthletes() {
        return athleteService.findAll();
    }

    @GetMapping("/{id}")
    public Athlete getAthlete(@PathVariable("id") String idString) {
        Long id = Long.parseLong(idString);
        return athleteService.findAthleteById(id);
    }

    @GetMapping("/count")
    public Long count() {
        return athleteService.count();
    }

    @GetMapping("/findbylastname")
    public Athlete findByLastName(@RequestParam("lastname") String lastname) {
        return athleteService.findAthleteByLastname(lastname);
    }

    @GetMapping("/findbylicense")
    public Athlete findByLicense(@RequestParam("license") String license) {
        return athleteService.findAthleteByLicense(license);
    }

    @GetMapping("/{id}/results")
    public List<Result> getResults(@PathVariable("id") String idString) {
        Long id = Long.parseLong(idString);
        return resultService.findResultsByAthleteId(id);
    }

    @GetMapping("/{id}/swimstyles")
    public Iterable<Swimstyle> getSwimstyles(@PathVariable("id") String idString) {
        Long id = Long.parseLong(idString);
        return swimstyleService.findSwimstylesByAthleteId(id);
    }



    @PostMapping("")
    public void add(@RequestBody Athlete athlete) {
        athleteService.add(athlete);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String idString) {
        Long id = Long.parseLong(idString);
        athleteService.deleteById(id);
    }
}
