package com.example.demo.controller;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.Result;
import com.example.demo.basic.UpdateFailException;
import com.example.demo.domain.Admin;
import com.example.demo.domain.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

import static com.example.demo.util.MD5.getMD5;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(value="/logon")
    public Result login(@RequestBody HashMap<String, String> map){
        Integer id=Integer.valueOf(map.get("uid"));
        String password=map.get("password");
        Admin a=userService.findById_a(id);
        Result result=new Result("success",200,null);
        if(a==null){
            User u=userService.findById_u(id);
            System.out.print(u);
            if(u==null){
                //uid aid都不匹配
                result.setError("该用户不存在");
            }
            else if(u.getPassword().equals(password)){
                u.setStatus("ok");
                    result.setResult(u);
                }

            else{
                result.setError("密码不正确");
            }

        }
        else if(a.getPassword().equals(password)){
            a.setStatus("ok");
            result.setResult(a);
        }
        else {
            result.setError("密码不正确");
        }
        return result;
    }


    @RequestMapping(value="/user/queryCurrent")
    public Result queryCurrent(@RequestBody HashMap<String,String> map){
        Integer id=Integer.valueOf(map.get("uid"));
        Result result=new Result("success",200,null);
        Admin a=userService.findById_a(id);
        if(a==null){
            User u=userService.findById_u(id);
            if(u==null){
                result.setError("查询当前用户信息错误");
            }
            else{
                result.setResult(u);
            }
        }
        else{
           result.setResult(a);
        }
        return result;
    }

    /**
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/updateUserInfo")
    public Result updateUserInfo(@RequestBody HashMap<String,String> user){
        Integer id=Integer.valueOf(user.get("id"));
        String tel=user.get("tel");
        String email=user.get("email");
        User u=userService.findById_u(id);
        Result result=new Result("success",200,null);
        if(u!=null){
            u.setTel(tel);
            u.setEmail(email);
            User userUpdate=userRepository.save(u);
            if(userUpdate==null){
                result.setError("更新失败");
            }
        }
        else{
            Admin a=userService.findById_a(id);
            a.setEmail(email);
            a.setTel(tel);
            Admin adminUpdate=adminRepository.save(a);
            if(adminUpdate==null){
                result.setError("更新失败");
            }
        }
        return result;
    }

    @RequestMapping(value = "/user/queryAllUsers")
    public Result queryAllUsers(){
         List<User> userList=userRepository.findAll();
         Result result=new Result("success", 200,null);
         if(userList==null){
             result.setError("查询用户出错");
         }
         else{
             result.setResult(userList);
         }
         return result;
    }
}
