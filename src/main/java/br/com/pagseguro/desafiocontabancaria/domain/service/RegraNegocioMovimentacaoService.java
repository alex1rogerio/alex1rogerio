package br.com.pagseguro.desafiocontabancaria.domain.service;

import java.math.BigDecimal;

public interface RegraNegocioMovimentacaoService {
	
	public BigDecimal aplicarTaxaSaqueNegocio(BigDecimal valorSaqueInput , Boolean clienteExclusive);
	public BigDecimal aplicarDeposito(BigDecimal valorSaqueInput);

}
