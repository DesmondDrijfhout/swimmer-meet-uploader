package com.test400.site.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Swimstyle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String distance;
    private String relaycount;
    private String stroke;
    private String gender;

    @OneToOne()
    @JsonIgnore
    private Event event;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Athlete> athletes = new ArrayList<>();

    @OneToMany(mappedBy = "swimstyle")
    @JsonBackReference
    private List<Result> results;

    @ManyToMany()
    @JsonBackReference
    private List<Meet> meets = new ArrayList<>();

    public Swimstyle(){

    }

    public Swimstyle(String distance, String stroke, String gender) {
        this.distance = distance;
        this.stroke = stroke;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRelaycount() {
        return relaycount;
    }

    public void setRelaycount(String relaycount) {
        this.relaycount = relaycount;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
