package com.tn.skillexchange.skillexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SkillexchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillexchangeApplication.class, args);
	}

}
