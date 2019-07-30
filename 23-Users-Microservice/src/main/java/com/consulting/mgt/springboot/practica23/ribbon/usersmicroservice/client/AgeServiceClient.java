package com.consulting.mgt.springboot.practica23.ribbon.usersmicroservice.client;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AgeServiceClient {

	//Inyecta Bean Balanceado con Ribbon RestTemplate loadBalancedRestTemplate
	@Autowired
	private RestTemplate loadBalancedRestTemplate;

	@Value("${age-service-name:age-microservice}")
	private String serviceName;

	@SneakyThrows
	public int getAge() {
		
		// Implementa
		URI uri = new URI(String.format("http://%s/age-service/age", serviceName));
		Map<String, Object> mapResponse = loadBalancedRestTemplate.getForObject(uri, Map.class);
		return (int) mapResponse.get("age");
	}

}
