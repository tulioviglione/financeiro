package com.financeiro.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping
	@ApiOperation(value = "Busca caixas ativos", produces = "application/JSON")
	public ResponseEntity<Response<List<CaixaDTO>>> findActiveCaixa() {
		Response<List<CaixaDTO>> response = new Response<>();
		try {
			response.setData(this.caixaService.findActiveCaixaByIdUsuario(UserUtil.getCodeUser()));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
