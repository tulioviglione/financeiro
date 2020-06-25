package com.financeiro.api.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.api.dtos.CaixaDTO;
import com.financeiro.api.exceptions.BusinessException;
import com.financeiro.api.response.Response;
import com.financeiro.api.services.CaixaService;
import com.financeiro.api.utils.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Metodos para acesso as funcionalidades dos caixas")
@RequestMapping("/api/caixas")
@RestController
public class CaixaController extends GenericController<CaixaDTO> {

	private static final Logger log = LoggerFactory.getLogger(CaixaController.class);
	
	@Autowired
	private CaixaService caixaService;

	@Override
	public ResponseEntity<Response<CaixaDTO>> save(@Valid @RequestBody CaixaDTO caixaDto, BindingResult result) {

		ResponseEntity<Response<CaixaDTO>> retorno = super.save(caixaDto, result);

		if (retorno.getStatusCodeValue() == 200) {
			try {
				Response<CaixaDTO> response = new Response<>();
				caixaDto.setUsuario(UserUtil.getUsuarioDto());
				response.setData(this.caixaService.cadastrarCaixa(caixaDto));
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			} catch (RuntimeException e) {
				return internalServerError(e);
			}
		}
		return retorno;
	}

	@Override
	public ResponseEntity<Response<CaixaDTO>> update(@Valid @RequestBody CaixaDTO caixaDto, BindingResult result) {

		ResponseEntity<Response<CaixaDTO>> retorno = super.update(caixaDto, result);

		if (retorno.getStatusCodeValue() == 200) {
			try {
				Response<CaixaDTO> response = new Response<>();
				caixaDto.setUsuario(UserUtil.getUsuarioDto());
				response.setData(this.caixaService.alterarCaixa(caixaDto));
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} catch (BusinessException e) {
				return badRequestResponse(e);
			} catch (RuntimeException e) {
				return internalServerError(e);
			}
		}
		return retorno;
	}
	
	@PutMapping(value = "/habilitaCaixa")
	@ApiOperation(value = "Habilita Caixa", produces = "application/JSON")
	public ResponseEntity<Response<String>> habilitarCaixa(@RequestParam("idCaixa") Long idCaixa) {
		Response<String> response = new Response<>();
		try {
			response.setData(this.caixaService.habilitarCaixa(idCaixa, UserUtil.getCodeUser()));
			return ResponseEntity.ok().body(response);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			response.setErrors(Arrays.asList(e.getMessage()));
			return ResponseEntity.badRequest().body(response);
		} catch (RuntimeException e) {
			log.error(e.getMessage(), e);
			response.setErrors(Arrays.asList(e.getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PutMapping(value = "/desabilitaCaixa")
	@ApiOperation(value = "Desabilita Caixa", produces = "application/JSON")
	public ResponseEntity<Response<String>> desabilitarCaixa(@RequestParam("idCaixa") Long idCaixa) {
		Response<String> response = new Response<>();
		try {
			response.setData(this.caixaService.desabilitarCaixa(idCaixa, UserUtil.getCodeUser()));
			return ResponseEntity.ok().body(response);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			response.setErrors(Arrays.asList(e.getMessage()));
			return ResponseEntity.badRequest().body(response);
		} catch (RuntimeException e) {
			log.error(e.getMessage(), e);
			response.setErrors(Arrays.asList(e.getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
