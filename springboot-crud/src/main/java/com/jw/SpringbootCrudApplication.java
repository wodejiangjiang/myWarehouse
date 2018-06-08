package com.jw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudApplication.class, args);
	}
}
