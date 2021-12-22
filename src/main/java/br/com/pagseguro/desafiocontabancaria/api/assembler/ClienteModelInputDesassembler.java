package br.com.pagseguro.desafiocontabancaria.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pagseguro.desafiocontabancaria.api.model.Input.ClienteInput;
import br.com.pagseguro.desafiocontabancaria.domain.model.Cliente;

@Component
public class ClienteModelInputDesassembler {
	
	@Autowired
	ModelMapper modelMapper;	
	
	public Cliente toDomainObject(ClienteInput c) {
		return modelMapper.map( c , Cliente.class);
	}
	
	public void copyToDomainObject(ClienteInput clienteInput , Cliente cliente  ) {

		modelMapper.map(clienteInput , cliente);
		
	}
}
