package br.com.lojanescoffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojanescoffee.model.Acesso;
import br.com.lojanescoffee.repository.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;
	
	public Acesso save(Acesso acesso) {
		
		/*é possível implemtar o metodo e fazer qualquer tipo de validação antes de salvar*/ 
		return acessoRepository.save(acesso);
		
	}
	
}
