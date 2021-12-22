package br.com.pagseguro.desafiocontabancaria.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pagseguro.desafiocontabancaria.api.model.ClienteModel;
import br.com.pagseguro.desafiocontabancaria.domain.model.Cliente;

@Component
public class ClienteModelAssembler {
	
	@Autowired
	ModelMapper modelMapper;

	public ClienteModel toModel(Cliente cliente) {		
		return modelMapper.map(cliente, ClienteModel.class);
	}
	
	
	public List<ClienteModel> toCollectionModel(List<Cliente> clientes){
		
		return clientes.stream()
				.map(r -> toModel(r) )
				.collect(Collectors.toList());
		
	}
	
	
	

}
