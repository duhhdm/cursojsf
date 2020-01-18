package br.com.farmacia.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.farmacia.dao.FuncionarioHibernateDAO;
import br.com.farmacia.model.Funcionario;
import br.com.farmacia.util.JSFUtil;
import br.com.farmacia.util.SessionUtil;

@ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean {

	private ArrayList<Funcionario> itens;
	private ArrayList<Funcionario> itensFiltrados;
	private Funcionario funcionarios = new Funcionario();
	
	private String usuario = null;
	private FuncionarioHibernateDAO funcionarioDAO = new FuncionarioHibernateDAO();

	@PostConstruct
	public void preparaPesquisa() {
		if(SessionUtil.getParam("Logado")!=null)
			usuario =SessionUtil.getParam("Logado").toString();
		if (usuario!=null && !usuario.equals("")) {
			try {
				System.out.println(SessionUtil.getParam("Logado"));
				List<Funcionario> lista = funcionarioDAO.listar();
				itens = new ArrayList<Funcionario>(lista);
			} catch (Exception e) {
				JSFUtil.adicionarMensagemErro("", "ERRO: Ocorreu um erro desconhecido");
				e.printStackTrace();
			}
		}else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
				JSFUtil.adicionarMensagemErro("", "ERRO: Ocorreu um erro na sessão");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Funcionario> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Funcionario> itens) {
		this.itens = itens;
	}

	public ArrayList<Funcionario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Funcionario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Funcionario getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Funcionario funcionarios) {
		this.funcionarios = funcionarios;
	}

}
