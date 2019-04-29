package com.example.demo.domain;

import java.util.List;

public class LevelInputRequest {
    private List<String> num;
    private List<String> description;
    private Integer sid;
    public void setNum(List<String> num) {
        this.num = num;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getNum() {
        return num;
    }

}
