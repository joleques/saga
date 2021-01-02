package com.poc.financeiro.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguracao {

	@Bean
	public RestTemplate criar() {
		return new RestTemplate();
	}
}
