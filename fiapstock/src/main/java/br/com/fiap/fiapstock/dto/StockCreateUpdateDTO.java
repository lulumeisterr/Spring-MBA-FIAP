package br.com.fiap.fiapstock.dto;

import java.math.BigDecimal;

public class StockCreateUpdateDTO {
	

	private String nome;
	private String descricao;
	private BigDecimal valor;
	private Boolean ativo;
	
	public StockCreateUpdateDTO() {
		super();
	}

	public StockCreateUpdateDTO(String nome, String descricao, BigDecimal valor, Boolean ativo) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.ativo = ativo;
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
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	

}
