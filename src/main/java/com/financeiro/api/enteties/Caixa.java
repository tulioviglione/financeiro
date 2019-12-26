package com.financeiro.api.enteties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.financeiro.api.enums.AtivoInativoEnum;
import com.financeiro.api.enums.TipoCaixaEnum;

/**
 * 
 * Entidade Caixa
 *
 * @author Tulio Viglione
 */
@Entity
@Table(name = "caixa")
public class Caixa extends Generics implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NOME", length = 50, nullable = false)
	private String nome;

	@Column(name = "DESCRICAO", length = 200)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "SITUACAO", length = 7, nullable = false)
	private AtivoInativoEnum situacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_CAIXA", length = 13, nullable = false)
	private TipoCaixaEnum tipoCaixa;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUsuario", nullable = false)
	private Usuario usuario;
	
	public Caixa() {
		// construtor padrão
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

	public AtivoInativoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(AtivoInativoEnum situacao) {
		this.situacao = situacao;
	}

	public TipoCaixaEnum getTipoCaixa() {
		return tipoCaixa;
	}

	public void setTipoCaixa(TipoCaixaEnum tipoCaixa) {
		this.tipoCaixa = tipoCaixa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}