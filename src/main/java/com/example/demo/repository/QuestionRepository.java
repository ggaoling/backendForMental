package com.example.demo.repository;

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

}
