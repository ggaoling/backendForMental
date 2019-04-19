package com.example.demo.service;


import com.example.demo.basic.Result;
import com.example.demo.domain.Presult;
import com.example.demo.domain.Restresult;
import com.example.demo.domain.TestResult;

import java.util.List;

public interface TestResultService {
    Result computeResult(Integer uid,Integer sid,List<TestResult> resultList);
    Result computeDWResult(Integer uid,Integer sid,List<TestResult> resultList);
    Result getRestlt(Integer uid,Integer sid);
    Result getDWResult(Integer uid,Integer sid);
}
