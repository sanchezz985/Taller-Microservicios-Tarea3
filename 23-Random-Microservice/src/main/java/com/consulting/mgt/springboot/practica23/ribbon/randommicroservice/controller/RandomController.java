package com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.MyListener;
import com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.service.RandomService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RandomController {

	@Autowired RandomService randomService;
	@Autowired Environment env;
	
	@GetMapping("/next")
	public Map<String, Object> getRandom(){
		log.info("Random ...");
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("random", randomService.next());
		map.put("from", "http://" + env.getProperty("spring.application.name") + ":" +
		MyListener.APPLICATION_PORT);
		return map;
	}
	
}