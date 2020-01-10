package com.test400.site.controllers;

import com.test400.site.models.Meet;
import com.test400.site.services.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeetController {

    @Autowired
    private MeetService meetService;

    @GetMapping("/meets")
    public List<Meet> allMeets() {
        return meetService.findAll();
    }

    @GetMapping("/meets/count")
    public Long count() {
        return meetService.count();
    }

    @DeleteMapping("/meets/{id}")
    public void delete(@PathVariable String id) {
        Long meetId = Long.parseLong(id);
        meetService.deleteById(meetId);
    }
}
