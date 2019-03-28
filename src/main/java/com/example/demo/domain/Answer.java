package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name="answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;
    private int qid;
    private String answer;
    private int qratio;
    private int binding;

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getAid() {
        return aid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQratio() {
        return qratio;
    }

    public void setQratio(int qratio) {
        this.qratio = qratio;
    }

    public int getBinding() {
        return binding;
    }

    public void setBinding(int binding) {
        this.binding = binding;
    }

}
