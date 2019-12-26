package com.financeiro.api.enums;

public enum TipoCaixaEnum {

	FISCAL("Fiscal"),CONTROLE("Controle"),BANCO("Banco"),INVESTIMENTOS("Investimentos");

	private final String descricao;

	private TipoCaixaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}