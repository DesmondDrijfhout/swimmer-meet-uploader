package com.test400.site.services;

import com.test400.site.models.Meet;
import com.test400.site.repositories.MeetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetService {

    @Autowired
    private MeetRepository meetRepository;

    @Autowired
    private XMLParser xmlParser;

    public List<Meet> findAll() {
        var it = meetRepository.findAll();
        var meets = new ArrayList<Meet>();
        it.forEach(meets::add);

        return meets;
    }

    public Meet findMeetByNameAndDate(String name, String date) {
        return meetRepository.findByNameAndDate(name, date);
    }

    public void addMeet(MultipartFile file) {
        xmlParser.createMeetFromFile(file);
    }


    public Long count() {
        return meetRepository.count();
    }

    public void deleteById(Long meetId) {
        meetRepository.deleteById(meetId);
    }
}
