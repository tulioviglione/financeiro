package com.financeiro.api.util;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.financeiro.api.util.ConstantesUtil;
import com.financeiro.api.utils.PasswordUtils;

public class PasswordUtilsTest {

	private static final String SENHA = ConstantesUtil.Usuario.SENHA_VALIDA;
	private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

	@Test
	public void testSenhaNula() throws Exception {
		assertNull(PasswordUtils.gerarBCrypt(null));
	}

	@Test
	public void testGerarHashSenha() throws Exception {
		String hash = PasswordUtils.gerarBCrypt(SENHA);

		assertTrue(bCryptEncoder.matches(SENHA, hash));
	}

}
