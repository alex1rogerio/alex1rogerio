package br.com.pagseguro.desafiocontabancaria.domain.repository;

import java.util.List;

import br.com.pagseguro.desafiocontabancaria.domain.filter.HistoricoMovimentacao;
import br.com.pagseguro.desafiocontabancaria.domain.model.MovimentacaoFinanceiraCliente;

public interface HistoricoMovimentacaoFInanceira {
	
	List<MovimentacaoFinanceiraCliente>  historicoPorDatas(HistoricoMovimentacao filtro);	
	
}
