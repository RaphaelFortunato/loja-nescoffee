package br.com.lojanescoffee.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.lojanescoffee.ApplicationContextLoad;
import br.com.lojanescoffee.model.Usuario;
import br.com.lojanescoffee.repository.UsuarioRepository;
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
		
		liberacaoCors(response);
		
		//usado para ver no Postamen para teste
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}
	
	
	/*Método que retorna o usuário validado com token ou caso não seja valido retorna null*/
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if(token != null) {
			
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
			
			//Faz a validação do token do usuário na requisição e obtem o USER
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(tokenLimpo)
					.getBody().getSubject();/*pegando o admin*/
			
			if (user != null) {
				
				Usuario usuario = ApplicationContextLoad
						.getApplicationContext()
						.getBean(UsuarioRepository.class).findUserByLogin(user);
				
				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(),
							usuario.getSenha(), 
							usuario.getAuthorities());
				}
				
			}

			
		}
		
		liberacaoCors(response);
		return null;
	}
	
	
	/*Fazendo liberação contra erro de Cors no navegador obs.:esse erro não acontece no Postman*/
	private void liberacaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}

		
		
	}
	
	
	
}
