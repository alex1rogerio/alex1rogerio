package br.com.pagseguro.desafiocontabancaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.pagseguro.desafiocontabancaria.domain.model.DadosBancarios;

public interface DadosBancariosRepository extends JpaRepository<DadosBancarios, Long>  , JpaSpecificationExecutor<DadosBancarios>{
	
	@Query("from DadosBancarios where nome like %:nome% and agencia =:agencia and  chequeEspecialLiberado =:chequeEspecialLiberado")
	DadosBancarios consultaPorNomeAgenciaChequeLiberado(String nome, String agencia, Boolean chequeEspecialLiberado);

	@Query("from DadosBancarios where numeroConta  =:numeroConta")
	DadosBancarios consultaPorNumeroConta(String numeroConta);
	
	
}
