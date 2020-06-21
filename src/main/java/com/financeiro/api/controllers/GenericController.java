package com.financeiro.api.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.financeiro.api.response.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = {
	    @ApiResponse(code = 400, message = "Verifique as informações enviadas"),
	    @ApiResponse(code = 400, message = "Obrigatorio o usuário estar logado"),
	    @ApiResponse(code = 500, message = "Erro inesperado. Exceção não tratada"),
	    @ApiResponse(code = 201, message = "Novo registro adicionado")
	})
public abstract class GenericController<K> {

	private static final Logger log = LoggerFactory.getLogger(GenericController.class);

	protected ResponseEntity<Response<K>> postResponse(Response<K> response) {
		return ResponseEntity.ok(response);
	}
	
	protected ResponseEntity<Response<K>> badRequestResponse(Response<K> response) {
		return ResponseEntity.badRequest().body(response);
	}
	
	protected ResponseEntity<Response<K>> internalServerError(Response<K> response, Exception e) {
		log.error(e.getMessage(), e);
		response.getErrors().add(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@PostMapping
	@ApiOperation(value = "Salva novo registro", produces = "application/JSON")
	public ResponseEntity<Response<K>> save(@Valid @RequestBody K object, BindingResult result){
		log.debug("Salva novo registro {}", object.getClass().getName());
		Response<K> response = new Response<>();
		if (result.hasErrors()) {
			log.error("Erro Validação DTO: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		} else return ResponseEntity.ok().build();
	}

}
