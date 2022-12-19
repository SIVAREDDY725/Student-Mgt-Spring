package com.avis.fee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.avis.fee.repository")
@EnableJpaAuditing
@EnableFeignClients
public class StudentFeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentFeeServiceApplication.class, args);
	}

}
