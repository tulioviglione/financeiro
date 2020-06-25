package com.financeiro.api.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.financeiro.api.enteties.Caixa;
import com.financeiro.api.enteties.Usuario;
import com.financeiro.api.enums.AtivoInativoEnum;
import com.financeiro.api.enums.PerfilEnum;
import com.financeiro.api.enums.SituacaoUsuarioEnum;
import com.financeiro.api.enums.TipoCaixaEnum;
import com.financeiro.api.util.ConstantesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class CaixaRepositoryTest {

	@Autowired
	private CaixaRepository caixaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Usuario user;

	private Caixa caixa;

	@BeforeEach
	public void setUp() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setEmail(ConstantesUtil.Usuario.EMAIL_VALIDO);
		usuario.setLogin(ConstantesUtil.Usuario.LOGIN);
		usuario.setSituacao(SituacaoUsuarioEnum.ATIVO);
		usuario.setPerfil(PerfilEnum.ADMIN);
		usuario.setSenha(ConstantesUtil.Usuario.SENHA_VALIDA);
		this.caixa = new Caixa();
		this.user = this.usuarioRepository.save(usuario);
		this.caixa.setUsuario(this.user);
		this.caixa.setTipoCaixa(TipoCaixaEnum.BANCO);
		this.caixa.setNome("caixaNome");
		this.caixa.setDescricao("caixaDescricao");
		this.caixa.setSituacao(AtivoInativoEnum.ATIVO);
		this.caixa = this.caixaRepository.save(caixa);
	}

	@AfterEach
	public final void tearDown() {
		this.caixaRepository.deleteAll();
		this.usuarioRepository.deleteAll();
	}

	@Test
	void testFindByUsuario() {
		assertFalse(caixaRepository.findByUsuario(this.user).isEmpty());
	}

	@Test
	void testFindByUsuarioAndSituacao() {
		assertFalse(caixaRepository.findByUsuarioAndSituacao(this.user, AtivoInativoEnum.ATIVO).isEmpty());
		assertTrue(caixaRepository.findByUsuarioAndSituacao(this.user, AtivoInativoEnum.INATIVO).isEmpty());
	}

	@Test
	void testFindByIdUsuario() {
		assertFalse(caixaRepository.findByIdUsuario(this.user.getId()).isEmpty());
		assertTrue(caixaRepository.findByIdUsuario(10L).isEmpty());
	}

	@Test
	void testFindByIdUsuarioAndSituacao() {
		assertFalse(caixaRepository.findByIdUsuarioAndSituacao(this.user.getId(), AtivoInativoEnum.ATIVO).isEmpty());
		assertTrue(caixaRepository.findByIdUsuarioAndSituacao(this.user.getId(), AtivoInativoEnum.INATIVO).isEmpty());
	}

	@Test
	void testFindByIdAndIdUsuario() {
		assertTrue(caixaRepository.findByIdAndIdUsuario(this.caixa.getId(), this.user.getId()).isPresent());
		assertFalse(caixaRepository
				.findByIdAndIdUsuario(Math.subtractExact(100L, this.caixa.getId()), this.user.getId()).isPresent());
	}

}
