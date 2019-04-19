package com.example.demo.service.impl;

import com.example.demo.basic.Result;
import com.example.demo.domain.QidNSid;
import com.example.demo.domain.Series;
import com.example.demo.repository.SeriesRepository;
import com.example.demo.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesServiceImpl implements SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;
    @Override
    public Result addSeries(Series series) {
        Result result=new Result("success",200,null);
        QidNSid qidNSid=new QidNSid();
        qidNSid.setQid(-1);
        series.setId(qidNSid);
        Series save=seriesRepository.save(series);
        if(save==null){
            result.setError("保存出错");
        }
        return result;
    }

    @Override
    public Series getSeries(Integer sid) {
        return null;
    }

    @Override
    public Result getAllSeries() {
        Result result=new Result("success",200,null);
        List<Series> seriesList=seriesRepository.findAll();
//        for(Series item:seriesList){
//
//        }
        List<Series> finalList=null;
        finalList=seriesList.stream().filter((Series item)->item.getId().getQid()==-1).collect((Collectors.toList()));
        result.setResult(finalList);
        return result;
    }
}
