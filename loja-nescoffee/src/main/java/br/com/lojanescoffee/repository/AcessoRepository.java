package br.com.lojanescoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojanescoffee.model.Acesso;

@Repository
@Transactional/*gerencia as transações com o banco*/
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

}
