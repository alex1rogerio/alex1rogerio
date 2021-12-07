package br.com.pagseguro.desafiocontabancaria.api.model;

public enum StatusChequeEspecial {
	
	CHEQUE_ESPECIAL_LIBERADO(1,"L","LIBERADO"),
	CHEQUE_ESPECIAL_NAO_LIBERADO(2,"NL","NÃO LIBERADO");
	
	private Integer valor;
	private String sigla;
	private String status;
	
	private StatusChequeEspecial(int i  , String sigla, String status) {
		this.valor = i;
		this.sigla = sigla;
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public String getSigla() {
		return this.sigla;
	}

	public Integer getCancel() {
		return this.valor;
	}	

}
