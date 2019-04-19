package com.example.demo.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Pid implements Serializable {
    private Integer uid;
    private String type;

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Pid)) {
            return false;
        }
        Pid castOther = (Pid)other;
        return
                this.uid.equals(castOther.uid)
                        && this.type.equals(castOther.type);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.uid.hashCode();
        hash = hash * prime + this.type.hashCode();

        return hash;
    }
}
