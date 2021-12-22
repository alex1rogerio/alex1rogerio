package br.com.pagseguro.desafiocontabancaria.domain.exception;


public class ClienteSaldoNaoEncontradaException  extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ClienteSaldoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}	
	public ClienteSaldoNaoEncontradaException(Long id) {
		this(String.format("Saldo insuficiente para Saque do Cliente com código %d", id));
	}	

}
