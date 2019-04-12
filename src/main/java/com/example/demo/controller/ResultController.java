package com.example.demo.controller;

import com.example.demo.basic.Result;
import com.example.demo.domain.ResultRequest;
import com.example.demo.domain.Selected;
import com.example.demo.domain.TestResult;
import com.example.demo.domain.User;
import com.example.demo.repository.SelectedRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.QuestionService;
import com.example.demo.service.TestResultService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ResultController {

        @Autowired
        private QuestionService questionService;
        @Autowired
        private SelectedRepository selectedRepository;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private TestResultService testResultService;
        @Autowired
        private UserService userService;


        @RequestMapping(value = "/test/getTest")
        public Result getTest(@RequestBody HashMap<String,String> request) {
            Integer uid=Integer.valueOf(request.get("uid"));
            User user=userRepository.findByUid(uid);
            Integer history=Integer.valueOf(user.getHistory());
            if(history>0){
                return new Result("success",200,null);
            }
            List<Selected> selectedList=selectedRepository.findAll();
            return  questionService.getTest(selectedList);
        }

        @RequestMapping(value = "/test/submitTest")
        public Result submitTest(@RequestBody ResultRequest request){
            List<TestResult> resultList=request.getResultList();
            Integer uid=Integer.valueOf(request.getUid());
            Result result=new Result("success",200,null);
            Integer history= testResultService.computeResult(resultList);
            User user=userService.findById_u(uid);
            user.setHistory(history);
            User update=userRepository.save(user);
            if(update==null){
                result.setError("更新失败");
            }
            return result;
        }

}
