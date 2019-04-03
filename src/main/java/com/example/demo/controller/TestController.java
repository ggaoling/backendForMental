package com.example.demo.controller;

import com.example.demo.basic.Result;
import com.example.demo.domain.Question;
import com.example.demo.domain.Selected;
import com.example.demo.domain.User;
import com.example.demo.repository.SelectedRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(name = "/test")
public class TestController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private SelectedRepository selectedRepository;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(name = "/getTest")
    public Result getTest(@RequestBody HashMap<String,String> request) {
        Integer uid=Integer.valueOf(request.get("uid"));
        User user=userRepository.findByUid(uid);
        Integer history=user.getHistory();
        if(history==0){
            return new Result("success",200,null);
        }
        List<Selected> selectedList=selectedRepository.findAll();
        return  questionService.getTest(selectedList);

    }

}
