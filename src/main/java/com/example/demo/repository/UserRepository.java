package com.example.demo.repository;
import com.example.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaRepository<User,Integer> ,PagingAndSortingRepository<User,Integer> {
    User findByUid(Integer id);
    Page<User> findAll(Pageable pageable);
}
