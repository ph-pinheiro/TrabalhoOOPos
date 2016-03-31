package br.phpinheiro.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.phpinheiro.model.Cliente;
import br.phpinheiro.model.Dependente;

/**
 * Implementação do DAO para acessar a tabela Clientes.
 * 
 * @author phpinheiro
 * 
 */
public class DependenteDAO extends BaseDAOImpl<Dependente, Long> {

	/**
	 * Instancia o DAO responsável pelas operações com objetos do Tipo Cliente
	 * 
	 * @param manager
	 *            Este EntityManager é injetado pelo VRaptor via VRaptor-JPA
	 *            plugin
	 * @see https://github.com/caelum/vraptor-jpa
	 */
	public DependenteDAO(EntityManager manager) {
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
	public List<Dependente> filter(String term) throws SQLException {
		String searchFilter = " FROM " + this.oClass.getSimpleName()
				+ " ent WHERE ent.nome like :term";

		Query query = this.em.createQuery(searchFilter);
		query.setParameter("term", "%" + term + "%");

		return query.getResultList();
	}
}
