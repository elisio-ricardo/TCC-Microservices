package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookCrudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCrudEurekaApplication.class, args);
	}

}
