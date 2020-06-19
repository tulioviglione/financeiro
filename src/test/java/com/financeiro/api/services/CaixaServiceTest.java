package com.financeiro.api.services;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.financeiro.api.dtos.CaixaDTO;
import com.financeiro.api.enteties.Caixa;
import com.financeiro.api.enteties.Usuario;
import com.financeiro.api.enums.AtivoInativoEnum;
import com.financeiro.api.enums.PerfilEnum;
import com.financeiro.api.enums.SituacaoUsuarioEnum;
import com.financeiro.api.enums.TipoCaixaEnum;
import com.financeiro.api.repositories.CaixaRepository;
import com.financeiro.api.repositories.UsuarioRepository;
import com.financeiro.api.util.ConstantesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CaixaServiceTest {

	@MockBean
	private CaixaRepository caixaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CaixaService caixaService;
	
	private Usuario usuario;
	
	@Before
	public void setUp() throws Exception {
		this.usuario = new Usuario();
		this.usuario.setEmail(ConstantesUtil.Usuario.EMAIL_VALIDO);
		this.usuario.setLogin(ConstantesUtil.Usuario.LOGIN);
		this.usuario.setSenha(ConstantesUtil.Usuario.SENHA_VALIDA);
		this.usuario.setPerfil(PerfilEnum.ADMIN);
		this.usuario.setSituacao(SituacaoUsuarioEnum.ATIVO);
		this.usuario = this.usuarioRepository.save(this.usuario);
//		this.caixaRepository.save(caixaInativo);
//		BDDMockito.given(this.caixaRepository.save(Mockito.any(Caixa.class))).willReturn(new Caixa());
//		BDDMockito.given(this.usuarioRepository.findByEmail(Mockito.anyString())).willReturn(Optional.of(new Caixa()));
//		BDDMockito.given(this.usuarioRepository.findByLogin(Mockito.anyString())).willReturn(Optional.of(new Caixa()));
//		BDDMockito.given(this.usuarioRepository.findAll(Mockito.any(PageRequest.class)))
//				.willReturn(new PageImpl<Caixa>(new ArrayList<Caixa>()));
	}
	
	@After
	public final void tearDown() {
		this.usuarioRepository.deleteAll();
	}
	
	@Test
	public void cadastrarCaixaTest() {
		Caixa caixa = new Caixa();
		caixa.setUsuario(this.usuario);
		caixa.setNome("testeNome");
		caixa.setDescricao("testeDescricao");
		caixa.setTipoCaixa(TipoCaixaEnum.BANCO);
		assertEquals(this.caixaService.cadastrarCaixa(new CaixaDTO(caixa)).getSituacao(), AtivoInativoEnum.ATIVO);
	}
	

//	@Test
//	public void buscaUsuario() {
//		assertTrue(this.usuarioService.findByEmail(ConstantesUtil.Usuario.EMAIL_VALIDO).isPresent());
//		assertTrue(this.usuarioService.findByLogin(ConstantesUtil.Usuario.LOGIN).isPresent());
//		assertTrue(this.usuarioService.isLoginExist(ConstantesUtil.Usuario.LOGIN));
//		assertTrue(this.usuarioService.isEmailExist(ConstantesUtil.Usuario.EMAIL_VALIDO));
//	}
//
//	@Test
//	public void TestCadastroNovoUsuario() throws ParseException, BusinessException {
//		Usuario usuario = new Usuario();
//		usuario.setEmail(ConstantesUtil.Usuario.EMAIL_VALIDO);
//		usuario.setLogin(ConstantesUtil.Usuario.LOGIN);
//		usuario.setSenha(ConstantesUtil.Usuario.SENHA_VALIDA);
//		usuario = this.usuarioService.cadastraNovoUsuario(usuario);
//		assertNotNull(usuario);
//	}
//
//	@Test
//	public void TestPersistencia() {
//		Usuario usuario = this.usuarioService.persisteUsuario(new Usuario());
//
//		assertNotNull(usuario);
//	}
//
//	@Test
//	public void TestBuscaUsuariosCadastrados() {
//		Page<Usuario> usuario = this.usuarioService.buscaTodosUsuarios(PageRequest.of(0, 10));
//
//		assertNotNull(usuario);
//	}
//
//	@Test(expected = BusinessException.class)
//	public void TestExceptionCadastroNovoUsuario() throws BusinessException {
//		BDDMockito.given(this.usuarioRepository.save(Mockito.any(Usuario.class))).willThrow(DataIntegrityViolationException.class);
//		this.usuarioService.cadastraNovoUsuario(new Usuario());
//	}
	
}