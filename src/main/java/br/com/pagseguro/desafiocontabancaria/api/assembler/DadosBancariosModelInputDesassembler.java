package br.com.pagseguro.desafiocontabancaria.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pagseguro.desafiocontabancaria.api.model.Input.DadosBancariosInput;
import br.com.pagseguro.desafiocontabancaria.domain.model.DadosBancarios;

@Component
public class DadosBancariosModelInputDesassembler {
	
	@Autowired
	ModelMapper modelMapper;	
	
	public DadosBancarios toDomainObject(DadosBancariosInput d) {
		return modelMapper.map( d , DadosBancarios.class);
	}
	
	public DadosBancarios copyToDomainObject(DadosBancariosInput dadosBancariosInput , DadosBancarios dadosBancarios  ) {
		
		//identifier of an instance of com.algaworks.algafood.domain.model.Cozinha 
		//was altered from 1 to 2; nested exception is org.hibernate.HibernateException:
		//Codigo para tratar o exception acima
		//restaurante.setCozinha(new Cozinha());
		//if(restaurante.getEndereco() != null) {
			//restaurante.getEndereco().setCidade(new Cidade());
		//}
//		modelMapper.map(dadosBancariosInput , dadosBancarios);
		
		if(dadosBancariosInput.getChequeEspecialLiberado() != null) {
			dadosBancarios.setChequeEspecialLiberado(dadosBancariosInput.getChequeEspecialLiberado());
		}
		if(dadosBancariosInput.getAgencia() != null) {
			dadosBancarios.setAgencia(dadosBancariosInput.getAgencia());			
		}
		if(dadosBancariosInput.getChequeEspecial() != null) {
			dadosBancarios.setChequeEspecial(dadosBancariosInput.getChequeEspecial());			
		}
		if(dadosBancariosInput.getNome() != null) {
			dadosBancarios.setNome(dadosBancariosInput.getNome());						
		}
		if(dadosBancariosInput.getNumeroConta() != null) {
			dadosBancarios.setNumeroConta(dadosBancariosInput.getNumeroConta());									
		}
		if(dadosBancariosInput.getSaldo() != null) {
			dadosBancarios.setSaldo(dadosBancariosInput.getSaldo());												
		}
		if(dadosBancariosInput.getTaxa() != null) {
			dadosBancarios.setTaxa(dadosBancariosInput.getTaxa());												
		}		
		
		return dadosBancarios;
	}
}
