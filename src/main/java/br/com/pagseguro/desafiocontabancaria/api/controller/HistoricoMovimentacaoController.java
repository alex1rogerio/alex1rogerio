package br.com.pagseguro.desafiocontabancaria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pagseguro.desafiocontabancaria.domain.filter.HistoricoMovimentacao;
import br.com.pagseguro.desafiocontabancaria.domain.model.MovimentacaoFinanceiraCliente;
import br.com.pagseguro.desafiocontabancaria.domain.service.MovimentacaoFinanceiraClienteService;

@RestController
@RequestMapping(path = "/historicoMovimentacao")
public class HistoricoMovimentacaoController {
	
	@Autowired
	private MovimentacaoFinanceiraClienteService movimentacaoFinanceiraClienteService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovimentacaoFinanceiraCliente> historicoMovimentacao(HistoricoMovimentacao filtro) {
		return movimentacaoFinanceiraClienteService.historico(filtro);		
	}
	
	
	

}
