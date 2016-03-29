package br.phpinheiro.facade;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import br.phpinheiro.dao.impl.ClienteDAO;
import br.phpinheiro.model.Cliente;

/**
 * Cliente facade simplifica o acesso aos dados dos Clientes. \@Component
 * registra este facade como componente do VRaptor para que o container possa
 * injeta-lo via injeção de dependências.
 * 
 * @author phpinheiro
 * 
 */
@Component
public class ClienteDAOFacade {

	private ClienteDAO dao;

	/**
	 * 
	 * @param em
	 *            EntityManager injetado
	 */
	public ClienteDAOFacade(EntityManager em) {
		dao = new ClienteDAO(em);
	}

	public void adicionarCliente(Cliente cliente) throws SQLException {
		dao.create(cliente);
	}

	public List<Cliente> listTodosClientes() throws SQLException {
		return dao.all();
	}

	public void atualizarCliente(Cliente cliente) throws SQLException {
		dao.update(cliente);
	}

	public void excluirCliente(long id) throws SQLException {
		dao.remove(this.lerPeloId(id));
	}

	public Cliente lerPeloId(long id) throws SQLException {
		return dao.readById(id);
	}

	public List<Cliente> pesquisarCliente(String termo) throws SQLException {
		return dao.filter(termo);
	}
}
