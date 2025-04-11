package br.com.lojanescoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*essas anotações ajuda na hora do projeto rodar sem grande dificuldades, com falta de ativação de recursos*/
@EntityScan(basePackages = "br.com.lojanescoffee.model")/*indica onde está minhas classes*/
@SpringBootApplication/**/
@ComponentScan(basePackages = {"br.*"})/*varre todas as pastas procurando recursos e dependencias*/
@EnableJpaRepositories(basePackages = {"br.com.lojanescoffee.repository"})/*indica onde está os repository*/
@EnableTransactionManagement/*ajuda na parte de transação de informações com o banco*/
public class LojaNescoffeeApplication {

	
	
	public static void main(String[] args) {
		
		System.out.println(new BCryptPasswordEncoder().encode("123"));
		
		SpringApplication.run(LojaNescoffeeApplication.class, args);
		
	}

}
