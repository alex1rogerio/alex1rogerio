package br.com.pagseguro.desafiocontabancaria.api.exceptionhandler;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import br.com.pagseguro.desafiocontabancaria.api.core.validation.ValidacaoException;
import br.com.pagseguro.desafiocontabancaria.domain.exception.EntidadeEmUsoException;
import br.com.pagseguro.desafiocontabancaria.domain.exception.EntidadeNaoEncontradaException;
import br.com.pagseguro.desafiocontabancaria.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
			
	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL="Ocorreu um erro interno inesperado no sistema. "
			+ "Tente novamente e se o problema persistir, entre em contato com o administrador so sistema!";
	
	
	@Autowired
	private MessageSource messageSource;
	
	
	@ExceptionHandler({ ValidacaoException.class })
	public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
	    return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), 
	            HttpStatus.BAD_REQUEST, request);
	} 	
	

	private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		        
		    ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		    
		    List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
		            .map(objectError -> {
		                String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
		                
		                String name = objectError.getObjectName();
		                
		                if (objectError instanceof FieldError) {
		                    name = ((FieldError) objectError).getField();
		                }
		                
		                return Problem.Object.builder()
		                    .name(name)
		                    .userMessage(message)
		                    .build();
		            })
		            .collect(Collectors.toList());
		    
		    Problem problem = createProblemBuilder(status, problemType, detail)
		        .userMessage(detail)
		        .objects(problemObjects)
		        .build();
		    
		    return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleExceptionError(Exception ex , WebRequest request){		
		var status = HttpStatus.INTERNAL_SERVER_ERROR;
		var problemType = ProblemType.ERRO_DE_SISTEMA;
		var detail = MSG_ERRO_GENERICA_USUARIO_FINAL;
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(detail)
				.timestamp(OffsetDateTime.now())
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);	
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		

		var problemType = ProblemType.ERRO_DE_SISTEMA;
		var detail = MSG_ERRO_GENERICA_USUARIO_FINAL;
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(detail)
				.timestamp(OffsetDateTime.now())
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);	
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request){
		
		var status = HttpStatus.NOT_FOUND;
		var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		var detail = ex.getMessage();	
				
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(detail)
				.timestamp(OffsetDateTime.now())
				.build(); 
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}	

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		
		var status = HttpStatus.CONFLICT;
		var problemType = ProblemType.ENTIDADE_EM_USO;
		var detail = ex.getMessage();		
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(detail)
				.timestamp(OffsetDateTime.now())
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);	
	}	
	
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex , WebRequest request){		
		var status = HttpStatus.BAD_REQUEST;
		var problemType = ProblemType.ERRO_NEGOCIO;
		var detail = ex.getMessage();	
				
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(detail)
				.timestamp(OffsetDateTime.now())
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);	
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
	    if (ex instanceof MethodArgumentTypeMismatchException) {
	        return handleMethodArgumentTypeMismatch(
	                (MethodArgumentTypeMismatchException) ex, headers, status, request);
	    }			    		
	    return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var problemType = ProblemType.PARAMETRO_INVALIDO;
		
	    String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
	            + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
	            ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

	    Problem problem = createProblemBuilder(status, problemType, detail)
	    		.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.timestamp(OffsetDateTime.now())	    		
	    		.build();
	    return handleExceptionInternal(ex, problem, headers, status, request);		

	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request );
		}
		if(rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, headers, status, request );
		}
		if(rootCause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request );
		}			
		var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		var detail = "O corpo da requisição esta inválido. Verifique o erro da sintaxe";						
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(detail)
				.timestamp(OffsetDateTime.now())
				.build();				
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);	
	}
	
	private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));				
		
		var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;		
		var detail = String.format("A propriedade '%s' não pode ser atualizada  '%s',"
		+ "esta propiedade não existe. Informe um valor compatível com o tipo! ",path,  ex.getClass());							
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(detail)
				.timestamp(OffsetDateTime.now())				
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);		
		
	}

	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));				
		
		var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;		
		var detail = String.format("A propriedade '%s' não pode ser atualizada  '%s',"
		+ "que é de um tipo inválido. Informe um valor compatível com o tipo! ",path,  ex.getClass());							
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(detail)
				.timestamp(OffsetDateTime.now())
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
	    if (ex instanceof NoHandlerFoundException) {	        
	    	return noHandlerFoundException(
	                (NoHandlerFoundException) ex, headers, status, request);
	    }
		return super.handleNoHandlerFoundException(ex, headers, status, request);
	}	

	private ResponseEntity<Object> noHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;		
		var detail = String.format("O recurso'%s' que você tentou acessar é inexistente!" ,ex.getRequestURL()  );							
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(detail)
				.timestamp(OffsetDateTime.now())
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);	
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));				
		
		var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;		
		var detail = String.format("A propriedade '%s' recebeu o valor '%s',"
				+ "que é de um tipo inválido. Informe um valor compatível com o tipo '%s' ",path, ex.getValue(),ex.getTargetType().getSimpleName());							
		var problem = createProblemBuilder(status , problemType , detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.timestamp(OffsetDateTime.now())
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = Problem.builder()					
					.title((status.getReasonPhrase()))
					.status(status.value())
					.build();					
		}else if (body instanceof String) {
			body = Problem.builder()					
					.title(body.toString())
					.status(status.value())					
					.build();
		}
	
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder  createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}
	

}
