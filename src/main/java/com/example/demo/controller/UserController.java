package com.example.demo.controller;

import com.example.demo.basic.Result;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static com.example.demo.util.MD5.getMD5;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value="/logon")
    public Object login(@RequestBody HashMap<String, String> map){
        String id=map.get("id");
        String password=map.get("password");
        System.out.print(id.equals("1001"));
        if(id.equals("1001")&&password.equals(getMD5("1001"))){
            User user=new User();
            user.setStatus("ok");
            user.setCurrentAuthority("user");
            Result result=new Result("success", 200,user);
            return result;
        }
        else{
            return "!!";
        }
    }
    @RequestMapping(value="user/queryCurrent")
    public User queryCurrent(@RequestBody HashMap<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
        User current=userRepository.findUserById(id);
        return current;
    }

    @RequestMapping(value = "user/updateUserInfo")
    public Object updateUserInfo(@RequestBody User user){
            userRepository.save(user);
            Result result=new Result("success",200,null);
            return result;
    }

    @RequestMapping(value = "user/queryAllUsers")
    public Object queryAllUsers(){
        return userService.queryAllUsers();
    }


}
