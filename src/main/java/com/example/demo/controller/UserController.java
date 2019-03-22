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
    public Result login(@RequestBody HashMap<String, String> map)throws NotFoundException {
        Integer id=Integer.valueOf(map.get("id"));
        String password=getMD5(map.get("password"));
        Admin a=userService.findById_a(id);
        Result result=new Result("success",200,null);
        if(a==null){
            User u=userService.findById_u(id);
            if(u==null){
                //uid aid都不匹配
                throw new NotFoundException("user"+id+"is not exist!", Result.ErrorCode.NOT_FOUND.getCode());
            }
            else if(u.getPassword().equals(password)){
                    result.setResult(u);
                }
            else{
                throw new NotFoundException("密码不正确", Result.ErrorCode.NOT_FOUND.getCode());
//                result=new Result("密码不正确",Result.ErrorCode.NOT_FOUND.getCode(),null);
            }

        }
        else if(a.getPassword().equals(password)){
            result.setResult(a);
        }
        else{
            throw new NotFoundException("密码不正确", Result.ErrorCode.NOT_FOUND.getCode());
        }


//        if(id.equals("1001")&&password.equals(getMD5("1001"))){
//            User user=new User();
//            user.setStatus("ok");
//            user.setCurrentAuthority("user");
//            result.setError("success");
//            result.setCode(200);
//            result.setResult(user);
//        }
//        else{
//            result.setError("fail");
//            result.setCode(Result.ErrorCode.NOT_FOUND.getCode());
//            result.setResult(null);
//        }
        return result;
    }


    @RequestMapping(value="user/queryCurrent")
    public Result queryCurrent(@RequestBody HashMap<String,String> map)throws NotFoundException {
        Integer id=Integer.valueOf(map.get("id"));
        Result result=new Result("success",200,null);
        Admin a=userService.findById_a(id);
        if(a==null){
            User u=userService.findById_u(id);
            if(u==null){
                throw new NotFoundException("查询当前用户信息错误", Result.ErrorCode.NOT_FOUND.getCode());
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
    @RequestMapping(value = "user/updateUserInfo")
    public Result updateUserInfo(@RequestBody HashMap<String,String> user)throws UpdateFailException{
        Integer id=Integer.valueOf(user.get("uid"));
        String tel=user.get("tel");
        String email=user.get("email");
        User u=userService.findById_u(id);
        if(u!=null){
            u.setTel(tel);
            u.setEmail(email);
            User userUpdate=userRepository.save(u);
            if(userUpdate==null){
                throw new UpdateFailException("更新失败", Result.ErrorCode.UPDATE_FAIL.getCode());
            }
        }
        else{
            Admin a=userService.findById_a(id);
            a.setEmail(email);
            a.setTel(tel);
            Admin adminUpdate=adminRepository.save(a);
            if(adminUpdate==null){
                throw new UpdateFailException("更新失败", Result.ErrorCode.UPDATE_FAIL.getCode());
            }
        }
        Result result=new Result("success",200,null);
        return result;
    }

    @RequestMapping(value = "user/queryAllUsers")
    public Result queryAllUsers() throws NotFoundException{
         List<User> userList=userRepository.findAll();
         Result result=new Result("success", 200,null);
         if(userList==null){
             throw new NotFoundException("查询用户出错", Result.ErrorCode.NOT_FOUND.getCode());
         }
         else{
             result.setResult(userList);
         }
         return result;
    }


}
