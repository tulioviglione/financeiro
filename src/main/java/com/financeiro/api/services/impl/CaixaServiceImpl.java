package com.financeiro.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financeiro.api.dtos.CaixaDTO;
import com.financeiro.api.enteties.Caixa;
import com.financeiro.api.enums.AtivoInativoEnum;
import com.financeiro.api.exceptions.BusinessException;
import com.financeiro.api.repositories.CaixaRepository;
import com.financeiro.api.services.CaixaService;

@Service
public class CaixaServiceImpl implements CaixaService {

	private static final Logger log = LoggerFactory.getLogger(CaixaServiceImpl.class);

	@Autowired
	private CaixaRepository caixaRepository;
	
	@Override
	public CaixaDTO cadastrarCaixa(CaixaDTO dto) {
		log.debug("Usuario {}. Novo caixa cadastrado {}", dto.getUsuario().getId(), dto.getNome());
		Caixa caixa = new Caixa(dto);
		caixa.setSituacao(AtivoInativoEnum.ATIVO);
		return new CaixaDTO(this.caixaRepository.save(caixa));
	}

	@Override
	public CaixaDTO alterarCaixa(CaixaDTO dto) throws BusinessException {
		log.debug("Usuario {}. Alteração caixa {}", dto.getUsuario().getId(), dto.getNome());
		Caixa caixa = new Caixa(dto);
		if (caixa.getId() != null)
			return new CaixaDTO(this.caixaRepository.save(caixa));
		else throw new BusinessException("O caixa deve ser salvo antes de realizar uma alteração");
	}

	@Override
	public void habilitarCaixa(Long idCaixa) throws BusinessException {
		log.debug("Habilita caixa id {}", idCaixa);
		Caixa caixa  = findCaixaById(idCaixa);
		if(!caixa.getSituacao().equals(AtivoInativoEnum.ATIVO)) {
			caixa.setSituacao(AtivoInativoEnum.ATIVO);
			this.caixaRepository.save(caixa);
		} else {
			throw new BusinessException("Caixa encontra-se ativo");
		}
	}

	@Override
	public void desabilitarCaixa(Long idCaixa) throws BusinessException {
		log.debug("Desabilita caixa id {}", idCaixa);
		Caixa caixa  = findCaixaById(idCaixa);
		if(!caixa.getSituacao().equals(AtivoInativoEnum.INATIVO)) {
			caixa.setSituacao(AtivoInativoEnum.INATIVO);
			this.caixaRepository.save(caixa);
		} else {
			throw new BusinessException("Caixa encontra-se inativo");
		}
	}

	private Caixa findCaixaById(Long idCaixa) throws BusinessException {
		return this.caixaRepository.findById(idCaixa).orElseThrow(() -> new BusinessException("Nenhum caixa retornado para o ID informado"));
	}

	@Override
	public List<CaixaDTO> findCaixaByIdUsuario(Long idUsuario) {
		return this.caixaRepository.findByIdUsuario(idUsuario).stream().map(CaixaDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<CaixaDTO> findActiveCaixaByIdUsuario(Long idUsuario) {
		return this.caixaRepository.findByIdUsuarioAndSituacao(idUsuario, AtivoInativoEnum.ATIVO).stream().map(CaixaDTO::new).collect(Collectors.toList());
	}

}