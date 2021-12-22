package br.com.pagseguro.desafiocontabancaria.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pagseguro.desafiocontabancaria.api.model.Input.MovimentacaoFinanceiraClienteInput;
import br.com.pagseguro.desafiocontabancaria.domain.model.MovimentacaoFinanceiraCliente;

@Component
public class MovimentacaoFinanceiraClienteModelInputDesassembler {
	
	@Autowired
	ModelMapper modelMapper;	
	
	public MovimentacaoFinanceiraCliente toDomainObject(MovimentacaoFinanceiraClienteInput c) {		
		return modelMapper.map( c , MovimentacaoFinanceiraCliente.class);
	}
	
	public void copyToDomainObject(MovimentacaoFinanceiraClienteInput clienteInput , MovimentacaoFinanceiraCliente cliente  ) {

		modelMapper.map(clienteInput , cliente);
		
	}
}
