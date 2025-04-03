package br.com.lojanescoffee;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lojanescoffee.controller.AcessoController;
import br.com.lojanescoffee.model.Acesso;
import br.com.lojanescoffee.repository.AcessoRepository;
import junit.framework.TestCase;


@SpringBootTest(classes = LojaNescoffeeApplication.class)
class LojaNescoffeeApplicationTests extends TestCase {
	
	
	//@Autowired
	//private AcessoService acessoService;

	@Autowired
	private AcessoController acessoController;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private WebApplicationContext wac;
	
	
	/*teste do end-point de salvar com Mockito*/
	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
				
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_COMPRADOR");
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc
								.perform(MockMvcRequestBuilders.post("/salvarAcesso")/*Perform = passamos os dados nele através do endpoint*/
								.content(mapper.writeValueAsString(acesso))/*Content = o contéud que é passado é o objeto que queremos testar (nesse caso salvar)*/
								.accept(MediaType.APPLICATION_JSON)/*Accept = tipo de contéudo que estamos passando*/
								.contentType(MediaType.APPLICATION_JSON));/*ContentType = tipo de contéudo que estamos passando*/
		
		/*amostra do retorno da api*/
		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
		
		/*Converter o retorno da API em objeto */
		Acesso objetoRetorno = mapper.
							readValue(retornoApi.andReturn().getResponse().getContentAsString(),
							Acesso.class);
	
		assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
		
	}
	
	/*teste do end-point de deletar (modelo 1) com Mockito*/
	@Test
	public void testRestApiDeleteAcesso() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
				
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_DELETE");
		
		acesso = acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc
								.perform(MockMvcRequestBuilders.post("/deleteAcesso")/*Perform = passamos os dados nele através do endpoint*/
								.content(mapper.writeValueAsString(acesso))/*Content = o contéud que é passado é o objeto que queremos testar (nesse caso salvar)*/
								.accept(MediaType.APPLICATION_JSON)/*Accept = tipo de contéudo que estamos passando*/
								.contentType(MediaType.APPLICATION_JSON));/*ContentType = tipo de contéudo que estamos passando*/
		
		/*amostra do retorno da api*/
		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Status de retorno: " + retornoApi.andReturn().getResponse().getStatus());
		
		assertEquals("Acesso removido", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
	}
	
	
	/*teste do end-point de deletar (modelo 2) com Mockito*/
	@Test
	public void testRestApiDeletePorIdAcesso() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
				
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_DELETE_POR_ID");
		
		acesso = acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc
								.perform(MockMvcRequestBuilders.delete("/deleteAcessoPorId/" + acesso.getId())/*Perform = passamos os dados nele através do endpoint*/
								.content(mapper.writeValueAsString(acesso))/*Content = o contéud que é passado é o objeto que queremos testar (nesse caso salvar)*/
								.accept(MediaType.APPLICATION_JSON)/*Accept = tipo de contéudo que estamos passando*/
								.contentType(MediaType.APPLICATION_JSON));/*ContentType = tipo de contéudo que estamos passando*/
		
		/*amostra do retorno da api*/
		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Status de retorno: " + retornoApi.andReturn().getResponse().getStatus());
		
		assertEquals("Acesso removido", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
	}
	
	
	/*teste do end-point de consultar com Mockito*/
	@Test
	public void testRestApiObeterAcessoId() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
				
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_CONSULTAR_POR_ID");
		
		acesso = acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc
								.perform(MockMvcRequestBuilders.get("/obterAcesso/" + acesso.getId())
								.content(mapper.writeValueAsString(acesso))
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON));
		
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
		Acesso acessoRetorno = mapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
		
		//validaçao
		assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());
		assertEquals(acesso.getId(), acessoRetorno.getId());
	}
	
	
	
	/*teste do end-point de consultar com Mockito*/
	@Test
	public void testRestApiObeterAcessoDesc() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
				
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_CONSULTAR_POR_LIST");
		
		acesso = acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc
								.perform(MockMvcRequestBuilders.get("/buscarPorDesc/POR_LIST")
								.content(mapper.writeValueAsString(acesso))
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON));
		
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
		List<Acesso> retornoApiList = mapper.
									readValue(retornoApi.andReturn().getResponse().getContentAsString(),
									new TypeReference<List<Acesso>> (){}); //converte em lista
		
		assertEquals(1, retornoApiList.size());
		assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());
		
		
		acessoRepository.deleteById(acesso.getId());
		
	}
	
	
	
	
	@Test
	public void testCadastraAcesso() {
		
		Acesso acesso = new Acesso();
	
		
		acesso.setDescricao("ROLE_ADMIN");
		
		assertEquals(true, acesso.getId() == null);
		
		/*Gravou no banco de dados*/
		acesso = acessoController.salvarAcesso(acesso).getBody();
		
		assertEquals(true, acesso.getId() > 0);
		
		/*Validar dados salvos da forma correta*/
		assertEquals("ROLE_ADMIN", acesso.getDescricao());
		
		
		/*teste de carregamento , passado o acesso estamos comparando com o acesso2 para ver se é igual*/
		
		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
		
		assertEquals(acesso.getId(), acesso2.getId());
		
		/*teste de delete*/
		acessoRepository.deleteById(acesso2.getId());
		
		acessoRepository.flush(); /*Roda esse SQL de delete no banco de dados*/
		
		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);/*para evitar exceção colocamos o orElse*/
		
		assertEquals(true, acesso3 == null);
		
		
		/*teste de query*/
		
		acesso = new Acesso();
		
		acesso.setDescricao("ROLE_ALUNO");
		
		acesso = acessoController.salvarAcesso(acesso).getBody();
		
		List<Acesso> acessos = acessoRepository.buscarAcessoDescricao("ALUNO".trim().toUpperCase());
		
		assertEquals(1, acessos.size());
		
		acessoRepository.deleteById(acesso.getId());


		
		
		
	}

}
