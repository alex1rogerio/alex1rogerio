package br.com.pagseguro.desafiocontabancaria.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.pagseguro.desafiocontabancaria.domain.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoFinanceiraClienteModel {

	private Long id;	
	private LocalDate data;
	private String tipo;	
	private BigDecimal valor;
	private Cliente cliente;
	
}
