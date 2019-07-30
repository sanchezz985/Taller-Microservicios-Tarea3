package com.consulting.mgt.springboot.practica23.ribbon.agemicroservice.client;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("ribbon-api")
public class RibbonApiRandomServiceClient implements IRandomService {

	private RestTemplate simpleRestTemple = new RestTemplate();
	
	@Autowired
	LoadBalancerClient loadBalancer;
	
	@Override
	@SneakyThrows
	public int getRandomValue() {

		ServiceInstance instance = loadBalancer.choose("random-microservice");
		
		// build URI using service-name
		URI uri = new URI(String.format("http://%s:%s/random-service/next", instance.getHost(), instance.getPort()));

		// getting map from loadBalanced RestTemplate
		Map<String, Object> mapResponse = simpleRestTemple.getForObject(uri, Map.class);

		log.info("[API Ribbon] {} response: {}", "random-microservice", mapResponse);

		return (int) mapResponse.get("random");
	}

}
