package br.com.pagseguro.desafiocontabancaria.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pagseguro.desafiocontabancaria.domain.exception.ClienteNaoEncontradaException;
import br.com.pagseguro.desafiocontabancaria.domain.model.Cliente;
import br.com.pagseguro.desafiocontabancaria.domain.repository.ClienteRepository;

@Service 
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
		
	public List<Cliente> listar(){
		return clienteRepository.findAll();		
	}
	
	public Cliente porId(Long id) {				
		return clienteRepository.findById(id)
				.orElseThrow( () -> new ClienteNaoEncontradaException(id)     
		);
	}	
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);		
	}
	
	public void remover(Long id) {
		try {
			clienteRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradaException(id) ; 			
		}
	}		
	
}
