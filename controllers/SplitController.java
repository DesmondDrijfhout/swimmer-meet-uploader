package com.test400.site.controllers;

import com.test400.site.services.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/splits")
public class SplitController {

    @Autowired
    private SplitService splitService;

    @GetMapping("/count")
    public Long count(){
        return splitService.count();
    }
}
