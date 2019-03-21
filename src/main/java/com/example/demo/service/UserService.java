package com.example.demo.service;

import com.example.demo.basic.NotFoundException;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

import java.util.List;

public interface UserService {

   Object findById(Integer id) throws NotFoundException;

    Object queryAllUsers();
}
