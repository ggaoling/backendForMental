package com.example.demo.repository;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
//    @Query("from user u where u.id=:id")
//    User findUserById(@Param("id") Integer id);

    User findUserById(Integer id);

}
