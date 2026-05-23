package br.com.fiap.clyvopaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ClyvovetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClyvovetApplication.class, args);
	}

}
