package br.com.pagseguro.desafiocontabancaria.api.model.Input;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoFinanceiraClienteInput {
	
	@NotNull
	private LocalDate data;
	@NotNull
	private String tipo;
	@NotNull
	private BigDecimal valor;
}
