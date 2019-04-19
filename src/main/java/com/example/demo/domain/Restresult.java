package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "restresult")
public class Restresult {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private  UidNSid id;

    private Integer history;

    public void setId(UidNSid id) {
        this.id = id;
    }

    public UidNSid getId() {
        return id;
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }
}
