package com.financeiro.api.resources;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.api.dtos.UsuarioDTO;
import com.financeiro.api.enteties.Usuario;
import com.financeiro.api.exceptions.BusinessException;
import com.financeiro.api.response.Response;
import com.financeiro.api.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Metodos para acesso as funcionalidades dos usuários")
@RequestMapping("/api/usuarios")
@RestController
public class UsuarioResource extends GenericResource<UsuarioDTO> {

	private static final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

	@Autowired
	private UsuarioService usuarioService;

	
	@Override
	public ResponseEntity<Response<UsuarioDTO>> save(@Valid @RequestBody UsuarioDTO usuarioDto,
			BindingResult result) {

		log.debug("Cadastrando novo usuário no sistema");

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
	@ApiOperation(value = "Verifica se e-mail já foi utilizado por outro usuário", response = Boolean.class, produces = "application/JSON")
	public ResponseEntity<Response<Boolean>> validaEmail(@PathVariable("email") String email) {
		log.debug("Controller verifica e-mail cadastrado: {}", email);
		Response<Boolean> response = new Response<>();
		response.setData(this.usuarioService.isEmailExist(email));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/validaUsuario/{usuario}")
	@ApiOperation(value = "Verifica usernamen informado ja esta sendo utilizado por outro usuário", response = Boolean.class, produces = "application/JSON")
	public ResponseEntity<Response<Boolean>> validaUsuario(@PathVariable("usuario") String usuario) {
		log.debug("Controller verifica usuario cadastrado: {}", usuario);
		Response<Boolean> response = new Response<>();
		response.setData(this.usuarioService.isLoginExist(usuario));
		return ResponseEntity.ok(response);
	}

}
