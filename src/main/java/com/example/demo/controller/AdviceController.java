package com.example.demo.controller;

import com.example.demo.basic.Result;
import com.example.demo.domain.Ask;
import com.example.demo.domain.Reply;
import com.example.demo.repository.AskRepository;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AdviceController {
    @Autowired
    private AskRepository askRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private AdviceService adviceService;

    @RequestMapping(value = "/advice/studentAsk")
    public Result studentAsk(@RequestBody Ask ask){
        askRepository.save(ask);
        return new Result("success",200,null);
    }

    @RequestMapping(value = "/advice/teacherReply")
    public Result teacherReply(@RequestBody Reply reply){
        replyRepository.save(reply);
        Integer aid=reply.getAid();
        Ask ask=askRepository.findByAid(aid);
        ask.setHasread("true");
        askRepository.save(ask);
        return new Result("success",200,null);
    }

    @RequestMapping(value="/advice/studentQuery")
    public Result studentQuery(@RequestBody HashMap<String,String> request){
        Integer uid=Integer.valueOf(request.get("uid"));
        return adviceService.studentQuery(uid);
    }
    @RequestMapping(value = "/advice/teacherQuery")
    public Result teacherQuery(){
        return adviceService.teacherQuery();
    }


    /**
     * from 's'|'t'
     * id
     * @param request
     * @return
     */
    @RequestMapping(value = "advice/changeRead")
    public Result changeRead(@RequestBody HashMap<String,String> request){
        String type=request.get("type");
        Integer id=Integer.valueOf(request.get("id"));
        if(type.equals("s")){
           Reply reply=replyRepository.findByRid(id);
           reply.setHasread("true");
           replyRepository.save(reply);
        }
        else{
            Ask ask=askRepository.findByAid(id);
            ask.setHasread("true");
            askRepository.save(ask);
        }
        return new Result("success",200,null);
    }
}
