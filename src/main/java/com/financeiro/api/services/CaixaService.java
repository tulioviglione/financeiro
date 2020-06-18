package com.financeiro.api.services;

import java.util.List;

import com.financeiro.api.dtos.CaixaDTO;
import com.financeiro.api.exceptions.BusinessException;

public interface CaixaService {

	/**
	 * Cadastra novo caixa
	 * 
	 * @param dto
	 */
	CaixaDTO cadastrarCaixa(CaixaDTO dto);

	/**
	 * Altera registro caixa
	 * 
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	CaixaDTO alterarCaixa(CaixaDTO dto) throws BusinessException;

	/**
	 * habilita o caixa
	 * 
	 * @param idCaixa
	 * @throws BusinessException
	 */
	void habilitarCaixa(Long idCaixa) throws BusinessException;

	/**
	 * desabilita o caixa
	 * 
	 * @param idCaixa
	 * @throws BusinessException
	 */
	void desabilitarCaixa(Long idCaixa) throws BusinessException;

	/**
	 * lista os caixa pelo ID do Usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	List<CaixaDTO> findCaixaByIdUsuario(Long idUsuario);

	/**
	 * lista os caixas ativos pelo ID do Usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	List<CaixaDTO> findActiveCaixaByIdUsuario(Long idUsuario);
}