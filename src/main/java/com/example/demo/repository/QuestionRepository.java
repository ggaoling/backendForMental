package com.example.demo.repository;

import com.example.demo.domain.QidNSid;
import com.example.demo.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer>,PagingAndSortingRepository<Question,Integer> {
    Question findByQid(Integer id);
   Page<Question> findAllByQuestionLike(String question, Pageable pageable);

   @Query("select q from Question q,Selected s where q.qid=s.qid")
   Page<Question> querySelected(Pageable pageable);

   //todo
   @Query("select q from Question q where q.qid in ?1")
   Page<Question> querySeries(List<Integer> qidList,Pageable pageable);

}
