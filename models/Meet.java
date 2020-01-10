package com.test400.site.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;
    private String city;
    private String date;
    private String course;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonBackReference
    private List<Athlete> athletes = new ArrayList<>();

    @OneToMany()
    @JsonBackReference
    @JsonIgnore
    private List<Event> events;

    @OneToMany(mappedBy = "meet")
    @JsonBackReference
    @JsonIgnore
    private List<Result> results;

    @ManyToMany(mappedBy = "meets")
    @JsonManagedReference
    @JsonIgnore
    private List<Swimstyle> swimstyles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Swimstyle> getSwimstyles() {
        return swimstyles;
    }

    public void setSwimstyles(List<Swimstyle> swimstyles) {
        this.swimstyles = swimstyles;
    }

    @Override
    public String toString(){
        return getName();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
