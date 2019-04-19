package com.example.demo.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Presult {
    @EmbeddedId
    private Pid id;
    private Integer history;

    public void setHistory(Integer history) {
        this.history = history;
    }

    public Integer getHistory() {
        return history;
    }

    public void setId(Pid id) {
        this.id = id;
    }

    public Pid getId() {
        return id;
    }
}
