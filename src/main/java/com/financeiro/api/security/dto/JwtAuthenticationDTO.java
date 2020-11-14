package com.financeiro.api.security.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JwtAuthenticationDTO {

	private String email;
	private String senha;

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

}
