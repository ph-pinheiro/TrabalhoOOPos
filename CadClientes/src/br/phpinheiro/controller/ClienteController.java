package br.phpinheiro.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.phpinheiro.facade.ClienteDAOFacade;
import br.phpinheiro.facade.DependenteDAOFacade;
import br.phpinheiro.model.Cliente;
import br.phpinheiro.model.Dependente;

/**
 * Controle do VRaptor, métodos públicos são expostos para que possam ser acessados via URL, 
 * usando o padrão URL/controller/metodo. Pode ser utilizado também o padrão denominado no 
 * \@Path. A responsabilidade de instanciar e injetar as dependências fica por conta do 
 * VRaptor.
 * 
 * @author phpinheiro
 * 
 */

@Resource
public class ClienteController {

	private Result result;
	private ClienteDAOFacade clienteFacade;
	private DependenteDAOFacade dependenteFacade;

	public ClienteController(Result result, ClienteDAOFacade facadeCliente, DependenteDAOFacade facadeDependente) {
		this.result = result;
		this.clienteFacade = facadeCliente;
		this.dependenteFacade = facadeDependente;
	}

	@Path("/")
	public void index() {
	}

	@Path("/adicionar-cliente")
	public void adicionarCliente(Cliente cliente, Dependente dependente1, Dependente dependente2) {
		
			if(this.verificaClientePorCPF(cliente)){
				this.result.include("erro_cliente","Cliente com este CPF já está cadastrado");
				result.include("cliente", cliente);
				this.result.redirectTo(ClienteController.class).form();
				return;
			} else{
				if(this.verificaCreditoMinimo(cliente)){
					try {
						clienteFacade.adicionarCliente(this.salvarDataAtual(cliente));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else{
					this.result.include("erro_cliente","Cliente com crédito inferior ao limite");
					result.include("cliente", cliente);
					this.result.redirectTo(ClienteController.class).form();
					return;
				}
			}

		this.result.include("msg", "Cliente Adicionado");
		this.result.redirectTo(ClienteController.class).list();
	}

	@Path("/list")
	public List<Cliente> list() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = clienteFacade.listTodosClientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}

	@Path("/editar")
	public Cliente editForm(long id) {
		Cliente cliente = new Cliente();
		try {
			cliente = clienteFacade.lerPeloId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Path("/salvar-cliente")
	public void salvarCliente(Cliente cliente, Dependente dependente0, Dependente dependente1) {
		try {
			//objeto temporário para manter a data de cadastro
			Cliente clienteTemp = clienteFacade.lerPeloId(cliente.getId());
			cliente.setDataCadastro(clienteTemp.getDataCadastro());
			if(this.verificaCreditoMinimo(cliente)){
				clienteFacade.atualizarCliente(cliente);
			} else{
				this.result.include("erroMsg", "Crédito do Cliente inferior ao limite");
				this.result.redirectTo(this).editForm(cliente.getId());
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.result.include("msg", "Cliente Atualizado com sucesso");
		this.result.redirectTo(this).list();
	}

	@Path("/excluir")
	public void excluir(Long id) {

		try {
			clienteFacade.excluirCliente(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.result.include("msg", "Cliente Excluido com sucesso");
		this.result.redirectTo(this).list();
	}

	@Path("/form")
	public void form() {
		result.include("titulo", "Adicionar Cliente");
	}

	@Path("/buscar")
	public void buscarClientes(String search) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = clienteFacade.pesquisarCliente(search);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		result.include("clienteList", clientes);
		result.use(Results.page()).forwardTo("WEB-INF/jsp/cliente/list.jsp");
	}
	
	@Path("/listDeps")
	public Cliente listDeps(Long id) {
		Cliente cliente = new Cliente();
		try {
			cliente = clienteFacade.lerPeloId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	@Path("/excluirDeps")
	public void excluirDeps(Long idDependente, Long idCliente) {

		try {
			dependenteFacade.excluirDependente(idDependente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.result.include("msg", "Dependente Excluido com sucesso");
		this.result.redirectTo(this).listDeps(idCliente);
	}
	
	@Path("/editDep")
	public Dependente editDep(Long idDependente) {

		Dependente dependente = new Dependente();
		try {
			dependente = dependenteFacade.lerPeloId(idDependente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dependente;
	}
	
	@Path("/salvar-dependente")
	public void salvarDependente(Dependente dependente, Cliente cliente) {
		
		if(dependente.getNome().isEmpty()){
			this.result.include("Erromsg", "ERRO: O Dependente precisa ter o nome preenchido");
			this.result.redirectTo(this).editDep(dependente.getId());
		} else{
			try {
				dependente.setParente(cliente);
				dependenteFacade.atualizarDependente(dependente);
				
				this.result.include("msg", "Dependente Atualizado com sucesso");
				this.result.redirectTo(this).listDeps(cliente.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	
	private Cliente salvarDataAtual(Cliente cliente){
	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");  
		Date date = new Date();  
		cliente.setDataCadastro(dateFormat.format(date));
		
		return cliente;
	}
	
	private boolean verificaClientePorCPF(Cliente cliente){
		try {
			List<Cliente> existente = clienteFacade.pesquisarCliente(cliente.getCpf());
			if (existente.size() == 0) {
				return false;
			} else{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	private boolean verificaCreditoMinimo(Cliente cliente){
		
		if(cliente.getCredito() >= cliente.getLimitCredito()){
			return true;
		} else{
			return false;
		}
	}
}
