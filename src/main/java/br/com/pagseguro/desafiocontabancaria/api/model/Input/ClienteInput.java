package br.com.pagseguro.desafiocontabancaria.api.model.Input;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ClienteInput {

	private String nome;	
	private Boolean planoExclusive;	
	private BigDecimal saldo;
	private String numeroConta;
	private LocalDate dataNascimento;	

}
