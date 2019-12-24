package com.financeiro.api.enums;

public enum SituacaoEnum {

	ATIVO("Ativo"), INATIVO("Inativo"), BLOQUEADO("Bloqueado");

	private final String descricao;

	private SituacaoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}