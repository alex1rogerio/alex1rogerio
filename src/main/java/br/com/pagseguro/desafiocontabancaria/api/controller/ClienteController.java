package br.com.pagseguro.desafiocontabancaria.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pagseguro.desafiocontabancaria.api.assembler.ClienteModelAssembler;
import br.com.pagseguro.desafiocontabancaria.api.assembler.ClienteModelInputDesassembler;
import br.com.pagseguro.desafiocontabancaria.api.model.ClienteModel;
import br.com.pagseguro.desafiocontabancaria.api.model.Input.ClienteInput;
import br.com.pagseguro.desafiocontabancaria.domain.exception.ClienteNaoEncontradaException;
import br.com.pagseguro.desafiocontabancaria.domain.exception.NegocioException;
import br.com.pagseguro.desafiocontabancaria.domain.model.Cliente;
import br.com.pagseguro.desafiocontabancaria.domain.service.ClienteService;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteModelAssembler clienteModelAssembler;
	
	@Autowired
	private ClienteModelInputDesassembler clienteModelInputDesassembler;
	
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClienteModel> listar(){		
		return   clienteModelAssembler.toCollectionModel( clienteService.listar());		
	}
	
	@GetMapping("/{clienteId}")
	public ClienteModel buscar(@PathVariable Long clienteId){
		Cliente cliente =  clienteService.porId(clienteId);
		return  clienteModelAssembler.toModel(cliente);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public 	ClienteModel adicionar(@RequestBody @Valid ClienteInput clienteInput) {

		try {
			return clienteModelAssembler.toModel(
					clienteService.salvar(clienteModelInputDesassembler.toDomainObject(clienteInput)));						
			
		}catch(ClienteNaoEncontradaException e ) {			
			throw new NegocioException(e.getMessage() , e);
		}
	}
	
	@PutMapping("/{clienteId}")	
	public ClienteModel atualizar(@PathVariable Long clienteId,  @Valid @RequestBody ClienteInput clienteInput) {				
		Cliente clienteAtual = clienteService.porId(clienteId);		
		clienteModelInputDesassembler.copyToDomainObject(clienteInput, clienteAtual);
		try {
			return clienteModelAssembler.toModel(clienteService.salvar(clienteAtual));			
		
		}catch(ClienteNaoEncontradaException e ) {			
			throw new NegocioException(e.getMessage() , e);
		}		
	}
	
	@DeleteMapping("/{clienteId}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long dadosBancariosId) {
		clienteService.remover(dadosBancariosId);		
	}
	
}