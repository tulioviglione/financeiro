package com.financeiro.api.enums;

public enum PerfilEnum {

	ADMIN("Admin");

	private final String descricao;

	private PerfilEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}