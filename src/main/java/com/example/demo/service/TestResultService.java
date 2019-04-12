package com.example.demo.service;


import com.example.demo.basic.Result;
import com.example.demo.domain.TestResult;

import java.util.List;

public interface TestResultService {
    Integer computeResult(List<TestResult> resultList);

}
