package com.example.demo.domain;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Question {
    private int qid;
    private String question;
    private int importance;
    private int type;
    private int tag;
    private List<Answer> answers;

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getQid() {
        return qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
