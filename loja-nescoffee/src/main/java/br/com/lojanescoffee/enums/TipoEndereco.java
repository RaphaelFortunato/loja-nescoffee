package br.com.lojanescoffee.enums;

public enum TipoEndereco {

	COBRANCA("Cobrança"), //COBRANCA->para o banco - Cobrança-> para a tela
	ENTREGA("Entrega");
	
	private String descricao;
	
	private TipoEndereco(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}
	
}
