package br.com.facio.mkube.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SimpleAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleAppBackendApplication.class, args);
	}

}
