package br.com.pagseguro.desafiocontabancaria.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pagseguro.desafiocontabancaria.domain.model.DadosBancarios;
import br.com.pagseguro.desafiocontabancaria.domain.repository.DadosBancariosRepository;

@Service 
public class DadosBancariosService {

	private static final String MSG_DADOS_BANCARIOS_EM_USO = "";
	
	@Autowired
	private DadosBancariosRepository dadosBancariosRepository;
		
	public List<DadosBancarios> listar(){
		return dadosBancariosRepository.findAll();		
	}
	
	public DadosBancarios porId(Long id) {				
		return dadosBancariosRepository.findById(id).get();
				//.orElseThrow( () -> new RestauranteNaoEncontradoException(restauranteId)    
		//);
	}
	
	public DadosBancarios buscaPorNumeroConta(String numeroConta) {
		return dadosBancariosRepository.consultaPorNumeroConta(numeroConta);		
	}
	
	public DadosBancarios consultaPorNomeAgenciaChequeLiberado(String nome , String agencia, Boolean chequeEspecialLiberado ) {
		return dadosBancariosRepository.consultaPorNomeAgenciaChequeLiberado(nome, agencia, chequeEspecialLiberado);
		
	}	
	
	@Transactional
	public DadosBancarios  salvar(DadosBancarios dados) {
		return dadosBancariosRepository.save(dados);		
	}
	
	public void remover(Long id) {
		//try {
			dadosBancariosRepository.deleteById(id);
//		}catch(EmptyResultDataAccessException e) {
//			throw new RestauranteNaoEncontradoException(id); 			
//		}catch(DataIntegrityViolationException e) {
//			throw new EntidadeEmUsoException(
//				String.format(MSG_RESTAURANTE_EM_USO, id));			
//		}
	}		
	
	
	
}
