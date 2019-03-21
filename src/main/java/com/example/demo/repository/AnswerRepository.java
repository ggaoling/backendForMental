package com.example.demo.repository;

import com.example.demo.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer> findByQid(Integer id);
}
