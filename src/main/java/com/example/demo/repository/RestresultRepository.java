package com.example.demo.repository;

import com.example.demo.domain.QidNSid;
import com.example.demo.domain.Restresult;
import com.example.demo.domain.UidNSid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestresultRepository extends JpaRepository<Restresult,UidNSid> {

    @Query("select r from Restresult r where r.id=?1")
    public Restresult findByMId(UidNSid id);
}
