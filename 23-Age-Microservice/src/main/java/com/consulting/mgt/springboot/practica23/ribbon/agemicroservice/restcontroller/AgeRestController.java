package com.consulting.mgt.springboot.practica23.ribbon.agemicroservice.restcontroller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consulting.mgt.springboot.practica23.ribbon.agemicroservice.MyListener;
import com.consulting.mgt.springboot.practica23.ribbon.agemicroservice.client.IRandomService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AgeRestController {

	@Autowired
	private IRandomService randomServiceClient;

	@Autowired
	private Environment env;

	@GetMapping("/age")
	public Map<String, Object> age() {

		log.info("sending age");

		Map<String, Object> map = new LinkedHashMap<>();

		map.put("age", randomServiceClient.getRandomValue());
		map.put("from", "http://" + env.getProperty("spring.application.name") + ":" + MyListener.APPLICATION_PORT);

		return map;
	}
}
