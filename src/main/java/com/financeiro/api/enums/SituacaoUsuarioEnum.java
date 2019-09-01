package com.financeiro.api.enums;

public enum SituacaoUsuarioEnum {

	ATIVO("Ativo"), INATIVO("Inativo"), BLOQUEADO("Bloqueado");

	private final String descricao;

	private SituacaoUsuarioEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}