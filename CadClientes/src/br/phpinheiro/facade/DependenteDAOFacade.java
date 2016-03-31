package br.phpinheiro.facade;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import br.phpinheiro.dao.impl.ClienteDAO;
import br.phpinheiro.dao.impl.DependenteDAO;
import br.phpinheiro.model.Cliente;
import br.phpinheiro.model.Dependente;

/**
 * Cliente facade simplifica o acesso aos dados dos Clientes. \@Component
 * registra este facade como componente do VRaptor para que o container possa
 * injeta-lo via injeção de dependências.
 * 
 * @author phpinheiro
 * 
 */
@Component
public class DependenteDAOFacade {

	private DependenteDAO dao;

	/**
	 * 
	 * @param em
	 *            EntityManager injetado
	 */
	public DependenteDAOFacade(EntityManager em) {
		dao = new DependenteDAO(em);
	}

	public void adicionarDependente(Dependente dependente) throws SQLException {
		dao.create(dependente);
	}

	public List<Dependente> listTodosDependentes() throws SQLException {
		return dao.all();
	}

	public void atualizarDependente(Dependente dependente) throws SQLException {
		dao.update(dependente);
	}

	public void excluirDependente(long id) throws SQLException {
		dao.remove(this.lerPeloId(id));
	}

	public Dependente lerPeloId(long id) throws SQLException {
		return dao.readById(id);
	}

	public List<Dependente> pesquisarDependente(String termo) throws SQLException {
		return dao.filter(termo);
	}
}
