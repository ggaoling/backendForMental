package com.example.demo.repository;

import com.example.demo.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level,Integer> {
    List<Level> findBySid(Integer sid);
}
