package br.com.pagseguro.desafiocontabancaria.domain.exception;


public class ClienteNaoEncontradaException  extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;
	
	public ClienteNaoEncontradaException(String mensagem) {
		super(mensagem);
	}	
	public ClienteNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro Cliente com código %d", id));
	}	

}
