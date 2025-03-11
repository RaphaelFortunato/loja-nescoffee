package br.com.lojanescoffee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id") /* referencia o ID da classe que foi extendida */
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String cnpj;
	
	@Column(nullable = false)
	private String inscrEstatudal;
	
	private String inscrMunicipal;
	
	@Column(nullable = false)
	private String nomeFantasia;
	
	@Column(nullable = false)
	private String razaoSocial;
	
	private String categoria;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscrEstatudal() {
		return inscrEstatudal;
	}

	public void setInscrEstatudal(String inscrEstatudal) {
		this.inscrEstatudal = inscrEstatudal;
	}

	public String getInscrMunicipal() {
		return inscrMunicipal;
	}

	public void setInscrMunicipal(String inscrMunicipal) {
		this.inscrMunicipal = inscrMunicipal;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
