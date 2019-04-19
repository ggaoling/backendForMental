package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "series")
public class Series {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer sid;
    @EmbeddedId
    private QidNSid id;
    private String name;
    private String description;

//    @EmbeddedId
    public QidNSid getId() {
        return id;
    }

    public void setId(QidNSid id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
