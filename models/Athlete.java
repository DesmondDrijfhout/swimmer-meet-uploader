package com.test400.site.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String athleteid;
    private String firstname;
    private String lastname;
    private String gender;
    private String license;
    private String birthdate;

    @ManyToMany(mappedBy = "athletes")
    @JsonIgnore
    private List<Swimstyle> swimstyles;

    @OneToMany(mappedBy = "athlete")
    @JsonIgnore
    private List<Result> results;

    @ManyToMany(mappedBy = "athletes")
    @JsonIgnore
    private List<Meet> meets;

    public Athlete(){
    }

    public Athlete(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Athlete(String athleteid, String firstname, String lastname, String gender, String license, String birthdate){
        this.firstname = firstname;
        this.lastname = lastname;
        this.athleteid = athleteid;
        this.gender = gender;
        this.license = license;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAthleteid() {
        return athleteid;
    }

    public void setAthleteid(String athleteid) {
        this.athleteid = athleteid;
    }

    public List<Swimstyle> getSwimstyles() {
        return swimstyles;
    }

    public void setSwimstyles(List<Swimstyle> swimstyles) {
        this.swimstyles = swimstyles;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Meet> getMeets() {
        return meets;
    }

    public void setMeets(List<Meet> meets) {
        this.meets = meets;
    }
}
