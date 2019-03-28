package com.example.demo.service.impl;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.RestExceptionHandler;
import com.example.demo.basic.Result;
import com.example.demo.domain.Admin;
import com.example.demo.domain.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public User findById_u(Integer id) {
        User currentUser_u=userRepository.findByUid(id);
        if(currentUser_u!=null){
            return currentUser_u;
        }

        else{
            return null;
        }


    }

    @Override
    public Admin findById_a(Integer id){
        Admin currentUser_a=adminRepository.findByUid(id);
         if(currentUser_a!=null){
            return currentUser_a;
        }
        else{
            return null;
        }
    }

    @Override
    public List<User> queryAllUsers(){
        List<User> userList=userRepository.findAll();
        return userList;
    }
}
