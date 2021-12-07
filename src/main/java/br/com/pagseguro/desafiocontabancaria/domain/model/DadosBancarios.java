package br.com.pagseguro.desafiocontabancaria.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DadosBancarios {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String numeroConta;

	@Column(nullable = false)
	private String agencia;
	
	private Boolean chequeEspecialLiberado;
	
	private BigDecimal saldo;
	
	private BigDecimal chequeEspecial;
	
	private BigDecimal taxa;
	
}
