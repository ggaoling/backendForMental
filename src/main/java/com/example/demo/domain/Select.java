package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Select {
    @Id
    private Integer qid;

    public Select(Integer id){
        this.qid=id;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getQid() {
        return qid;
    }
}
