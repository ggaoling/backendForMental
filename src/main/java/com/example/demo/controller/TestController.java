package com.example.demo.controller;

import com.example.demo.basic.Result;
import com.example.demo.domain.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @Autowired
    private QuestionService questionService;


}
