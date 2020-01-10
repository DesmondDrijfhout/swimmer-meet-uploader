package com.test400.site.controllers;

import com.test400.site.models.Swimstyle;
import com.test400.site.services.SwimstyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/swimstyles")
public class SwimstyleController {

    @Autowired
    private SwimstyleService swimstyleService;

    @GetMapping("")
    public List<Swimstyle> allSwimstyles(){
        return swimstyleService.findAll();
    }

}
