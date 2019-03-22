package com.example.demo.service;

import com.example.demo.basic.NotFoundException;
import com.example.demo.domain.Admin;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface UserService {

   User findById_u(Integer id);

   Admin findById_a(Integer id);

    List<User> queryAllUsers();

}
