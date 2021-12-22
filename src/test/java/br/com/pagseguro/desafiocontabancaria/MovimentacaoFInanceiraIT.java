package br.com.pagseguro.desafiocontabancaria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.pagseguro.desafiocontabancaria.domain.service.RegraNegocioMovimentacaoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovimentacaoFInanceiraIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private RegraNegocioMovimentacaoService regraNegocioSaqueService; 
	

	
	@Before
	public void setUp() {
 	}
	
	@Test
	public void calculandoSaqueClientesComCemReais() {		
		BigDecimal aplicarTaxaSaqueNegocio = regraNegocioSaqueService.aplicarTaxaSaqueNegocio(new BigDecimal(100), true);
		assertEquals(aplicarTaxaSaqueNegocio, new BigDecimal(100));		
	}	
	

	@Test
	public void validandoSaqueClientesComPlanoExclusivo() {		
		BigDecimal aplicarTaxaSaqueNegocio = regraNegocioSaqueService.aplicarTaxaSaqueNegocio(new BigDecimal(200), true);
		assertEquals(aplicarTaxaSaqueNegocio, new BigDecimal(200));		
	}		
	
	@Test
	public void validandoSaqueClientesComPlanoExclusivo_Erro() {		
		BigDecimal aplicarTaxaSaqueNegocio = regraNegocioSaqueService.aplicarTaxaSaqueNegocio(new BigDecimal(200), false);
		assertEquals(aplicarTaxaSaqueNegocio, new BigDecimal(200));		
	}
	
	@Test
	public void validandoSaqueClientesComDuzentosReaisEPlanoExclusivo_false() {		
		BigDecimal aplicarTaxaSaqueNegocio = regraNegocioSaqueService.aplicarTaxaSaqueNegocio(new BigDecimal(200), false);
		assertNotEquals(aplicarTaxaSaqueNegocio, new BigDecimal(200));		
	}	
	
	
	
	

}
