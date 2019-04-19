package com.example.demo;

import com.example.demo.domain.Selected;
import com.example.demo.domain.Series;
import com.example.demo.repository.SelectedRepository;
import com.example.demo.repository.SeriesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private SelectedRepository selectedRepository;
	@Autowired
	private SeriesRepository seriesRepository;
	@Test
	public void contextLoads() {
    List<Series> a =seriesRepository.findByIdSid(9);
    System.out.print(a);

	}

}
