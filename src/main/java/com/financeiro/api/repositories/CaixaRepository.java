package com.financeiro.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.financeiro.api.enteties.Caixa;
import com.financeiro.api.enteties.Usuario;
import com.financeiro.api.enums.AtivoInativoEnum;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {

	/**
	 * busca caixa por usuario
	 * 
	 * @param usuario
	 * @return
	 */
	@Transactional(readOnly = true)
	Optional<Caixa> findByUsuario(Usuario usuario);
	
	/**
	 * busca caixas por status e usuario
	 * 
	 * @param usuario
	 * @param situacao
	 * @return
	 */
	@Transactional(readOnly = true)
	Optional<Caixa> findByUsuarioAndSituacao(Usuario usuario, AtivoInativoEnum situacao);
}