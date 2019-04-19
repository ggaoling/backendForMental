package com.example.demo.domain;

import java.util.List;

public class BPResponse {
    private String name;
    private List<BPResponseItem> dataList;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDataList(List<BPResponseItem> dataList) {
        this.dataList = dataList;
    }

    public List<BPResponseItem> getDataList() {
        return dataList;
    }
}
