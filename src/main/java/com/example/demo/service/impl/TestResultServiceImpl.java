package com.example.demo.service.impl;

import com.example.demo.basic.Result;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.service.QuestionService;
import com.example.demo.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private PresultRepository presultRepository;
    @Autowired
    private RestresultRepository restresultRepository;
    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private LevelRepository levelRepository;

    /**
     * 普通试题计算，根据uid+sid 存history
     * @param uid
     * @param sid
     * @param resultList
     * @return
     */
    @Override
    public Result computeResult(Integer uid,Integer sid,List<TestResult> resultList) {
        Result result=new Result("success",200,null);
        Integer history=0;
        for(TestResult item:resultList){
            Integer qid=item.getQid();
            Question question=questionService.queryQuestionById(qid);
            Integer importance=question.getImportance();
            List<Integer> aidList=item.getAid();
            Integer sum=0;
            for(Integer aid:aidList){
              Answer answer= answerRepository.findByAid(aid);
              sum=sum+answer.getQratio();
            }
            history=history+sum*importance;
        }
        Restresult obj=new Restresult();
        UidNSid uidNSid=new UidNSid();
        uidNSid.setSid(sid);
        uidNSid.setUid(uid);
        obj.setId(uidNSid);
        obj.setHistory(history);
        restresultRepository.save(obj);
        return result;
    }

    /**
     * 计算五个属性分别的分数，根据uid和type存history
     * @param uid
     * @param sid
     * @param resultList
     * @return
     */
    @Override
    public Result computeDWResult(Integer uid,Integer sid,List<TestResult> resultList) {

        Result result=new Result("success",200,null);
        Integer Ohistory=0, Chistory=0,Ehistory=0,Ahistory=0,Nhistory=0;
        List<Integer> history=new ArrayList<>();
        for(Integer i=0;i<5;i++){
            history.add(0);
        }
        for(TestResult item:resultList){
            Integer qid=item.getQid();
            Question question=questionService.queryQuestionById(qid);
            Integer importance=question.getImportance();
            String dwtype=question.getDwtype();
            List<Integer> aidList=item.getAid();
            Integer sum=0;
            for(Integer aid:aidList){
                Answer answer= answerRepository.findByAid(aid);
                sum=sum+answer.getQratio();
            }
            switch (dwtype){
                case "O":
                    Ohistory=history.get(0)+sum*importance;
                    history.set(0,Ohistory);
                    break;
                case "C":
                    Chistory=history.get(1)+sum*importance;
                    history.set(1,Chistory);
                    break;
                case "E":
                    Ehistory=history.get(2)+sum*importance;
                    history.set(2,Ehistory);
                    break;
                case "A":
                    Ahistory=history.get(3)+sum*importance;
                    history.set(3,Ahistory);
                    break;
                case "N":
                    Nhistory=history.get(4)+sum*importance;
                    history.set(4,Nhistory);
                    break;
            }
            Presult presult=new Presult();
            Pid pid=new Pid();
            pid.setUid(uid);
            for(Integer i=0;i<5;i++){
                if(i==0){
                    pid.setType("O");
                    presult.setId(pid);
                    presult.setHistory(history.get(0));
                }
                if(i==1){
                    pid.setType("C");
                    presult.setId(pid);
                    presult.setHistory(history.get(1));
                }
                if(i==2){
                    pid.setType("E");
                    presult.setId(pid);
                    presult.setHistory(history.get(2));
                }
                if(i==3){
                    pid.setType("A");
                    presult.setId(pid);
                    presult.setHistory(history.get(3));
                }
                if(i==4){
                    pid.setType("N");
                    presult.setId(pid);
                    presult.setHistory(history.get(4));
                }
                presultRepository.save(presult);
            }
        }
        return result;
    }

    @Override
    public Result getRestlt(Integer uid, Integer sid) {
        Result result=new Result("success",200,null);
        QidNSid qidNSid=new QidNSid();
        qidNSid.setQid(-1);
        qidNSid.setSid(sid);
        String name=seriesRepository.findNameById(qidNSid);
        UidNSid uidNSid=new UidNSid(uid,sid);
        Restresult r=restresultRepository.findByMId(uidNSid);
        if(r==null){
            result.setError("无结果");
            return result;
        }
        HashMap<String,String> response=new HashMap<>();
        List<Level> levelList=levelRepository.findBySid(sid);
        Integer history =r .getHistory();
        for(Integer i=0;i<levelList.size();i++){
            Integer pre=0;
            if(i!=0){
                pre=levelList.get(i-1).getNum();
            }
            Integer next=levelList.get(i).getNum();
            if(i==0&&history<next||history>=pre&&history<next){
                response.put("explain",levelList.get(i).getDescription());
                break;
            }
        }
        response.put("name",name);
        response.put("average","14");
        response.put("personal",history.toString());
        result.setResult(response);
        return result;
    }

    @Override
    public Result getDWResult(Integer uid, Integer sid) {
        Result result=new Result("success",200,null);
        BPResponse response=new BPResponse();
        QidNSid qidNSid=new QidNSid();
        qidNSid.setQid(-1);
        qidNSid.setSid(sid);
        String name=seriesRepository.findNameById(qidNSid);
        response.setName(name);
        BPResponseItem item=new BPResponseItem();
        item.setName("个人");
        List<Presult> presultList=presultRepository.findByIdUid(uid);
        for(Presult elem:presultList){
            if(elem.getId().getType().equals("A")){
                item.setA(elem.getHistory());
            }
            if(elem.getId().getType().equals("N")){
                item.setN(elem.getHistory());
            }
            if(elem.getId().getType().equals("C")){
                item.setC(elem.getHistory());
            }
            if(elem.getId().getType().equals("O")){
                item.setO(elem.getHistory());
            }
            if(elem.getId().getType().equals("E")){
                item.setE(elem.getHistory());
            }
        }
        BPResponseItem average=new BPResponseItem("平均",18,20,20,21,19);
        List<BPResponseItem> list=new ArrayList<>();
        list.add(item);
        list.add(average);
        response.setDataList(list);
        result.setResult(response);
        return result;
    }


}
