package br.com.pagseguro.desafiocontabancaria.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pagseguro.desafiocontabancaria.api.model.DadosBancariosModel;
import br.com.pagseguro.desafiocontabancaria.domain.model.DadosBancarios;

@Component
public class DadosBancariosModelAssembler {
	
	@Autowired
	ModelMapper modelMapper;

	public DadosBancariosModel toModel(DadosBancarios dadosBancarios) {		
		return modelMapper.map(dadosBancarios, DadosBancariosModel.class);
	}
	
	
	public List<DadosBancariosModel> toCollectionModel(List<DadosBancarios> dadosBancarios){
		
		return dadosBancarios.stream()
				.map(r -> toModel(r) )
				.collect(Collectors.toList());
		
	}
	
	
	

}
