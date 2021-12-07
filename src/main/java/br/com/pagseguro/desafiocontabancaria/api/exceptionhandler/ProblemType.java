package br.com.pagseguro.desafiocontabancaria.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
		
	DADOS_INVALIDOS("/dados-invalidos","Dados Inválidos"),
	ERRO_DE_SISTEMA("/erro-de-sistema","Erro de Sistema"),
	PARAMETRO_INVALIDO("/parametro-invalido","Parametrô Inválido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem Incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não Encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em Uso"),	
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	private ProblemType(String path , String title) {
		
		this.uri = "https://localhost:8090"+path;
		this.title = title;	
	}

}
