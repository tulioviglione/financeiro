package com.financeiro.api.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
public class CaixaRepositoryTest {

	@Autowired
	private CaixaRepository caixaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario user;
	

	@Before
	public void setUp() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setEmail(ConstantesUtil.Usuario.EMAIL_VALIDO);
		usuario.setLogin(ConstantesUtil.Usuario.LOGIN);
		usuario.setSituacao(SituacaoUsuarioEnum.ATIVO);
		usuario.setPerfil(PerfilEnum.ADMIN);
		usuario.setSenha(ConstantesUtil.Usuario.SENHA_VALIDA);
		Caixa caixa = new Caixa();
		this.user = this.usuarioRepository.save(usuario);
		caixa.setUsuario(this.user);
		caixa.setTipoCaixa(TipoCaixaEnum.BANCO);
		caixa.setNome("caixaNome");
		caixa.setDescricao("caixaDescricao");
		caixa.setSituacao(AtivoInativoEnum.ATIVO);
		this.caixaRepository.save(caixa);
	}

	@After
	public final void tearDown() {
		this.caixaRepository.deleteAll();
		this.usuarioRepository.deleteAll();
	}

	@Test
	public void testFindByUsuario() {
		assertFalse(caixaRepository.findByUsuario(this.user).isEmpty());
	}

	@Test
	public void testFindByUsuarioAndSituacao() {
		assertFalse(caixaRepository.findByUsuarioAndSituacao(this.user, AtivoInativoEnum.ATIVO).isEmpty());
		assertTrue(caixaRepository.findByUsuarioAndSituacao(this.user, AtivoInativoEnum.INATIVO).isEmpty());		
	}
	
	@Test
	public void testFindByIdUsuario() {
		assertFalse(caixaRepository.findByIdUsuario(this.user.getId()).isEmpty());
		assertTrue(caixaRepository.findByIdUsuario(10L).isEmpty());
	}

	@Test
	public void testFindByIdUsuarioAndSituacao() {
		assertFalse(caixaRepository.findByIdUsuarioAndSituacao(this.user.getId(), AtivoInativoEnum.ATIVO).isEmpty());
		assertTrue(caixaRepository.findByIdUsuarioAndSituacao(this.user.getId(), AtivoInativoEnum.INATIVO).isEmpty());
	}

}
