package com.financeiro.api.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.financeiro.api.enteties.Usuario;
import com.financeiro.api.exceptions.BusinessException;
import com.financeiro.api.repositories.UsuarioRepository;
import com.financeiro.api.util.ConstantesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class UsuarioServiceTest {

	@MockBean
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@BeforeEach
	public void setUp() throws Exception {
		BDDMockito.given(this.usuarioRepository.save(Mockito.any(Usuario.class))).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.findByEmail(Mockito.anyString()))
				.willReturn(Optional.of(new Usuario()));
		BDDMockito.given(this.usuarioRepository.findByLogin(Mockito.anyString()))
				.willReturn(Optional.of(new Usuario()));
		BDDMockito.given(this.usuarioRepository.findAll(Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Usuario>(new ArrayList<Usuario>()));
	}

	@Test
	void buscaUsuario() {
		Assertions.assertTrue(this.usuarioService.findByEmail(ConstantesUtil.Usuario.EMAIL_VALIDO).isPresent());
		Assertions.assertTrue(this.usuarioService.findByLogin(ConstantesUtil.Usuario.LOGIN).isPresent());
		Assertions.assertTrue(this.usuarioService.isLoginExist(ConstantesUtil.Usuario.LOGIN));
		Assertions.assertTrue(this.usuarioService.isEmailExist(ConstantesUtil.Usuario.EMAIL_VALIDO));
	}

	@Test
	void TestCadastroNovoUsuario() throws ParseException, BusinessException {
		Usuario usuario = new Usuario();
		usuario.setEmail(ConstantesUtil.Usuario.EMAIL_VALIDO);
		usuario.setLogin(ConstantesUtil.Usuario.LOGIN);
		usuario.setSenha(ConstantesUtil.Usuario.SENHA_VALIDA);
		usuario = this.usuarioService.cadastraNovoUsuario(usuario);
		Assertions.assertNotNull(usuario);
		BDDMockito.given(this.usuarioRepository.save(Mockito.any(Usuario.class)))
				.willThrow(DataIntegrityViolationException.class);
		Assertions.assertThrows(BusinessException.class, () -> this.usuarioService.cadastraNovoUsuario(new Usuario()));
	}

	@Test
	void TestPersistencia() {
		Usuario usuario = this.usuarioService.persisteUsuario(new Usuario());
		Assertions.assertNotNull(usuario);
	}

	@Test
	void TestBuscaUsuariosCadastrados() {
		Page<Usuario> usuario = this.usuarioService.buscaTodosUsuarios(PageRequest.of(0, 10));
		Assertions.assertNotNull(usuario);
	}

}