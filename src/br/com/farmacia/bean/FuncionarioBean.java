package br.com.farmacia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.dao.FuncionarioHibernateDAO;
import br.com.farmacia.model.Funcionario;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name="MBFuncionario")
@ViewScoped
public class FuncionarioBean {
	
	private ArrayList<Funcionario> itens;
	private ArrayList<Funcionario> itensFiltrados;
	private Funcionario funcionarios = new Funcionario();
	
	private FuncionarioHibernateDAO funcionarioDAO = new FuncionarioHibernateDAO();
	
	@PostConstruct
	public void preparaPesquisa() {
		try {
			
			List<Funcionario> lista = funcionarioDAO.listar();
			itens = new ArrayList<Funcionario>(lista);
			
		}catch(Exception e) {
			
			JSFUtil.adicionarMensagemErro("", "ERRO: Ocorreu um erro desconhecido");
			e.printStackTrace();
			
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
