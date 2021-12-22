package br.com.pagseguro.desafiocontabancaria.domain.business;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.pagseguro.desafiocontabancaria.domain.service.RegraNegocioMovimentacaoService;

@Service
public class TaxaNegocioImpl implements RegraNegocioMovimentacaoService {

	private BigDecimal valorSaque = new BigDecimal(0);

	@Override
	public BigDecimal aplicarTaxaSaqueNegocio(BigDecimal valorSaqueInput , Boolean clienteExclusive) {
		
		
		if (valorSaque.compareTo(new BigDecimal(100.00)) < 1 || clienteExclusive ) {
			
			this.valorSaque = valorSaqueInput;
			
		}else if(valorSaqueInput.compareTo(new BigDecimal(100.00)) == 1 || valorSaqueInput.compareTo(new BigDecimal(300.00)) < 1 ) {
			
			this.valorSaque = valorSaqueInput.add((valorSaqueInput.multiply(new BigDecimal(0.004))));
			
		}else if (valorSaqueInput.compareTo(new BigDecimal(300.00)) == 1) {
			
			this.valorSaque = valorSaqueInput.add((valorSaqueInput.multiply(new BigDecimal(0.01))));
			
		}
		return this.valorSaque;	

	}

	@Override
	public BigDecimal aplicarDeposito(BigDecimal valorSaqueInput) {
		return valorSaqueInput;
	}

}
