package com.financeiro.api.repositories;

import java.util.List;
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
	List<Caixa> findByUsuario(Usuario usuario);

	/**
	 * busca caixas por status e usuario
	 * 
	 * @param usuario
	 * @param situacao
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Caixa> findByUsuarioAndSituacao(Usuario usuario, AtivoInativoEnum situacao);

	/**
	 * busca caixas por status e id do usuario
	 * 
	 * @param idUsuario
	 * @param situacao
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Caixa> findByIdUsuarioAndSituacao(Long idUsuario, AtivoInativoEnum situacao);

	/**
	 * busca caixas por status e id do usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Caixa> findByIdUsuario(Long idUsuario);

	/**
	 * busca caixa por id do caixa e id do usuário
	 * 
	 * @param id
	 * @param idUsuario
	 * @return
	 */
	@Transactional(readOnly = true)
	Optional<Caixa> findByIdAndIdUsuario(Long id, Long idUsuario);

}