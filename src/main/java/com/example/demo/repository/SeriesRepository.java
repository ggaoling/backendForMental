package com.example.demo.repository;

import com.example.demo.domain.QidNSid;
import com.example.demo.domain.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series,QidNSid> {

   List<Series> findByIdSid(Integer id);

   @Query("select s.name from Series s where s.id=?1")
   String findNameById(QidNSid id);

   @Query("select  s from Series s where s.id=?1")
   Series findSeriesById(QidNSid id);
}
