package br.com.pagseguro.desafiocontabancaria.domain.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HistoricoMovimentacao {
		
	private Long clienteId;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate dataInicial;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate dataFinal;
	
	

}
