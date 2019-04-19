package com.example.demo.service.impl;

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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    private SelectedRepository selectedRepository;
    @Autowired
    private SeriesRepository seriesRepository;

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
            Integer qid=questionUpdate.getQid();
            for(Answer item:answers){
                Integer binding=item.getBinding();
                if(binding!=0){
                    //自身设为tag 1
                    questionUpdate.setTag(1);
                    questionRepository.save(questionUpdate);
                    //binding问题tag更改
                    Question next=questionRepository.findByQid(binding);
                    if(next==null){
                        result.setError("绑定的问题不存在，请重新确认");
                        questionRepository.deleteById(qid);

                        return result;
                    }
                    int tag=next.getTag();
                    if(tag<1){
                        next.setTag(2);
                    }
                    else if(tag==1){
                        next.setTag(3);
                    }
                    questionRepository.save(next);
                }
                item.setQid(qid);
            }
            List<Answer> answerUpdate=answerRepository.saveAll(answers);
            if(answerUpdate==null){
                result.setError("更新失败");
//                throw new UpdateFailException("更新失败", Result.ErrorCode.UPDATE_FAIL.getCode());
            }
        }
        return result;
    }

    @Override
    public Question queryQuestionById(Integer id) {
        Question question=questionRepository.findByQid(id);
        return question;
    }

    @Override
    public Result queryQuestionsByName(String question, Pageable pagination) {
        Page<Question> pages=questionRepository.findAllByQuestionLike("%"+question+"%",pagination);
        System.out.print(pages);
        Result result=new Result("success",200,null);
        if(pages.getContent()==null){
            result.setError("查找出错");
        }
        else{
            result.setResult(pages);
        }
            return result;
    }

    @Override
    public Result getTest(List<Selected> qidList){
        Result result=new Result("success",200,null);
        List<Question> questionList=new ArrayList<>();
        for(Selected item:qidList){
            Integer qid=Integer.valueOf(item.getQid());
            //找question表
            Question question=questionRepository.findByQid(qid);
            //找对应的answers
            List<Answer> answers=answerService.queryAnswers(qid);
            question.setAnswers(answers);
            questionList.add(question);
        }
        result.setResult(sortQuestionList(questionList));
        return result;
    }

    private List<Question> sortQuestionList(List<Question> questionList){
        List<Question> list=new ArrayList<>();
        Integer tag1Length=0;
        Integer tag3Length=0;//最后的位置
        Integer random=0;
        Integer pre0Length=0;
        for(Question item:questionList){
            Integer tag=item.getTag();
            switch (tag){
                case 1:
                    list.add(0,item);
                    tag1Length+=1;
                break;
                case 2:
                    list.add(list.size(),item);
                    break;
                case 3:
                    list.add(tag1Length+pre0Length,item);
                    tag3Length+=1;
                    break;
                case 0:
                    if(random%2==0){
                        list.add(tag1Length,item);
                        pre0Length+=1;
                    }
                    else{
                        list.add(tag1Length+pre0Length+tag3Length,item);
                    }
                    random+=1;
            }
        }
        return list;
    }
    @Override
    public Result updateQuestion(Question q){
        Result result=new Result("success",200,null);
        Integer qid=q.getQid();
        String question=q.getQuestion();
        int importance=q.getImportance();
        int type=q.getType();
        List<Answer> answers=q.getAnswers();
        Question currQuestion=questionRepository.findByQid(qid);
        currQuestion.setImportance(importance);
        currQuestion.setQuestion(question);
        currQuestion.setType(type);
        Question questionUpdate=questionRepository.save(currQuestion);
        if(questionUpdate==null){
            result.setError("更新失败");
        }
        Boolean flag=false;
        int tag=currQuestion.getTag();
        for(Answer item:answers){
            int binding=item.getBinding();
            if(binding>0){
                flag=true;
                //修改自身tag
                if(tag==0){
                    currQuestion.setTag(1);
                }
                else if(tag==2){
                    currQuestion.setTag(3);
                }

                //binding问题tag更改
                Question next=questionRepository.findByQid(binding);
                if(next==null){
                    result.setError("绑定的问题不存在，请重新确认");
                    return result;
                }
                int nextTag=next.getTag();
                if(nextTag<1){
                    next.setTag(2);
                }
                else if(nextTag==1){
                    next.setTag(3);
                }
                questionRepository.save(next);
            }
            if(!false){
                //没有binding
                if(tag==3){
                    currQuestion.setTag(2);
                }
                else if(tag==1){
                    currQuestion.setTag(0);
                }
            }
            questionRepository.save(currQuestion);
        }
            List<Answer> answerUpdate=answerRepository.saveAll(answers);
            if(answerUpdate==null){
                result.setError("更新失败");
        }

        return result;

    }

    //自定义目前写死11
    @Override
    public Result selectQuestions(SelectSeriesReuqest request){
        Result result=new Result("success",200,null);
        Integer sid=Integer.valueOf(request.getSid());
        List<Selected> qidList=request.getQidList();
        if(sid==11){
            selectedRepository.deleteAll();
            List<Selected> saveResult1=selectedRepository.saveAll(qidList);

            if(saveResult1==null){
                result.setError("插入select出错");
            }
            for(Selected item:qidList){
                List<Integer> valueList=findBinding(item.getQid());
                for(Integer elem:valueList){
                    if(elem!=0){
                        Selected toSave=new Selected(elem);
                        Selected saveResult2=selectedRepository.save(toSave);
                        if(saveResult2==null){
                            result.setError("bingding插入select出错");
                        }
                    }
                }
            }
        }
        else{
            for(Selected item:qidList){
                Integer qid=item.getQid();
                QidNSid qidNSid=new QidNSid();
                qidNSid.setQid(qid);
                qidNSid.setSid(sid);
                Series series=new Series();
                series.setId(qidNSid);
                Series save=seriesRepository.save(series);
                if(save==null)
                    result.setError("出错");
            }
        }

        return result;
    }


    private List<Integer> findBinding(Integer qid){
        List<Integer> bindingList=answerRepository.findBindingByQid(qid);
        List<Integer> result=answerRepository.findBindingByQid(qid);
        for(Integer elem:bindingList){
            if(elem!=0){
                result.addAll(findBinding(elem));
            }
        }
        return result;
    }

    @Override
    public Result getSimpleTest(Integer sid){
        Result result=new Result("success",200,null);
        List<Series> seriesList=seriesRepository.findByIdSid(sid);
        List<Question> resultList=new ArrayList<>();
        for(Series item:seriesList){
            Integer qid = item.getId().getQid();
            if (qid > -1) {
                Question question = questionRepository.findByQid(qid);
                List<Answer> answers = answerRepository.findByQid(qid);
                question.setAnswers(answers);
                resultList.add(question);
            }
        }
        result.setResult(resultList);
        return result;
    }
}