package com.financeiro.api.util;

public class ConstantesUtil {

	public final class Url {
		public static final String CADASTRA_USUARIO = "/api/usuarios";
		public static final String AUTENTICACAO = "/auth/";
		public static final String VALIDA_EXISTENCIA_EMAIL = "/api/usuarios/validaEmail/";
		public static final String VALIDA_EXISTENCIA_LOGIN = "/api/usuarios/validaUsuario/";
		public static final String CAIXA = "/api/caixas";
		public static final String CAIXA_ATIVO = "/api/caixas/buscaAtivos";
		public static final String CAIXA_HABILITAR = "/api/caixas/habilitaCaixa";
		public static final String CAIXA_DESABILITAR = "/api/caixas/desabilitaCaixa";

	}

	public final class Usuario {
		public static final String SENHA_VALIDA = "12345678";
		public static final String SENHA_MENOR = "1234567";
		public static final String SENHA_MAIOR = "12345678901234567";
		public static final String LOGIN = "login";
		public static final String LOGIN_AUX = "loginAux";
		public static final String EMAIL_VALIDO = "teste@email.com";
		public static final String EMAIL_INVALIDO = "teste.com";
	}
}
