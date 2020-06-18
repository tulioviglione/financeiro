package com.financeiro.api.enteties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Classe com campos genericos para as entidades
 * Possui métodos que adiciona e atualiza datas de alteração dos registros
 *
 * @author Tulio Viglione
 *
 */
@MappedSuperclass
public class Generics {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "DAT_INS", nullable = false)
	private Date dataCriacao;

	@Column(name = "DAT_ALT", nullable = false)
	private Date dataAtualizacao;

	/**
	 * seta campo automaticamente no update
	 */
	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
	
	/**
	 * seta campo automaticamente ao salvar
	 */
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

    public Long getId() {
    	return id;
    }
    
    public Date getDataCriacao() {
    	return dataCriacao;
    }
    
    public Date getDataAtualizacao() {
    	return dataAtualizacao;
    }

}
