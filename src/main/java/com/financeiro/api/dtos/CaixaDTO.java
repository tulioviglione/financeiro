package com.financeiro.api.dtos;

import java.io.Serializable;

import com.financeiro.api.enteties.Caixa;
import com.financeiro.api.enums.AtivoInativoEnum;
import com.financeiro.api.enums.TipoCaixaEnum;

public class CaixaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1105579878689368058L;
	
	private Long id;
	private String nome;
	private String descricao;
	private AtivoInativoEnum situacao;
	private TipoCaixaEnum tipoCaixa;
	private Long idUsuario;
	private UsuarioDTO usuario;

	public CaixaDTO() {
		// construtor padrão
	}
	
	public CaixaDTO(Caixa caixa) {
		super();
		this.id = caixa.getId();
		this.nome = caixa.getNome();
		this.descricao = caixa.getDescricao();
		this.situacao = caixa.getSituacao();
		this.tipoCaixa = caixa.getTipoCaixa();
		this.setIdUsuario(caixa.getIdUsuario());
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

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
