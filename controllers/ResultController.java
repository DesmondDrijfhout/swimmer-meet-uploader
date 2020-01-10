package com.test400.site.controllers;

import com.test400.site.models.Result;
import com.test400.site.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("")
    public List<Result> allResults() {
        return resultService.findAll();
    }

    @GetMapping("/count")
    public Long count(){
        return resultService.count();
    }
}
