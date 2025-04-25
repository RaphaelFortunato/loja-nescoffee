package br.com.lojanescoffee;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.lojanescoffee.model.dto.ObjetoErroDTO;

//essas anotações fará a interceptação de exceções no projeto
@RestControllerAdvice
@ControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler({ExceptionNescoffee.class})
	public ResponseEntity<Object> handleExceptionCustom(ExceptionNescoffee ex){
		
		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
		
		objetoErroDTO.setError(ex.getMessage());
		objetoErroDTO.setCode(HttpStatus.OK.toString());
		
		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.OK);
		
	}
	

	//Captura exeções do projeto
	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
		
		String msg = "";
		
		if(ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			
			for (ObjectError objectError : list) {
				msg += objectError.getDefaultMessage() + "\n";
			}
		}else {
			msg = ex.getMessage();
		}
		
		objetoErroDTO.setError(msg);
		objetoErroDTO.setCode(status.value() + "==>" + status.getReasonPhrase());
		
		ex.printStackTrace();
		
		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//Captura erro na parte de banco de dados
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handlerExceptionDataIntery(Exception ex){
	
		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
		
		String msg = "";
		
		if(ex instanceof DataIntegrityViolationException) {
			msg = "Erro de integridade no banco: " + ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		}else {
			msg = ex.getMessage();
		}
		
		if(ex instanceof ConstraintViolationException) {
			msg = "Erro de chave estrangeira: " + ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		}else {
			msg = ex.getMessage();
		}
		
		if(ex instanceof SQLException) {
			msg = "Erro de SQL do banco: " + ((SQLException) ex).getCause().getCause().getMessage();
		}else {
			msg = ex.getMessage();
		}
		objetoErroDTO.setError(msg);
		objetoErroDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		
		ex.printStackTrace();
		
		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
