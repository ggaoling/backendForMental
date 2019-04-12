package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;


public class TestResult {
    @Id
    private Integer qid;
    private List<Integer> aid;

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public void setAid(List<Integer> aid) {
        this.aid = aid;
    }

    public List<Integer> getAid() {
        return aid;
    }
}
