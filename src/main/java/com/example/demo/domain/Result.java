package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Result {
    @Id
    private Integer id;
    private Integer qid;
    private Integer qratio;
    private Integer importance;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getQratio() {
        return qratio;
    }

    public void setQratio(Integer qratio) {
        this.qratio = qratio;
    }

}
