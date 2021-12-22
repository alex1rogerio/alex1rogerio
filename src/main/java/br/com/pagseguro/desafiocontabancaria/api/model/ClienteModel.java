package br.com.pagseguro.desafiocontabancaria.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {

	private Long id;
	private String nome;
	private Boolean planoExclusive;
	private BigDecimal saldo;
	private String numeroConta;
	private LocalDate dataNascimento;
}
