package com.example.demo.service.impl;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.Result;
import com.example.demo.basic.UpdateFailException;
import com.example.demo.domain.Answer;
import com.example.demo.domain.Question;
import com.example.demo.domain.Select;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SelectRepository;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SelectRepository selectRepository;

    @Override
    public Result addQuestion(Question question) {
        Question questionUpdate=questionRepository.save(question);
        Result result=new Result("success",200,null);
        if(questionUpdate==null){
            result.setError("更新失败");
//            throw new UpdateFailException("更新失败", Result.ErrorCode.UPDATE_FAIL.getCode());
        }
        else{
            List<Answer> answers=question.getAnswers();
            List<Answer> answerUpdate=answerRepository.saveAll(answers);
            if(answerUpdate==null){
                result.setError("更新失败");
//                throw new UpdateFailException("更新失败", Result.ErrorCode.UPDATE_FAIL.getCode());
            }
        }
        return result;
    }

    @Override
    public Object queryQuestionById(Integer id) {
        Question question=questionRepository.findByQid(id);
        return question;
//        if(question==null){
//            throw new NotFoundException("问题"+id+"不存在",Result.ErrorCode.NOT_FOUND.getCode());
//        }
//        else{
//            return new Result("success",200,question);
//        }
    }

    @Override
    public Result queryQuestionsByName(String name) {
        List<Question> questions=questionRepository.findByQuestionLike(name);
        Result result=new Result("success",200,questions);
        if(questions==null){
            result.setError("查找出错");
        }
            return result;
    }

    @Override
    public Object getTest(List<Select> qidList){
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

    @Override
    public Result selectQuestions(List<Select> qidList){
       List<Select> saveResult1=selectRepository.saveAll(qidList);
       Result result=new Result("success",200,null);
       if(saveResult1==null){
           result.setError("插入select出错");
//           throw new UpdateFailException("插入select出错",Result.ErrorCode.NOT_FOUND.getCode());
       }
        ArrayList<Integer> valueList=new ArrayList<Integer>();
       for(Select item:qidList){
           valueList.add(item.getQid());
           List<Integer> bindingList=answerRepository.findBindingByQid(item.getQid());
           for(Integer elem:bindingList){
               Select toSave=new Select(elem);
               Select saveResult2=selectRepository.save(toSave);
               if(saveResult2==null){
                   result.setError("bingding插入select出错");
//                   throw new UpdateFailException("bingding插入select出错",Result.ErrorCode.NOT_FOUND.getCode());
               }
           }
       }
        return result;
    }
}