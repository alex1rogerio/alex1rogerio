package br.com.pagseguro.desafiocontabancaria.infrastructure.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import br.com.pagseguro.desafiocontabancaria.domain.filter.HistoricoMovimentacao;
import br.com.pagseguro.desafiocontabancaria.domain.model.MovimentacaoFinanceiraCliente;
import br.com.pagseguro.desafiocontabancaria.domain.repository.HistoricoMovimentacaoFInanceira;

@Repository
public class HistoricoMovimentacaoFinanceira  implements HistoricoMovimentacaoFInanceira {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<MovimentacaoFinanceiraCliente> historicoPorDatas(HistoricoMovimentacao filtro) {

		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(MovimentacaoFinanceiraCliente.class);
		var root = query.from(MovimentacaoFinanceiraCliente.class);
		
		var predicates = new ArrayList<Predicate>();

		if(filtro.getClienteId() != null) {
			predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
		}
		if(filtro.getDataInicial() != null) {
			
			LocalDate dat = filtro.getDataInicial();
			predicates.add(builder.greaterThanOrEqualTo(root.get("data"), filtro.getDataInicial()));
		}
		if(filtro.getDataFinal() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("data"), filtro.getDataFinal()));
		}			

		query.where(predicates.toArray(new Predicate[0]));
		return  manager.createQuery(query).getResultList();

	}

}
