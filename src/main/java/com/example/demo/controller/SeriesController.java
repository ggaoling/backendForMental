package com.example.demo.controller;

import com.example.demo.basic.Result;
import com.example.demo.domain.Level;
import com.example.demo.domain.LevelInputRequest;
import com.example.demo.domain.QidNSid;
import com.example.demo.domain.Series;
import com.example.demo.repository.LevelRepository;
import com.example.demo.repository.SeriesRepository;
import com.example.demo.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class SeriesController {

    @Autowired
    private SeriesService seriesService;
    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private LevelRepository levelRepository;

    @RequestMapping(value = "/series/addSeries")
    Result addSeries(@RequestBody Series series){
        return seriesService.addSeries(series);
    }
    @RequestMapping(value = "/series/getSeries")
    Result getAllSeries(@RequestBody HashMap<String,String> request){
        return seriesService.getAllSeries(Integer.valueOf(request.get("id")));
    }

    @RequestMapping(value="/series/setIsopen")
    Result setIsopen(@RequestBody HashMap<String,String> request){
        Integer sid=Integer.valueOf(request.get("sid"));
        String isopen=request.get("isopen");
        QidNSid id=new QidNSid();
        id.setQid(-1);
        id.setSid(sid);
        Series series=seriesRepository.findSeriesById(id);
        series.setIsopen(isopen);
        seriesRepository.save(series);
        return new Result("success",200,null);
    }

    @RequestMapping(value = "/series/submitLevel")
    public Result submitLevel(@RequestBody LevelInputRequest request){
        List<String> num=request.getNum();
        List<String> description=request.getDescription();
        Integer sid=request.getSid();
        List<Level> levelList=new ArrayList<>();
        for(String item:num){
            Level level=new Level();
            level.setNum(Integer.valueOf(item));
            level.setDescription(description.get(num.indexOf(item)));
            level.setSid(sid);
            levelRepository.save(level);
        }
        return new Result("success",200,null);
    }

}
