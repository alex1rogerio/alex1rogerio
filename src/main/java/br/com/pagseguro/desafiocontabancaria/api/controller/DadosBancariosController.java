package br.com.pagseguro.desafiocontabancaria.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pagseguro.desafiocontabancaria.api.assembler.DadosBancariosModelAssembler;
import br.com.pagseguro.desafiocontabancaria.api.assembler.DadosBancariosModelInputDesassembler;
import br.com.pagseguro.desafiocontabancaria.api.model.DadosBancariosModel;
import br.com.pagseguro.desafiocontabancaria.api.model.DadosBancariosPesquisaNumeroContaModel;
import br.com.pagseguro.desafiocontabancaria.api.model.Input.DadosBancariosInput;
import br.com.pagseguro.desafiocontabancaria.domain.exception.DadosBancariosNaoEncontradaException;
import br.com.pagseguro.desafiocontabancaria.domain.exception.NegocioException;
import br.com.pagseguro.desafiocontabancaria.domain.model.DadosBancarios;
import br.com.pagseguro.desafiocontabancaria.domain.service.DadosBancariosService;



@RestController
@RequestMapping(value = "/dadosBancarios")
public class DadosBancariosController {
	
	@Autowired
	private DadosBancariosService dadosBancariosService;
	
	@Autowired
	private DadosBancariosModelAssembler dadosBancariosModelAssembler;
	
	@Autowired
	private DadosBancariosModelInputDesassembler dadosBancariosModelInputDesassembler;
	
	DadosBancariosModel dadosBancariosModel = null;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	public List<DadosBancariosModel> listar(){		
		return   dadosBancariosModelAssembler.toCollectionModel( dadosBancariosService.listar());		
	}
	
	@GetMapping("/{dadosBancariosId}")
	public DadosBancariosModel buscar(@PathVariable Long dadosBancariosId){
		DadosBancarios dadosBancarios =  dadosBancariosService.porId(dadosBancariosId);
		return  dadosBancariosModelAssembler.toModel(dadosBancarios);
	}
	
	@GetMapping("/consultaPorNomeAgenciaChequeLiberado")
	public ResponseEntity<DadosBancariosModel> buscarNomeAgenciaChequeLiberado(String nome , String agencia, Boolean chequeEspecialLiberado){
		DadosBancarios dadosBancarios =  dadosBancariosService.consultaPorNomeAgenciaChequeLiberado(nome, agencia, chequeEspecialLiberado);
		
		if (dadosBancarios == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(dadosBancariosModelAssembler.toModel(dadosBancarios));
	}	
	
	@GetMapping("/consultaPorNumeroDeConta")
	public ResponseEntity<DadosBancariosPesquisaNumeroContaModel> buscarNumeroDeConta(String numeroConta){
		DadosBancarios dadosBancarios =  dadosBancariosService.buscaPorNumeroConta(numeroConta);		
		
		if (dadosBancarios != null) {
			var dadosBancariosPesquisaNumeroContaModel =new DadosBancariosPesquisaNumeroContaModel(dadosBancariosModelAssembler.toModel(dadosBancarios));
			return ResponseEntity.status(HttpStatus.OK).body(dadosBancariosPesquisaNumeroContaModel);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}			
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public 	DadosBancariosModel adicionar(@RequestBody @Valid DadosBancariosInput dadosBancariosInput) {

		try {
			dadosBancariosModel  =dadosBancariosModelAssembler.toModel(
					dadosBancariosService.salvar(dadosBancariosModelInputDesassembler.toDomainObject(dadosBancariosInput)));						
			
			return dadosBancariosModel;		
			
		}catch(DadosBancariosNaoEncontradaException e ) {			
			throw new NegocioException(e.getMessage() , e);
		}
	}
	
	@PutMapping("/{dadosBancariosId}")	
	public DadosBancariosModel atualizar(@PathVariable Long dadosBancariosId,  @Valid @RequestBody DadosBancariosInput dadosBancariosInput) {				
		DadosBancarios dadosBancariosAtual = dadosBancariosService.porId(dadosBancariosId);		
		dadosBancariosAtual = dadosBancariosModelInputDesassembler.copyToDomainObject(dadosBancariosInput, dadosBancariosAtual);
		try {
			dadosBancariosModel  = dadosBancariosModelAssembler.toModel(dadosBancariosService.salvar(dadosBancariosAtual));

			return dadosBancariosModel;
		
		}catch(DadosBancariosNaoEncontradaException e ) {			
			throw new NegocioException(e.getMessage() , e);
		}		
	}
	
	@DeleteMapping("/{dadosBancariosId}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long dadosBancariosId) {
		dadosBancariosService.remover(dadosBancariosId);		
	}
	
}