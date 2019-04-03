package com.example.demo.service;

import com.example.demo.basic.NotFoundException;
import com.example.demo.domain.Admin;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

   User findById_u(Integer id);

   Admin findById_a(Integer id);

    Page<User> queryAllUsers(Pageable pageable);

}
