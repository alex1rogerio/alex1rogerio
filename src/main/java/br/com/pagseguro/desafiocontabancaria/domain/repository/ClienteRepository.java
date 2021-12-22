package br.com.pagseguro.desafiocontabancaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.pagseguro.desafiocontabancaria.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>  , JpaSpecificationExecutor<Cliente>{
	
	
}
