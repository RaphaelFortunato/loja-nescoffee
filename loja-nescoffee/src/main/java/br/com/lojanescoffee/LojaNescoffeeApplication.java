package br.com.lojanescoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "br.com.lojanescoffee.model")
@SpringBootApplication
public class LojaNescoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaNescoffeeApplication.class, args);
	}

}
