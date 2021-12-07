package br.com.pagseguro.desafiocontabancaria;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroDadosBancariosIT {
	
	@LocalServerPort
	private int port;	
	
	private String jsonCadastroBancarioCorreto;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/dadosBancarios";
		
		jsonCadastroBancarioCorreto = ResourceUtils.getContentFromResource(
				"/dados-bancarios-correto.json");
 	}
	
	@Test
	public void deveRetornarStatus200_QuandoExistirDadosBancarios() {	
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(200);		
	}	
	
	@Test
	public void deveRetornarStatus201_CadastroBancarioComSucesso() {	
		given()
			.body(jsonCadastroBancarioCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());	
	}		

}
