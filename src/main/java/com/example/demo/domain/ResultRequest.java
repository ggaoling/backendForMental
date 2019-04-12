package com.example.demo.domain;

import java.util.List;

public class ResultRequest {
    private Integer uid;
    private List<TestResult> resultList;

    public Integer getUid() {
        return uid;
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
