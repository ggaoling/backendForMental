package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lid;
    private Integer num;
    private String description;
    private Integer sid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Integer getLid() {
        return lid;
    }

    public Integer getNum() {
        return num;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
