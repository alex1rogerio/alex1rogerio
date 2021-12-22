package br.com.pagseguro.desafiocontabancaria.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pagseguro.desafiocontabancaria.api.model.MovimentacaoFinanceiraClienteModel;
import br.com.pagseguro.desafiocontabancaria.domain.model.MovimentacaoFinanceiraCliente;

@Component
public class MovimentacaoFinanceiraClienteModelAssembler {
	
	@Autowired
	ModelMapper modelMapper;

	public MovimentacaoFinanceiraClienteModel toModel(MovimentacaoFinanceiraCliente cliente) {		
		return modelMapper.map(cliente, MovimentacaoFinanceiraClienteModel.class);
	}
	
	
	public List<MovimentacaoFinanceiraClienteModel> toCollectionModel(List<MovimentacaoFinanceiraCliente> clientes){
		
		return clientes.stream()
				.map(r -> toModel(r) )
				.collect(Collectors.toList());
		
	}
	
	
	

}
