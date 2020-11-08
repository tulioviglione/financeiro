package com.financeiro.api.resources;

import java.util.Arrays;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.financeiro.api.exceptions.BusinessException;
import com.financeiro.api.response.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 400, message = "Verifique as informações enviadas"),
		@ApiResponse(code = 401, message = "Obrigatorio o usuário estar logado"),
		@ApiResponse(code = 500, message = "Erro inesperado. Exceção não tratada"),
		@ApiResponse(code = 201, message = "Novo registro adicionado"),
		@ApiResponse(code = 204, message = "Registro atualizado") })
public abstract class GenericResource<K> {

	private static final Logger log = LoggerFactory.getLogger(GenericResource.class);

	@PostMapping
	@ApiOperation(value = "Salva novo registro", produces = "application/JSON")
	public ResponseEntity<Response<K>> save(@Valid @RequestBody K object, BindingResult result) {
		log.debug("Salva novo registro {}", object.getClass().getName());
		return (result.hasErrors()) ? trataErroDTO(result) : ResponseEntity.ok().build();
	}

	@PutMapping
	@ApiOperation(value = "Atualiza registro", produces = "application/JSON")
	public ResponseEntity<Response<K>> update(@Valid @RequestBody K object, BindingResult result) {
		log.debug("Atualiza registro {}", object.getClass().getName());
		return (result.hasErrors()) ? trataErroDTO(result) : ResponseEntity.ok().build();
	}

	private ResponseEntity<Response<K>> trataErroDTO(BindingResult result) {
		log.error("Erro Validação DTO: {}", result.getAllErrors());
		Response<K> response = new Response<>();
		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
		return this.badRequestResponse(response);
	}

	protected ResponseEntity<Response<K>> getResponse(Response<K> response) {
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("unchecked")
	protected ResponseEntity<Response<K>> badRequestResponse(Object obj) {
		Response<K> response = new Response<>();
		if (obj instanceof BusinessException) {
			response.setErrors(Arrays.asList(((BusinessException) obj).getMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData((K) obj);
		return ResponseEntity.badRequest().body(response);
	}

	protected ResponseEntity<Response<K>> internalServerError(RuntimeException e) {
		Response<K> response = new Response<>();
		log.error(e.getMessage(), e);
		response.getErrors().add(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
