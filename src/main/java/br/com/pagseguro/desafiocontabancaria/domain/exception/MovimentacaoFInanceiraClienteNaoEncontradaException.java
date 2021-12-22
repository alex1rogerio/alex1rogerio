package br.com.pagseguro.desafiocontabancaria.domain.exception;


public class MovimentacaoFInanceiraClienteNaoEncontradaException  extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;
	
	public MovimentacaoFInanceiraClienteNaoEncontradaException(String mensagem) {
		super(mensagem);
	}	
	public MovimentacaoFInanceiraClienteNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro MovimentacaoFinanceira com código %d", id));
	}	

}
