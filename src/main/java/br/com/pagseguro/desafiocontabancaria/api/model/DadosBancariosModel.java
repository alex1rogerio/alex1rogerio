package br.com.pagseguro.desafiocontabancaria.api.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DadosBancariosModel {

	private Long id;
	private String nome;
	private String numeroConta;
	private String agencia;	
	private Boolean chequeEspecialLiberado;	
	private BigDecimal saldo;	
	private BigDecimal chequeEspecial;	
	private BigDecimal taxa;	

}
