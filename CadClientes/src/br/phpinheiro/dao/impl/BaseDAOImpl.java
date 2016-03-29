package br.phpinheiro.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.phpinheiro.dao.BaseDAO;

/**
 * DAO genérico para as operações básicas e comuns entre todos
 * os DAOs.
 * 
 * 
 * @author phpinheiro
 * 
 * @param <E>
 *            Classe usada para gerar as operações básicas
 * @param <ID>
 *            Tipo do id utilizado pelas Classes
 */

public class BaseDAOImpl<E, ID extends Serializable> implements BaseDAO<E, ID> {

	protected EntityManager em;
	protected Class<E> oClass;

	/**
	 * Instancia e inicializa o novo BaseDAO.
	 * @param manager Entity Manager utilizado para as operações com os dados via JPA 
	 */
	public BaseDAOImpl(EntityManager manager) {
		this.em = manager;
		this.oClass = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public E create(E entity) throws SQLException {
		em.persist(entity);
		return entity;
	}

	public List<E> all() throws SQLException {
		String queryS = " FROM " + oClass.getSimpleName();
		Query query = em.createQuery(queryS);
		return query.getResultList();
	}

	public E readById(ID id) throws SQLException {
		return em.find(oClass, id);
	}

	public List<E> readByCriteria(Map<String, Object> criteria)
			throws SQLException {
		return null;
	}

	public E update(E entity) throws SQLException {
		return em.merge(entity);
	}

	public boolean remove(E entity) throws SQLException {
		em.remove(entity);
		return true;
	}

	public List<E> filter(String term) throws SQLException {
		return null;
	}
}
