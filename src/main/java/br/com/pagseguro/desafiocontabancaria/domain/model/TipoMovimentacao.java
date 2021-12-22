package br.com.pagseguro.desafiocontabancaria.domain.model;

public enum TipoMovimentacao {

	S("Saque"),
	D("Deposito");
	
	private String descricao;
	
	private TipoMovimentacao(String descricao) {
		this.descricao = descricao;
		
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
