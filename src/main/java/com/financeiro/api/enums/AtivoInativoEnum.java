package com.financeiro.api.enums;

public enum AtivoInativoEnum {

	ATIVO("Ativo"), INATIVO("Inativo");

	private final String descricao;

	private AtivoInativoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}