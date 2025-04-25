package br.com.lojanescoffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojanescoffee.ExceptionNescoffee;
import br.com.lojanescoffee.model.Acesso;
import br.com.lojanescoffee.repository.AcessoRepository;
import br.com.lojanescoffee.service.AcessoService;

//@CrossOrigin(origins = "https://www.jdevtreinamento.com.br")
@Controller
@RestController
public class AcessoController {

	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@ResponseBody/*retorno da API*/
	@PostMapping(value = "**/salvarAcesso")/*Mapeando a url para receebr JSON*/
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) throws ExceptionNescoffee {/*@RequestBody = recebe o JSON e converte para objeto*/
		
		if(acesso.getId() == null ) {
			
				List<Acesso> acessos = acessoRepository.buscarAcessoDescricao(acesso.getDescricao().toUpperCase());
			
			if(!acessos.isEmpty()) {
				throw new ExceptionNescoffee("Já existe acesso com a descrição: " + acesso.getDescricao());
			}
		}
		Acesso acessoSalvo = acessoService.save(acesso);
		
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
		
	}
	
	@ResponseBody/*retorno da API*/
	@PostMapping(value = "**/deleteAcesso")/*Mapeando a url para receebr JSON*/
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {/*@RequestBody = recebe o JSON e converte para objeto*/
		
		acessoRepository.deleteById(acesso.getId());
		
		return new ResponseEntity("Acesso removido", HttpStatus.OK);
		
	}
	
	
	@ResponseBody
	@DeleteMapping(value = "**/deleteAcessoPorId/{id}")
	public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {
		
		acessoRepository.deleteById(id);
		
		return new ResponseEntity("Acesso removido", HttpStatus.OK);
		
	}
	
	//@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody
	@GetMapping(value = "**/obterAcesso/{id}")
	public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) throws ExceptionNescoffee {
		
		Acesso acesso = acessoRepository.findById(id).orElse(null);
		
		if(acesso == null) {
			throw new ExceptionNescoffee("Não encontrou com código: " + id);
		}
		
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
		
	}
	
	@ResponseBody
	@GetMapping(value = "**/buscarPorDesc/{desc}")
	public ResponseEntity<List<Acesso>> buscarPorDesc(@PathVariable("desc") String desc) {
		
		List<Acesso> acesso = acessoRepository.buscarAcessoDescricao(desc.toUpperCase());
		
		return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);
		
	}
	
	
	
	
}
