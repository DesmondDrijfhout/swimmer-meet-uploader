package com.test400.site.services;

import com.test400.site.repositories.SplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SplitService {

    @Autowired
    private SplitRepository splitRepository;

    public Long count() {
        return splitRepository.count();
    }
}
