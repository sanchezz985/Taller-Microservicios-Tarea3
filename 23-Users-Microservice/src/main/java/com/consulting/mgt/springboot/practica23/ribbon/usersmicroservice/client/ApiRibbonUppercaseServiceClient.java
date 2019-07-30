package com.consulting.mgt.springboot.practica23.ribbon.usersmicroservice.client;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class ApiRibbonUppercaseServiceClient implements IUppercaseService {

	private RestTemplate simpleRestTemple = new RestTemplate();
	
	@Autowired
	LoadBalancerClient loadBalancer;
	
	@Value("${uppercase-service-name:uppercase-microservice}")
	private String serviceName;

	@Override
	@SneakyThrows
	public String toUppercase(String name) {

		ServiceInstance instance = loadBalancer.choose(serviceName);
		
		// build URI using service-name
		URI uri = new URI(String.format("http://%s:%s/uppercase-service/toUppercase/%s", instance.getHost(), instance.getPort(), name));

		// getting map from loadBalanced RestTemplate
		Map<String, Object> mapResponse = simpleRestTemple.getForObject(uri, Map.class);

		log.info("[API Ribbon] {} response: {}", serviceName, mapResponse);

		return (String) mapResponse.get("uppercase");
	}

}
