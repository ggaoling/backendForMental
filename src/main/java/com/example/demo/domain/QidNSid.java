package com.example.demo.domain;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
public class QidNSid implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;
    private Integer qid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getQid() {
        return qid;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QidNSid)) {
            return false;
        }
        QidNSid castOther = (QidNSid)other;
        return this.qid.equals(castOther.qid)
                        && this.sid.equals(castOther.sid);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.sid.hashCode();
        hash = hash * prime + this.qid.hashCode();

        return hash;
    }
}
