package com.test400.site;

import com.test400.site.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.test400.site.services.XMLParser;

import javax.annotation.Resource;

@SpringBootApplication
public class SiteApplication implements CommandLineRunner {

    @Resource
    StorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
