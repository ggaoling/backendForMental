package com.example.demo.service.impl;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.RestExceptionHandler;
import com.example.demo.basic.Result;
import com.example.demo.domain.User;
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

    @Override
    public User findById(Integer id) throws NotFoundException {
        User currentUser=userRepository.findUserById(id);
        if(currentUser==null){
            throw new NotFoundException("user"+id+"is not exist!", Result.ErrorCode.NOT_FOUND.getCode());
        }
        return currentUser;
    }

    @Override
    public Object queryAllUsers(){
        List<User> userList=userRepository.findAll();
        Result result=new Result("success", 200,null);
     return result;
    }
}
