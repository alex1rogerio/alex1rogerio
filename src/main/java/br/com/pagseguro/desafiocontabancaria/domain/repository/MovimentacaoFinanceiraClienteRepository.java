package br.com.pagseguro.desafiocontabancaria.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.pagseguro.desafiocontabancaria.domain.model.MovimentacaoFinanceiraCliente;

public interface MovimentacaoFinanceiraClienteRepository extends JpaRepository<MovimentacaoFinanceiraCliente, Long>  , JpaSpecificationExecutor<MovimentacaoFinanceiraCliente>{
	
	@Query("select m from MovimentacaoFinanceiraCliente m where m.cliente.id =:clienteId")
	List<MovimentacaoFinanceiraCliente> findByCliente(Long clienteId);
}
