package br.phpinheiro.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.phpinheiro.model.Cliente;

/**
 * Implementação do DAO para acessar a tabela Clientes.
 * 
 * @author phpinheiro
 * 
 */
public class ClienteDAO extends BaseDAOImpl<Cliente, Long> {

	/**
	 * Instancia o DAO responsável pelas operações com objetos do Tipo Cliente
	 * 
	 * @param manager
	 *            Este EntityManager é injetado pelo VRaptor via VRaptor-JPA
	 *            plugin
	 * @see https://github.com/caelum/vraptor-jpa
	 */
	public ClienteDAO(EntityManager manager) {
		super(manager);
	}

	/**
	 * Filtra os clientes por um dado termo. Verifica se o termo esta presente
	 * em pelo menos um dos campos presentes na listagem.
	 * 
	 * @param term
	 *            Termo a ser pesquisado
	 */
	@Override
	public List<Cliente> filter(String term) throws SQLException {
		String searchFilter = " FROM " + this.oClass.getSimpleName()
				+ " ent WHERE ent.nome like :term OR ent.cpf like :term "
				+ "OR ent.credito like :term OR ent.rua like :term "
				+ "OR ent.cep like :term OR ent.cidade like :term";

		Query query = this.em.createQuery(searchFilter);
		query.setParameter("term", "%" + term + "%");

		return query.getResultList();
	}
}
