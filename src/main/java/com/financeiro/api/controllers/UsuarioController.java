package com.financeiro.api.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.api.dtos.UsuarioDTO;
import com.financeiro.api.enteties.Usuario;
import com.financeiro.api.exceptions.BusinessException;
import com.financeiro.api.response.Response;
import com.financeiro.api.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Response<UsuarioDTO>> adicionar(@Valid @RequestBody UsuarioDTO usuarioDto,
			BindingResult result) {

		log.debug("Cadastrando novo usuário na API");

		Response<UsuarioDTO> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Erro Validação Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			response.setData(new UsuarioDTO(this.usuarioService.cadastraNovoUsuario(new Usuario(usuarioDto))));
		} catch (BusinessException e) {
			log.error("Erro ao cadastrar usuário", e.getCause());
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "/validaEmail/{email}")
	public ResponseEntity<Response<Boolean>> validaEmail(@PathVariable("email") String email) {
		log.debug("Controller verifica e-mail cadastrado: {}", email);
		Response<Boolean> response = new Response<>();
		response.setData(this.usuarioService.isEmailExist(email));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/validaUsuario/{usuario}")
	public ResponseEntity<Response<Boolean>> validaUsuario(@PathVariable("usuario") String usuario) {
		log.debug("Controller verifica usuario cadastrado: {}", usuario);
		Response<Boolean> response = new Response<>();
		response.setData(this.usuarioService.isLoginExist(usuario));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/buscaTodosUsuarios")
	public ResponseEntity<Response<Page<UsuarioDTO>>> buscaTodosUsuarios(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.debug("Retornando lista de usuarios Cadastrados");
		Response<Page<UsuarioDTO>> response = new Response<>();
		PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);

		Page<Usuario> usuarios = this.usuarioService.buscaTodosUsuarios(pageRequest);
		Page<UsuarioDTO> usuariosDto = usuarios.map(usuario -> new UsuarioDTO(usuario));

		response.setData(usuariosDto);
		return ResponseEntity.ok(response);
	}
}
