package com.example.demo.domain;

import java.util.List;

public class ResultRequest {
    private Integer uid;
    private Integer sid;
    private List<TestResult> resultList;

    public Integer getUid() {
        return uid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public List<TestResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<TestResult> resultList) {
        this.resultList = resultList;
    }
}
