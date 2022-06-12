package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class CambioCrudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambioCrudEurekaApplication.class, args);
	}

}
