package com.example.demo.controller;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.Result;
import com.example.demo.basic.UpdateFailException;
import com.example.demo.domain.Answer;
import com.example.demo.domain.Question;
import com.example.demo.domain.Select;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SelectRepository;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

//@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private SelectRepository selectRepository;
    @Autowired
    private QuestionRepository questionRepository;


    @RequestMapping(value = "/question/addQuestion")
    public Result addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    /**
     *
     * @param map {questionName:value}
     * @return
     */
    @RequestMapping(value = "/queryQuestionsByName")
    public Result queryQuestionsByName(@RequestBody HashMap<String,String>map){
        String name=map.get("questionName");
        return questionService.queryQuestionsByName(name);
    }

    /**
     *
     * @param qidList [{qid:value},]
     * @return
     */
    @RequestMapping(value = "/selectQuestions")
    public Result selectQuestions(@RequestBody List<Select> qidList){

        return questionService.selectQuestions(qidList);
    }

    @RequestMapping(value = "/queryQuestionByQid")
    public Result queryQuestionByQid(@RequestBody HashMap<String,String>request){
        Integer qid=Integer.valueOf(request.get("qid"));
        Question question=questionService.queryQuestionById(qid);
        Result result=new Result("success",200,null);
        if(question==null){
            result.setError("该id对应问题不存在");
        }
        else{
            result.setResult(question);
        }
        return result;
    }

}
