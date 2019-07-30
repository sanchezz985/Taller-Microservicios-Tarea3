package com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomService {

	@Autowired
	private Random random;

	public int next() {
		return random.nextInt(40);
	}
}