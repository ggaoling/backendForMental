package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

public interface UserService {

    User findUserByName(String name);
}
