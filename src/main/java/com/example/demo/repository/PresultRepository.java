package com.example.demo.repository;

import com.example.demo.domain.Pid;
import com.example.demo.domain.Presult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresultRepository extends JpaRepository<Presult,Pid> {

    public List<Presult> findByIdUid(Integer uid);
}
