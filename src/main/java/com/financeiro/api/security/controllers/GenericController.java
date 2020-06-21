package com.financeiro.api.security.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.api.response.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiOperation(value = "Metodos para recuperar token para autenticação")
@ApiResponses(value = {
	    @ApiResponse(code = 400, message = "Verifique as informações enviadas"),
	    @ApiResponse(code = 500, message = "Erro inesperado. Exceção não tratada")
	})
@RestController
public class GenericController<K> {

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

}
