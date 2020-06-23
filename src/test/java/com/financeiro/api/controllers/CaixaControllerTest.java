package com.financeiro.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.financeiro.api.config.WithMockCustomUser;
import com.financeiro.api.dtos.CaixaDTO;
import com.financeiro.api.enums.AtivoInativoEnum;
import com.financeiro.api.enums.TipoCaixaEnum;
import com.financeiro.api.exceptions.BusinessException;
import com.financeiro.api.services.CaixaService;
import com.financeiro.api.util.ConstantesUtil;
import com.financeiro.api.utils.FunctionUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CaixaControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CaixaService caixaService;

	private CaixaDTO caixaDto;

	@Before
	public void setUp() throws Exception {
		this.caixaDto = new CaixaDTO();
		this.caixaDto.setNome("nomeCaixa");
		this.caixaDto.setDescricao("descricaoCaixa");
		this.caixaDto.setTipoCaixa(TipoCaixaEnum.BANCO);
		this.caixaDto.setSituacao(AtivoInativoEnum.ATIVO);
	}

	@Test
	@WithMockCustomUser
	public void cadastraNovoCaixaTest() throws Exception {
		BDDMockito.given(this.caixaService.cadastrarCaixa(Mockito.any(CaixaDTO.class))).willReturn(this.caixaDto);

		this.caixaDto.setNome(null);
		mvc.perform(
				MockMvcRequestBuilders.post(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

		this.caixaDto.setNome("nomeCaixa");
		this.caixaDto.setTipoCaixa(null);
		mvc.perform(
				MockMvcRequestBuilders.post(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

		this.caixaDto.setTipoCaixa(TipoCaixaEnum.BANCO);
		mvc.perform(
				MockMvcRequestBuilders.post(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		BDDMockito.given(this.caixaService.cadastrarCaixa(Mockito.any(CaixaDTO.class)))
				.willThrow(new RuntimeException());
		mvc.perform(
				MockMvcRequestBuilders.post(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
	}

	@Test
	@WithMockCustomUser
	public void atualizarCaixaTest() throws Exception {
		BDDMockito.given(this.caixaService.alterarCaixa(Mockito.any(CaixaDTO.class))).willReturn(this.caixaDto);

		this.caixaDto.setNome(null);
		mvc.perform(
				MockMvcRequestBuilders.put(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

		this.caixaDto.setNome("nomeCaixa");
		this.caixaDto.setTipoCaixa(null);
		mvc.perform(
				MockMvcRequestBuilders.put(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

		this.caixaDto.setTipoCaixa(TipoCaixaEnum.BANCO);
		mvc.perform(
				MockMvcRequestBuilders.put(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

		BDDMockito.given(this.caixaService.alterarCaixa(Mockito.any(CaixaDTO.class)))
				.willThrow(new BusinessException());
		mvc.perform(
				MockMvcRequestBuilders.put(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

		BDDMockito.given(this.caixaService.alterarCaixa(Mockito.any(CaixaDTO.class))).willThrow(new RuntimeException());
		mvc.perform(
				MockMvcRequestBuilders.put(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
	}
	
	@Test
	public void cadastraNovoCaixaSemUsuarioTest() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void alterarCaixaSemUsuarioTest() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.put(ConstantesUtil.Url.CAIXA).content(FunctionUtil.asJsonString(this.caixaDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void habilitarCaixaTestSemUsuario() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put(ConstantesUtil.Url.CAIXA_HABILITAR + "1"))
				.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void desabilitarCaixaTestSemUsuario() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put(ConstantesUtil.Url.CAIXA_DESABILITAR + "1"))
				.andExpect(status().isUnauthorized());
	}

}