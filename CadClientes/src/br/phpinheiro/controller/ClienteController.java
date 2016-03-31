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
						cliente = this.verificaDependentes(cliente, dependente1, dependente2);
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
				cliente = verificaDependentes(cliente, dependente0, dependente1);
				excluirDependentes(cliente, dependente0, dependente1);
				clienteFacade.atualizarCliente(cliente);
			} else{
				this.result.include("erroMsg", "Crédito do Cliente inferior ao limite");
				this.result.redirectTo(this).editForm(cliente.getId());
				return;
			}
			clienteFacade.atualizarCliente(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.result.include("msg", "Cliente Atualizado com sucesso");
		this.result.redirectTo(this).list();
	}

	private void excluirDependentes(Cliente cliente, Dependente dependente0, Dependente dependente1) {
		
		if (dependente0.getNome().isEmpty()){
			if(dependente0.getId() > 1){
				try {
					dependenteFacade.excluirDependente(dependente0.getId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (dependente1.getNome().isEmpty()){
			if(dependente1.getId() > 1){
				try {
					dependenteFacade.excluirDependente(dependente1.getId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
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
	
	@Path("/outra-action")
	public void outraAction() {
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
	
	private Cliente verificaDependentes(Cliente cliente, Dependente dependente1, 
										Dependente dependente2){
			
		List<Dependente> dependentes = new ArrayList<Dependente>();
		
		
		if(dependente1.getNome() != ""){
			dependente1.setParente(cliente);
			dependentes.add(dependente1);
		}
		
		if(dependente2.getNome() != ""){
			dependente2.setParente(cliente);
			dependentes.add(dependente2);
		}
		
		if(dependentes.size() != 0){
			
			cliente.setDependentes(dependentes);
		}
		
		return cliente;
		
	}
}
