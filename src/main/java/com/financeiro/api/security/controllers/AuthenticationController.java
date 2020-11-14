package com.financeiro.api.security.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.api.response.Response;
import com.financeiro.api.security.dto.JwtAuthenticationDTO;
import com.financeiro.api.security.dto.TokenDTO;
import com.financeiro.api.security.utils.JwtTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Metodos para recuperar token")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Gera e retorna um novo token JWT.
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 */
	@PostMapping("/authentic")
	@ApiOperation(value = "Gera token para acesso a API", response = TokenDTO.class, produces = "application/JSON")
	public ResponseEntity<Response<TokenDTO>> gerarTokenJwt(@Valid @RequestBody JwtAuthenticationDTO authenticationDto,
			BindingResult result) {
		Response<TokenDTO> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Erro validando lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		try {
			log.debug("Gerando token para o email {}.", authenticationDto.getEmail());
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationDto.getEmail(), authenticationDto.getSenha()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
			String token = jwtTokenUtil.obterToken(userDetails);
			response.setData(new TokenDTO(token));

			return ResponseEntity.ok(response);
		} catch (BadCredentialsException e) {
			log.debug(e.getMessage());
			response.getErrors().add("Usuário ou senha inválido");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.getErrors().add(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/**
	 * Gera um novo token com uma nova data de expiração.
	 * 
	 * @param request
	 * @return ResponseEntity<Response<TokenDto>>
	 */
	@PutMapping(value = "/refresh")
	@ApiOperation(value = "Atualiza token para acesso a API", response = TokenDTO.class, produces = "application/JSON")
	public ResponseEntity<Response<TokenDTO>> gerarRefreshTokenJwt(HttpServletRequest request) {
		log.debug("Gerando refresh token JWT.");
		Response<TokenDTO> response = new Response<>();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
		try {
			if (token.isPresent()) {
				if (token.get().startsWith(BEARER_PREFIX)) {
					token = Optional.of(token.get().substring(7));
				}
				if (!jwtTokenUtil.tokenValido(token.get())) {
					response.getErrors().add("Token inválido ou expirado.");
					return ResponseEntity.badRequest().body(response);
				} else {
					String refreshedToken = jwtTokenUtil.refreshToken(token.get());
					response.setData(new TokenDTO(refreshedToken));
					return ResponseEntity.ok(response);
				}
			} else {
				response.getErrors().add("Token não informado.");
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.getErrors().add(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
