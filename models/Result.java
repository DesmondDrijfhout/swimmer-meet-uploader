package com.test400.site.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Long swimtime;

    @ManyToOne
    @JsonManagedReference
    private Swimstyle swimstyle;

    @OneToMany
    @JsonIgnore
    private List<Split> splits = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Athlete athlete;

    @ManyToOne
    @JsonIgnore
    private Meet meet;

    public Result(){}

    public Result(Long swimtime, Athlete athlete) {
        this.swimtime = swimtime;
        this.athlete = athlete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSwimtime() {
        return swimtime;
    }

    public void setSwimtime(Long swimtime) {
        this.swimtime = swimtime;
    }

    public Swimstyle getSwimstyle() {
        return swimstyle;
    }

    public void setSwimstyle(Swimstyle swimstyle) {
        this.swimstyle = swimstyle;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Meet getMeet() {
        return meet;
    }

    public void setMeet(Meet meet) {
        this.meet = meet;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }
}
