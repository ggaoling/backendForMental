package com.example.demo.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UidNSid implements Serializable {
    private Integer uid;
    private Integer sid;

    public UidNSid(Integer uid,Integer sid){
        this.uid=uid;
        this.sid=sid;
    }
    public UidNSid(){}
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UidNSid)) {
            return false;
        }
        UidNSid castOther = (UidNSid)other;
        return
                this.uid.equals(castOther.uid)
                        && this.sid.equals(castOther.sid);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.uid.hashCode();
        hash = hash * prime + this.sid.hashCode();

        return hash;
    }
}
