package br.com.pagseguro.desafiocontabancaria.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Id
	private Long id;
	
	@Column(nullable = false, length = 50, name="nome")
	@NotNull
	private String nome;
	
	@Column(nullable = false , name="plano_exclusive")
	private Boolean planoExclusive;
	
	@NotNull
	@Column(nullable = false , name = "saldo")
	private BigDecimal saldo;
	
	@NotNull	
	@Column(nullable = false , name = "numero_conta")
	private String numeroConta;
	
	@NotNull
	@Column(nullable = false , name = "data_nascimento")
	private LocalDate dataNascimento;
	
}
