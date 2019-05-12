package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "series")
public class Series {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer sid;
    @EmbeddedId
    private QidNSid id;
    private String name;
    private String description;
    private String isopen;
    private Integer average;
    private String needcount;
    private Integer visit;

//    @EmbeddedId
    public QidNSid getId() {
        return id;
    }

    public void setId(QidNSid id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }

    public Integer getAverage() {
        return average;
    }

    public Integer getVisit() {
        return visit;
    }

    public String getNeedcount() {
        return needcount;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public void setNeedcount(String needcount) {
        this.needcount = needcount;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }
}
