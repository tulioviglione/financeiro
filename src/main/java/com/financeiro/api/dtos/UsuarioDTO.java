package com.financeiro.api.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.financeiro.api.enteties.Usuario;
import com.financeiro.api.enums.SituacaoUsuarioEnum;

public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5099601254240404676L;
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String login;
	private String email;
	private String senha;
	private SituacaoUsuarioEnum situacao;

	public UsuarioDTO() {
		// construtor padrão
	}
	
	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.login = usuario.getLogin();
		this.email = usuario.getEmail();
		this.situacao = usuario.getSituacao();
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@NotNull(message = "{login.nulo}")
	@Size(min = 1, message = "{login.vazio}")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotNull(message = "{email.nulo}")
	@Size(min = 1, message = "{email.vazio}")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull(message = "{senha.nulo}")
	@Size(min = 8, max = 16, message = "{senha.invalida}")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public SituacaoUsuarioEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoUsuarioEnum situacao) {
		this.situacao = situacao;
	}

}
