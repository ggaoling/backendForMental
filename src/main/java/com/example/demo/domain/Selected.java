package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="selected")
public class Selected {
    @Id
    private Integer qid;

    public Selected(){

    }
    public Selected(Integer qid){
        this.qid=qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getQid() {
        return qid;
    }
}
