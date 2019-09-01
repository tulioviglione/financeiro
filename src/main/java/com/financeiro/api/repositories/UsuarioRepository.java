package com.financeiro.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.financeiro.api.enteties.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * busca usuario por login
	 * 
	 * @param login
	 * @return
	 */
	@Transactional(readOnly = true)
	Optional<Usuario> findByLogin(String login);

	/**
	 * busca usuario por e-mail
	 * 
	 * @param email
	 * @return
	 */
	@Transactional(readOnly = true)
	Optional<Usuario> findByEmail(String email);

}