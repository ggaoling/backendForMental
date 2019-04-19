package com.example.demo.controller;

import com.example.demo.basic.Result;
import com.example.demo.domain.Series;
import com.example.demo.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @RequestMapping(value = "/series/addSeries")
    Result addSeries(@RequestBody Series series){
        return seriesService.addSeries(series);
    }
    @RequestMapping(value = "/series/getSeries")
    Result getAllSeries(){
        return seriesService.getAllSeries();
    }

}
