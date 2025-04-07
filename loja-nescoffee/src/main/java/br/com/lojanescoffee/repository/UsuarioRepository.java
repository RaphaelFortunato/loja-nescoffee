package br.com.lojanescoffee.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.lojanescoffee.model.Usuario;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, Long>{
	
	
	@Query(value = "select u from Usuario u whee u.login = ?1")
	Usuario findUserByLogin(String login);

}
