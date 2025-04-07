package br.com.lojanescoffee.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/*Criar a autenticacao e retornar a autenticacao JWT*/
@Service
@Component
public class JWTTokenAutenticacaoService {

	/*Token de validade de 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;
	
	/*Chave de senha para juntar com o JWT*/
	private static final String SECRET = "ss/-*-*sds565dsd-s/d-s*dsds";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";


	/*Gera o token e da a responsta para o cliente o com JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		
		/*Montagem do token*/
		
		String JWT = Jwts.builder()//chama o gerador de token
				.setSubject(username)//adiciona o user
				.setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))//tempo de expiração
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();//chave de compactação
		
		/*Exe: Bearer *-/a*dad9s5d6as5d4s5d4s45dsd54s.sd4s4d45s45d4sd54d45s4d5s.ds5d5s5d5s65d6s6d*/
		//pega a nível de código
		String token = TOKEN_PREFIX + " " + JWT;
		
		
		/*Dá a resposta pra tela e para o cliente, outra API, navegador, aplicativo, javascript, outra chamadajava*/
		response.addHeader(HEADER_STRING, token);
		
		//usado para ver no Postamen para teste
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}
	
	
	
}
