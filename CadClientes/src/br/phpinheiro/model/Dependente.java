package br.phpinheiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * Classe Dependente anotada para ser utilizada com JPA.
 * 
 * @author phpinheiro
 * 
 */

@Entity
@Table(name = "dependentes")
public class Dependente {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "parente_fk", referencedColumnName = "id")
	private Cliente parente;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getParente() {
		return parente;
	}

	public void setParente(Cliente parente) {
		this.parente = parente;
	}
	
	

}
