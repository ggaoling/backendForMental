package com.example.demo.domain;

import java.util.List;

public class SelectSeriesReuqest {
    private Integer sid;
    private List<Selected> qidList;

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public List<Selected> getQidList() {
        return qidList;
    }

    public void setQidList(List<Selected> qidList) {
        this.qidList = qidList;
    }
}
