package com.test400.site.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String eventid;
    private String gender;
    private String number;

    @OneToOne
    @JsonIgnore
    private Swimstyle swimstyle;

    @ManyToOne
    @JsonIgnore
    private Meet meet;

    public Event(){}

    public Event(String eventid, String gender, String number, Meet meet, Swimstyle swimstyle){
        this.eventid = eventid;
        this.gender = gender;
        this.number = number;
        this.meet = meet;
        this.swimstyle = swimstyle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Swimstyle getSwimstyle() {
        return swimstyle;
    }

    public void setSwimstyle(Swimstyle swimstyle) {
        this.swimstyle = swimstyle;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Meet getMeet() {
        return meet;
    }

    public void setMeet(Meet meet) {
        this.meet = meet;
    }
}
