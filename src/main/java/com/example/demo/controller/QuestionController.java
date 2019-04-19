package com.example.demo.controller;

import com.example.demo.basic.Result;
import com.example.demo.domain.*;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SelectedRepository;
import com.example.demo.repository.SeriesRepository;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SeriesRepository seriesRepository;


    @RequestMapping(value = "/question/addQuestion")
    public Result addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @RequestMapping(value="/question/updateQuestion")
    public Result updataQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    /**
     *
     * @param map {questionName:value}
     * @return
     */
    @RequestMapping(value = "/question/queryQuestionsByName")
    public Result queryQuestionsByName(@RequestBody HashMap<String,String>map){
        String question=map.get("questionName");
        Integer pageNo=Integer.valueOf(map.get("pageNo"));
        Sort sort=new Sort(Sort.Direction.ASC, "qid");
        Pageable pagination=new PageRequest(pageNo,10,sort);
        return questionService.queryQuestionsByName(question,pagination);
    }

    /**
     *
     * @param request {selectList:[{qid:value},]}
     * @return
     */
    @RequestMapping(value = "/question/selectQuestions")
    public Result selectQuestions(@RequestBody SelectSeriesReuqest request){
        return questionService.selectQuestions(request);
    }

    @RequestMapping(value = "/question/queryQuestionByQid")
    public Result queryQuestionByQid(@RequestBody HashMap<String,String>request){
        Integer qid=Integer.valueOf(request.get("qid"));
        Question question=questionService.queryQuestionById(qid);
        Result result=new Result("success",200,null);
        if(question==null){
            result.setError("该id对应问题不存在");
        }
        else{
            List<Answer> answers=answerRepository.findByQid(qid);
            question.setAnswers(answers);
            result.setResult(question);
        }
        return result;
    }

    //自定义
    @RequestMapping(value = "/question/querySelected")
    public Result querySelected(@RequestBody HashMap<String,Integer> map){
        Integer pageNo=map.get("pageNo");
        Integer pageSize=map.get("pageSize");
        Integer sid=Integer.valueOf(map.get("sid"));
//        Sort sort=new Sort(Sort.Direction.ASC, "qid");
        Pageable pageable=new PageRequest(pageNo,pageSize);
        Result result=new Result("success",200,null);
        if(sid==11){
            Page<Question> questionList=questionRepository.querySelected(pageable);
            result.setResult((questionList));
        }
        else{
            List<Series> seriesList=seriesRepository.findByIdSid(sid);
            List<Integer> qidList=new ArrayList<>();
            for(Series item:seriesList){
                Integer qid=item.getId().getQid();
                    qidList.add(qid);
            }
            Page<Question> questionList=questionRepository.querySeries(qidList,pageable);
            result.setResult((questionList));
        }
        return result;
    }



}
