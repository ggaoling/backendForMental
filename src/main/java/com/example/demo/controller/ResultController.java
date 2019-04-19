package com.example.demo.controller;

import com.example.demo.basic.Result;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.service.QuestionService;
import com.example.demo.service.TestResultService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        @Autowired
        private SeriesRepository seriesRepository;
        @Autowired
        private QuestionRepository questionRepository;
        @Autowired
        private AnswerRepository answerRepository;


        //自定义问卷 11
        @RequestMapping(value = "/test/getTest")
        public Result getTest(@RequestBody HashMap<String,String> request) {
            Integer sid=Integer.valueOf(request.get("sid"));
            if(sid==11){
                List<Selected> selectedList=selectedRepository.findAll();
                return  questionService.getTest(selectedList);
            }
            else{
                return questionService.getSimpleTest(sid);
                }

        }

        @RequestMapping(value = "/test/submitTest")
        public Result submitTest(@RequestBody ResultRequest request){
            List<TestResult> resultList=request.getResultList();
            Integer uid=Integer.valueOf(request.getUid());
            Integer sid=Integer.valueOf(request.getSid());

            if(sid==12){
                return testResultService.computeDWResult(uid,sid,resultList);
            }
            else{
               return testResultService.computeResult(uid,sid,resultList);
            }

        }

        @RequestMapping(value="/test/getResult")
        public Result getResult(@RequestBody HashMap<String,String> request){
            Integer sid=Integer.valueOf(request.get("sid"));
            Integer uid=Integer.valueOf(request.get("uid"));
            if(sid==12){
                return testResultService.getDWResult(uid,sid);
            }
            else{
                return testResultService.getRestlt(uid,sid);
            }
        }




}
