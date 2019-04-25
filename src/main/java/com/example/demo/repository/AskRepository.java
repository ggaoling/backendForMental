package com.example.demo.repository;

import com.example.demo.domain.Ask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AskRepository extends JpaRepository<Ask,Integer> {
    Ask findByAid(Integer id);
    List<Ask> findByUid(Integer id);
}
