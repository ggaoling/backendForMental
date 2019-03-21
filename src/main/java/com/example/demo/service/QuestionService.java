package com.example.demo.service;

import com.example.demo.basic.NotFoundException;
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
    Object addQuestion(Question question) throws UpdateFailException;

    /**
     * 根据名字模糊查询问题列表
     * @param name
     * @return
     * @throws NotFoundException
     */
    Object queryQuestionsByName(String name)throws NotFoundException;

    /**
     * 根据qid查问题
     * @param id
     * @return
     * @throws NotFoundException
     */
    Object queryQuestionById(Integer id)throws NotFoundException;

    /**
     * 根据qid获取question
     * @param qidList
     * @return
     */
    Object getTest(List<Select> qidList)throws NotFoundException;
}
