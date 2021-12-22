package br.com.pagseguro.desafiocontabancaria.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pagseguro.desafiocontabancaria.domain.exception.ClienteNaoEncontradaException;
import br.com.pagseguro.desafiocontabancaria.domain.exception.ClienteSaldoNaoEncontradaException;
import br.com.pagseguro.desafiocontabancaria.domain.filter.HistoricoMovimentacao;
import br.com.pagseguro.desafiocontabancaria.domain.model.Cliente;
import br.com.pagseguro.desafiocontabancaria.domain.model.MovimentacaoFinanceiraCliente;
import br.com.pagseguro.desafiocontabancaria.domain.model.TipoMovimentacao;
import br.com.pagseguro.desafiocontabancaria.domain.repository.ClienteRepository;
import br.com.pagseguro.desafiocontabancaria.domain.repository.HistoricoMovimentacaoFInanceira;
import br.com.pagseguro.desafiocontabancaria.domain.repository.MovimentacaoFinanceiraClienteRepository;

@Service 
public class MovimentacaoFinanceiraClienteService {
	
	@Autowired
	private RegraNegocioMovimentacaoService regraNegocioSaqueService; 
	
	@Autowired
	private MovimentacaoFinanceiraClienteRepository movimentacaoFInanceiraClienteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HistoricoMovimentacaoFInanceira historicoMovimentacaoFInanceira;
	
	
	public List<MovimentacaoFinanceiraCliente> listar(Long clienteId){		
		return movimentacaoFInanceiraClienteRepository.findByCliente(clienteId);		
	}
	
	public List<MovimentacaoFinanceiraCliente> historico(HistoricoMovimentacao filtro){	
		return historicoMovimentacaoFInanceira.historicoPorDatas(filtro);		
	}
	
	
	@Transactional
	public MovimentacaoFinanceiraCliente salvar(MovimentacaoFinanceiraCliente movimentacaoFinanceiraCliente , Long clienteId) {
		BigDecimal valorAplicarTaxaNegocio = null;
		
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow( () -> new ClienteNaoEncontradaException(clienteId));
		
		if (movimentacaoFinanceiraCliente.getTipo().equals(TipoMovimentacao.S)) {
		
			valorAplicarTaxaNegocio = regraNegocioSaqueService.aplicarTaxaSaqueNegocio(
					movimentacaoFinanceiraCliente.getValor(), cliente.getPlanoExclusive());
			
			if (cliente.getSaldo().compareTo(valorAplicarTaxaNegocio) == -1) {
				throw  new ClienteSaldoNaoEncontradaException(clienteId);
			}			
			cliente.setSaldo(cliente.getSaldo().subtract(valorAplicarTaxaNegocio));
			
		}else if (movimentacaoFinanceiraCliente.getTipo().equals(TipoMovimentacao.D)) {
			
			valorAplicarTaxaNegocio = 
					regraNegocioSaqueService.aplicarDeposito(movimentacaoFinanceiraCliente.getValor());
			cliente.setSaldo(cliente.getSaldo().add(valorAplicarTaxaNegocio));
			
		}	
		
		movimentacaoFinanceiraCliente.setCliente(cliente);		
		return movimentacaoFInanceiraClienteRepository.save(movimentacaoFinanceiraCliente);		
	}
	
	public void remover(Long id) {
		try {
			movimentacaoFInanceiraClienteRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradaException(id) ; 			
		}
	}		
	
}
