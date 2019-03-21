package com.example.demo.service.impl;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.Result;
import com.example.demo.basic.UpdateFailException;
import com.example.demo.domain.Answer;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Object addAnswer(Answer answer) throws UpdateFailException {
        Answer answer1=answerRepository.save(answer);
        if(answer1==null){
            throw new UpdateFailException("新增失败", Result.ErrorCode.UPDATE_FAIL.getCode());
        }
        else{
            return new Result("success",200,null);
        }
    }

    @Override
    public List<Answer> queryAnswers(Integer qid) {
       List<Answer> answers=answerRepository.findByQid(qid);
       return answers;

    }




}
