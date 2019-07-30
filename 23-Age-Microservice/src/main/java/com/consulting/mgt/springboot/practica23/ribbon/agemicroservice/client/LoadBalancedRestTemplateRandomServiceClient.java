package com.consulting.mgt.springboot.practica23.ribbon.agemicroservice.client;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("load-balanced-rest-template")
public class LoadBalancedRestTemplateRandomServiceClient implements IRandomService {

	//Inyecta Bean Balanceado con Ribbon RestTemplate loadBalancedRestTemplate
	@Autowired
	private RestTemplate loadBalancedRestTemplate;

	@SneakyThrows
	public int getRandomValue() {
		
		log.info("[Load balanced] generating random value from");
		
		// Implementa
		URI uri = new URI(String.format("http://%s/random-service/next", "random-microservice"));
		@SuppressWarnings("unchecked")
		Map<String, Object> mapResponse = loadBalancedRestTemplate.getForObject(uri, Map.class);
		return (int) mapResponse.get("random");
	}
	
}