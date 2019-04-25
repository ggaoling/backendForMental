package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "ask")
public class Ask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;
    private Integer uid;
    private String talk;
    private String hasread;
    @Transient
    private String reply;

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getAid() {
        return aid;
    }

    public String getHasread() {
        return hasread;
    }

    public String getTalk() {
        return talk;
    }

    public void setHasread(String hasread) {
        this.hasread = hasread;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

@Transient
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
