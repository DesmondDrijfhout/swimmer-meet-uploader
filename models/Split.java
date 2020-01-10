package com.test400.site.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Split {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String distance;
    private String swimtime;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    private Result result;

    public Split(){}

    public Split(String swimtime, String distance) {
        this.swimtime = swimtime;
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSwimtime() {
        return swimtime;
    }

    public void setSwimtime(String swimtime) {
        this.swimtime = swimtime;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
