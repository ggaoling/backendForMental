package com.example.demo.service.impl;

import com.example.demo.basic.Result;
import com.example.demo.domain.Ask;
import com.example.demo.domain.Reply;
import com.example.demo.repository.AskRepository;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdviceServiceImpl implements AdviceService {
    @Autowired
    private AskRepository askRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public Result studentQuery(Integer uid) {
        Result result=new Result("success",200,null);
        List<Ask> askList= askRepository.findByUid(uid);
        for(Ask item:askList){
            Integer aid=item.getAid();
            Reply reply=replyRepository.findByAid(aid);
            if(reply!=null){
                item.setReply(reply.getTalk());
            }

        }
        result.setResult(askList);
        return result;
    }

    @Override
    public Result teacherQuery() {
        Result result=new Result("success",200,null);
        List<Ask> askList=askRepository.findAll();
        if(askList==null){
            result.setError("error");
            return result;
        }
        List<Ask> finalList=new ArrayList<>();
        finalList=askList.stream().filter((Ask item)->replyRepository.findByAid(item.getAid())==null).collect((Collectors.toList()));
        result.setResult(finalList);
        return result;
    }
}
