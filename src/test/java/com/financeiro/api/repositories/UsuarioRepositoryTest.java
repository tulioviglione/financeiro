package com.financeiro.api.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.financeiro.api.enteties.Usuario;
import com.financeiro.api.enums.PerfilEnum;
import com.financeiro.api.enums.SituacaoUsuarioEnum;
import com.financeiro.api.util.ConstantesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeEach
	public void setUp() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setEmail(ConstantesUtil.Usuario.EMAIL_VALIDO);
		usuario.setLogin(ConstantesUtil.Usuario.LOGIN);
		usuario.setSituacao(SituacaoUsuarioEnum.ATIVO);
		usuario.setPerfil(PerfilEnum.ADMIN);
		usuario.setSenha(ConstantesUtil.Usuario.SENHA_VALIDA);
		this.usuarioRepository.save(usuario);
	}

	@AfterEach
	public final void tearDown() {
		this.usuarioRepository.deleteAll();
	}

	@Test
	void testFindByEmail() {
		assertTrue(usuarioRepository.findByEmail(ConstantesUtil.Usuario.EMAIL_VALIDO).isPresent());
	}

	@Test
	void testFindByNickName() {
		assertTrue(usuarioRepository.findByLogin(ConstantesUtil.Usuario.LOGIN).isPresent());
	}

}
