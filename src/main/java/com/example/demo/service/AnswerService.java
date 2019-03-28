package com.example.demo.service;

import com.example.demo.basic.NotFoundException;
import com.example.demo.basic.UpdateFailException;
import com.example.demo.domain.Answer;

import java.util.List;

public interface AnswerService {
    /***
     *
     * @param answer
     * @return
     * @throws UpdateFailException
     * 新增答案
     */
    Object addAnswer(Answer answer);

    /**
     * 根据qid查找answers
     * @param qid
     * @return
     * @throws NotFoundException
     */
    List<Answer> queryAnswers(Integer qid);
}
