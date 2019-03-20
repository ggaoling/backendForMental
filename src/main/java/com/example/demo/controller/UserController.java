package com.example.demo.controller;

import com.example.demo.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.example.demo.util.MD5.getMD5;

@RestController
public class UserController {
    @RequestMapping(value="/logon")
    public Object login(@RequestBody HashMap<String, String> map){
        String id=map.get("id");
        String password=map.get("password");
        System.out.print(id.equals("1001"));
        if(id.equals("1001")&&password.equals(getMD5("1001"))){
            User user=new User();
            user.setStatus("ok");
            user.setCurrentAuthority("user");
            return user;
        }
        else{
            return "!!";
        }
    }


}
