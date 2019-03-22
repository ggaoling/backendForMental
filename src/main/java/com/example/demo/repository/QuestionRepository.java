package com.example.demo.repository;

import com.example.demo.domain.Question;
import com.example.demo.domain.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Question findByQid(Integer id);


    List<Question> findByQuestionLike(String name);

}
