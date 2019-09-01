package com.financeiro.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.financeiro.api.enteties.Usuario;

public interface UsuarioService {

	/**
	 * verifica se ja possui usuário cadastrado com e-mail informado
	 * 
	 * @param email
	 * @return
	 */
	boolean isEmailExist(String email);

	/**
	 * verifica se existe usuario cadastrado com o login informado
	 * 
	 * @param login
	 * @return
	 */
	boolean isLoginExist(String login);

	/**
	 * Filtra usuario por e-mail
	 * 
	 * @param email
	 * @return
	 */
	Optional<Usuario> findByEmail(String email);

	/**
	 * Filtra usuario por e-mail
	 * 
	 * @param login
	 * @return
	 */
	Optional<Usuario> findByLogin(String login);

	/**
	 * Salva novo Usuario
	 * 
	 * @param usuario
	 * @return
	 */
	Usuario cadastraNovoUsuario(Usuario usuario);

	/**
	 * Persiste Usuario
	 * 
	 * @param usuario
	 * @return
	 */
	Usuario persisteUsuario(Usuario usuario);

	/**
	 * Retorna todos os usuarios cadastrados
	 * 
	 * @param pageRequest
	 * @return
	 */
	Page<Usuario> buscaTodosUsuarios(PageRequest pageRequest);

}