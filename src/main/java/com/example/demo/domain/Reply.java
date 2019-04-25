package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name="reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;
    private Integer aid;
    private String talk;
    private String hasread;

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public void setHasread(String hasread) {
        this.hasread = hasread;
    }

    public String getTalk() {
        return talk;
    }

    public String getHasread() {
        return hasread;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
