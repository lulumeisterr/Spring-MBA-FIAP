package br.com.fiap.fiapstock.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.fiap.fiapstock.model.Stock;

/**
 * 
 * Classe Pojo para realizar transferencia dados.
 * 
 * 
 * UltraBaixaLatencia , Varia entre vc usar um Wrapper ou um primitivo\
 * int ocupa muita informacao na memoria mais do que o wrapper
 */
public class StockDTO {

	private Long id;
	private String nome;
	private String descricao;
	private Boolean ativo;
	private BigDecimal valor;
	private Date datacriacao;
	
	public StockDTO(Stock stock) {
		super();
		this.id = 0L;
		this.nome = stock.getNome();
		this.descricao = stock.getDescricao();
		this.ativo = stock.getAtivo();
		this.valor = stock.getValor();
		this.datacriacao = stock.getDataCriacao();
	}

	public StockDTO() {
	
	}

	public StockDTO(Long id, String nome, String descricao, Boolean ativo, BigDecimal valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.valor = valor;
	}

	public StockDTO(long l, String nome2, String descricao2, BigDecimal valor2, boolean b) {
		this.id = l;
		this.nome = nome2;
		this.descricao = descricao2;
		this.ativo = b;
		this.valor = valor2;
	}


	public Date getDatacriacao() {
		return datacriacao;
	}
	public void setDatacriacao(Date datacriacao) {
		this.datacriacao = datacriacao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	




}
