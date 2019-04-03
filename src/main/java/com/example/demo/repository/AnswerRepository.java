package com.example.demo.repository;

import com.example.demo.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer> findByQid(Integer id);

    @Query("select binding from Answer a where a.qid in ?1")
    List<Integer> findBindingListByQid(List<Integer> qidList);

    @Query("select binding from Answer a where a.qid=?1")
    List<Integer> findBindingByQid(Integer qid);
}
