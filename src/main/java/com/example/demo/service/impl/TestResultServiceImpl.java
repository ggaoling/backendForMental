package com.example.demo.service.impl;

import com.example.demo.basic.Result;
import com.example.demo.domain.Answer;
import com.example.demo.domain.Question;
import com.example.demo.domain.TestResult;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.service.QuestionService;
import com.example.demo.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerRepository answerRepository;
    @Override
    public Integer computeResult(List<TestResult> resultList) {

        Integer history=0;
        for(TestResult item:resultList){
            Integer qid=item.getQid();
            Question question=questionService.queryQuestionById(qid);
            Integer importance=question.getImportance();
            List<Integer> aidList=item.getAid();
            Integer sum=0;
            for(Integer aid:aidList){
              Answer answer= answerRepository.findByAid(aid);
              sum=sum+answer.getQratio();
            }
            history=history+sum*importance;
        }
        return history;
    }
}
