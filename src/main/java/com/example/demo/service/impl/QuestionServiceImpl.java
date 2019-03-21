package com.example.demo.service.impl;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.Result;
import com.example.demo.basic.UpdateFailException;
import com.example.demo.domain.Answer;
import com.example.demo.domain.Question;
import com.example.demo.domain.Select;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerService answerService;

    @Override
    public Object addQuestion(Question question) throws UpdateFailException{
        Question questionUpdate=questionRepository.save(question);
        if(questionUpdate==null){
            throw new UpdateFailException("更新失败", Result.ErrorCode.UPDATE_FAIL.getCode());
        }
        else{
            return new Result("success",200,null);
        }
    }

    @Override
    public Object queryQuestionById(Integer id) throws NotFoundException{
        Question question=questionRepository.findByQid(id);
        if(question==null){
            throw new NotFoundException("问题"+id+"不存在",Result.ErrorCode.NOT_FOUND.getCode());
        }
        else{
            return new Result("success",200,question);
        }
    }

    @Override
    public Object queryQuestionsByName(String name) throws NotFoundException{
        List<Question> questions=questionRepository.findByQuestionLike(name);
        if(questions==null){
            throw new NotFoundException("查找出错",Result.ErrorCode.NOT_FOUND.getCode());
        }
        else{
            return new Result("success",200,questions);
        }
    }

    @Override
    public Object getTest(List<Select> qidList)throws NotFoundException{
        for(Select item:qidList){
            Integer qid=Integer.valueOf(item.getQid());
            //找question表
            Question question=questionRepository.findByQid(qid);
            //找对应的answers
            List<Answer> answers=answerService.queryAnswers(qid);
            //便利answers找binding
            for(Answer elem:answers){
                Integer tempId=elem.getBinding();
                Question bindingQuestion=questionRepository.findByQid(qid);
            }
        }
        return "";
    }
}