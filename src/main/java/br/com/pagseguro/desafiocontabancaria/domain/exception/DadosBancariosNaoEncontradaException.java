package br.com.pagseguro.desafiocontabancaria.domain.exception;


public class DadosBancariosNaoEncontradaException  extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;
	
	public DadosBancariosNaoEncontradaException(String mensagem) {
		super(mensagem);
	}	
	public DadosBancariosNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de Dados Bancarios com código %d", id));
	}	

}
