package com.financeiro.api.services;

import java.util.List;

import com.financeiro.api.dtos.CaixaDTO;
import com.financeiro.api.exceptions.BusinessException;

public interface CaixaService {

	/**
	 * Cadastra novo caixa
	 * 
	 * @param dto
	 * @param idUsuario
	 */
	CaixaDTO cadastrarCaixa(CaixaDTO dto, Long idUsuario);

	/**
	 * Altera registro caixa
	 * 
	 * @param dto
	 * @param idUsuario
	 * @return
	 * @throws BusinessException
	 */
	CaixaDTO alterarCaixa(CaixaDTO dto, Long idUsuario) throws BusinessException;

	/**
	 * habilita o caixa
	 * 
	 * @param idCaixa
	 * @param idUsuario
	 * @throws BusinessException
	 */
	void habilitarCaixa(Long idCaixa, Long idUsuario) throws BusinessException;

	/**
	 * desabilita o caixa
	 * 
	 * @param idCaixa
	 * @param idUsuario
	 * @throws BusinessException
	 */
	void desabilitarCaixa(Long idCaixa, Long idUsuario) throws BusinessException;

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