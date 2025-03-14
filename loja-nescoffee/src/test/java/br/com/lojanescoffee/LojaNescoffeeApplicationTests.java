package br.com.lojanescoffee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.lojanescoffee.controller.AcessoController;
import br.com.lojanescoffee.model.Acesso;
import br.com.lojanescoffee.repository.AcessoRepository;
import br.com.lojanescoffee.service.AcessoService;

@SpringBootTest(classes = LojaNescoffeeApplication.class)
class LojaNescoffeeApplicationTests {
	
	
	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoController acessoController;
	
	//@Autowired
	//private AcessoRepository acessoRepository;
	
	@Test
	public void testCadastraAcesso() {
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_ADMIN");
		
		acessoController.salvarAcesso(acesso);
		
	}

}
