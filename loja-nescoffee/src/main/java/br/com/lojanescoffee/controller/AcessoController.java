package br.com.lojanescoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojanescoffee.model.Acesso;
import br.com.lojanescoffee.repository.AcessoRepository;
import br.com.lojanescoffee.service.AcessoService;

@Controller
@RestController
public class AcessoController {

	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@ResponseBody/*retorno da API*/
	@PostMapping(value = "**/salvarAcesso")/*Mapeando a url para receebr JSON*/
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {/*@RequestBody = recebe o JSON e converte para objeto*/
		
		Acesso acessoSalvo = acessoService.save(acesso);
		
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
		
	}
	
	@ResponseBody/*retorno da API*/
	@PostMapping(value = "**/deleteAcesso")/*Mapeando a url para receebr JSON*/
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {/*@RequestBody = recebe o JSON e converte para objeto*/
		
		acessoRepository.deleteById(acesso.getId());
		
		return new ResponseEntity("Acesso removido", HttpStatus.OK);
		
	}
	
}
