package br.com.pagseguro.desafiocontabancaria.api.model;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosBancariosPesquisaNumeroContaModel {

	
	private String nome;
	@JsonIgnore
	private String numeroConta;
	@JsonIgnore
	private String agencia;	
	@JsonIgnore
	private Boolean chequeEspecialLiberado_;	
	@JsonIgnore
	private BigDecimal saldo_;	
	@JsonIgnore
	private BigDecimal chequeEspecial;	
	@JsonIgnore
	private BigDecimal taxa;		
	
	private String agenciaNumeroConta;
	private String saldoCliente;
	private String chequeEspecialLiberadoCliente;
	private String valorChequeEspecialDiaSeguinte;
	

	public DadosBancariosPesquisaNumeroContaModel(DadosBancariosModel dadosBancariosModel ) {	
		this.nome = dadosBancariosModel.getNome();
		this.numeroConta = dadosBancariosModel.getNumeroConta();
		this.agencia = dadosBancariosModel.getAgencia();
		this.chequeEspecialLiberado_ = dadosBancariosModel.getChequeEspecialLiberado();
		this.saldo_ = dadosBancariosModel.getSaldo();
		this.chequeEspecial = dadosBancariosModel.getChequeEspecial();
		this.taxa = dadosBancariosModel.getTaxa();
		
		this.getAgenciaConta();
		this.getSaldo();
		this.getChequeEspecialLiberado();
		this.valorChequeEspecialDiaSeguinte();
	}
	
	public void getAgenciaConta() {		
		this.agenciaNumeroConta =  this.agencia+"/"+ this.numeroConta.substring(0, this.numeroConta.length()-1)+"-"
				+this.numeroConta.substring(this.numeroConta.length()-1, this.numeroConta.length());
	}
	
	public void getSaldo() {		
		this.saldoCliente = FormataValorLocal(this.saldo_);		
	}
	
	public void getChequeEspecialLiberado() {
		
		if (this.chequeEspecialLiberado_)	{
			this.chequeEspecialLiberadoCliente = StatusChequeEspecial.CHEQUE_ESPECIAL_LIBERADO.getStatus();
		}else {
			this.chequeEspecialLiberadoCliente = StatusChequeEspecial.CHEQUE_ESPECIAL_NAO_LIBERADO.getStatus();
		}		
	}
	
	public void valorChequeEspecialDiaSeguinte() {
		
		BigDecimal valor = this.chequeEspecial.add( (this.chequeEspecial.multiply( this.taxa.divide(new BigDecimal(100)))));		
		this.valorChequeEspecialDiaSeguinte = FormataValorLocal(valor);
	}
	
	private String FormataValorLocal(BigDecimal valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String saldoFormatdo = nf.format (valor);
		return saldoFormatdo;
	}
}