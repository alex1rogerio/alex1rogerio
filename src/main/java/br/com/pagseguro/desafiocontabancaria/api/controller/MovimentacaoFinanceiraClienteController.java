package br.com.pagseguro.desafiocontabancaria.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pagseguro.desafiocontabancaria.api.assembler.MovimentacaoFinanceiraClienteModelAssembler;
import br.com.pagseguro.desafiocontabancaria.api.assembler.MovimentacaoFinanceiraClienteModelInputDesassembler;
import br.com.pagseguro.desafiocontabancaria.api.model.MovimentacaoFinanceiraClienteModel;
import br.com.pagseguro.desafiocontabancaria.api.model.Input.MovimentacaoFinanceiraClienteInput;
import br.com.pagseguro.desafiocontabancaria.domain.exception.ClienteNaoEncontradaException;
import br.com.pagseguro.desafiocontabancaria.domain.exception.NegocioException;
import br.com.pagseguro.desafiocontabancaria.domain.service.MovimentacaoFinanceiraClienteService;

@RestController
@RequestMapping(path = "/cliente/{clienteId}/movimentacao-financeira")
public class MovimentacaoFinanceiraClienteController {
	
	@Autowired
	private MovimentacaoFinanceiraClienteService movimentacaoFinanceiraClienteService;
	
	@Autowired
	private MovimentacaoFinanceiraClienteModelAssembler movimentacaoFinanceiraClienteModelAssembler;
	
	@Autowired
	private MovimentacaoFinanceiraClienteModelInputDesassembler movimentacaoFinanceiraClienteModelInputDesassembler;
	
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovimentacaoFinanceiraClienteModel> listar(@PathVariable Long clienteId){		
		return   movimentacaoFinanceiraClienteModelAssembler.toCollectionModel( movimentacaoFinanceiraClienteService.listar(clienteId));		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public 	MovimentacaoFinanceiraClienteModel salvar(@RequestBody @Valid MovimentacaoFinanceiraClienteInput clienteInput, @PathVariable Long clienteId) {
		
		try {			
			return movimentacaoFinanceiraClienteModelAssembler.toModel(
					movimentacaoFinanceiraClienteService.salvar(movimentacaoFinanceiraClienteModelInputDesassembler.toDomainObject(clienteInput),clienteId));						
			
		}catch(ClienteNaoEncontradaException e ) {			
			throw new NegocioException(e.getMessage() , e);
		}
	}
	

}