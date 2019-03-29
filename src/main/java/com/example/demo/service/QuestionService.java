package com.example.demo.service;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.Result;
import com.example.demo.basic.UpdateFailException;
import com.example.demo.domain.Question;
import com.example.demo.domain.Select;

import java.util.List;

public interface QuestionService {
    /**
     * 新增问题
     * @param question
     * @return
     * @throws UpdateFailException
     */
    Result addQuestion(Question question) ;

    /**
     * 根据名字模糊查询问题列表
     * @param name
     * @return
     * @throws NotFoundException
     */
    Result queryQuestionsByName(String name);

    /**
     * 根据qid查问题
     * @param id
     * @return
     * @throws NotFoundException
     */
    Question queryQuestionById(Integer id);

    /**
     * 根据qid获取question
     * @param qidList
     * @return
     */
    Result getTest(List<Select> qidList);

    Result selectQuestions(List<Select> qidList);
}
