package com.example.demo.controller;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.Result;
import com.example.demo.basic.UpdateFailException;
import com.example.demo.domain.Question;
import com.example.demo.domain.Select;
import com.example.demo.repository.SelectRepository;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private SelectRepository selectRepository;

    @RequestMapping(value = "/addQuestion")
    public Object addQuestion(@RequestBody Question question)throws UpdateFailException {
        return questionService.addQuestion(question);
    }

    /**
     *
     * @param map {questionName:value}
     * @return
     * @throws NotFoundException
     */
    @RequestMapping(value = "/queryQuestionsByName")
    public Object queryQuestionsByName(@RequestBody HashMap<String,String>map) throws NotFoundException{
        String name=map.get("questionName");
        return questionService.queryQuestionsByName(name);
    }

    /**
     *
     * @param qidList [{qid:value},]
     * @return
     */
    @RequestMapping(value = "selectQuestions")
    public Object selectQuestions(@RequestBody List<Select> qidList)throws UpdateFailException{
        for(Select item:qidList){
            Select select=selectRepository.save(item);
            if(select==null){
                throw new UpdateFailException("插入select表失败", Result.ErrorCode.UPDATE_FAIL.getCode());
            }

        }
        return new Result("success",200,null);
    }




}
