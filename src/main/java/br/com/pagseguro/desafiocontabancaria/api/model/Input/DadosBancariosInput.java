package br.com.pagseguro.desafiocontabancaria.api.model.Input;

import java.math.BigDecimal;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class DadosBancariosInput {
	
	private String nome;	
	private String numeroConta;
	private String agencia;	
	private Boolean chequeEspecialLiberado;	
	private BigDecimal saldo;	
	private BigDecimal chequeEspecial;	
	private BigDecimal taxa;
	

}
