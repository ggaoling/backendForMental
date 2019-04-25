package com.example.demo.service;

import com.example.demo.basic.Result;
import com.example.demo.domain.Series;

public interface SeriesService {

    Result addSeries(Series series);

    Series getSeries(Integer sid);

    Result getAllSeries(Integer id);
}
