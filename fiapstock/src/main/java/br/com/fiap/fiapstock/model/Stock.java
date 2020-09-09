package br.com.fiap.fiapstock.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.fiapstock.dto.StockCreateUpdateDTO;

/**
 * Habilitar o EntityListener para realizar notificacoes de alteracoes
 * @author lucasrodriguesdonascimento
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_STOCK")
public class Stock {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private BigDecimal valor;
	
	@Column
	private Boolean ativo;
	
	@Column(name = "data_criacao" , nullable = false , updatable = false)
	@CreatedDate
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao")
	@LastModifiedDate
	private Date dataAtualizacao;
	
	public Stock() {
		
	}
	
	public Stock(StockCreateUpdateDTO stock) {
		super();
		this.id = 0L;
		this.nome = stock.getNome();
		this.descricao = stock.getDescricao();
		this.valor = stock.getValor();
		this.ativo = stock.getAtivo();
	}
	
	public Stock(Long id, String nome, String descricao, BigDecimal valor, Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.ativo = ativo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
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
