package br.com.pagseguro.desafiocontabancaria.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MovimentacaoFinanceiraCliente {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;	
	private LocalDate data;	
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;	
	
	private BigDecimal valor;
	
	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;	

}
