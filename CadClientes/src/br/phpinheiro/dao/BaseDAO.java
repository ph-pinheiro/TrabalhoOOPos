package br.phpinheiro.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BaseDAO<E, ID extends Serializable> {

	public E create(E entity) throws SQLException;

	public List<E> all() throws SQLException;

	public E readById(ID id) throws SQLException;

	public List<E> readByCriteria(Map<String, Object> criteria)
			throws SQLException;

	public E update(E entity) throws SQLException;

	public boolean remove(E entity) throws SQLException;

	public List<E> filter(String term) throws SQLException;

}
