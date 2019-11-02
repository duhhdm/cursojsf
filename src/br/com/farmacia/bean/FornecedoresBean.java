package br.com.farmacia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.dao.FornecedorDAO;
import br.com.farmacia.dao.FornecedorHibernateDAO;
import br.com.farmacia.dto.FornecedorDTO;
import br.com.farmacia.dto.enums.Ativo;
import br.com.farmacia.model.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	//variaveis conectam com o view
	private ArrayList<FornecedorDTO> itens;
	private ArrayList<FornecedorDTO> itensFiltrados;
	private Fornecedores fornecedores = new Fornecedores();
	
	//conexao dao
	private FornecedorHibernateDAO forDao = new FornecedorHibernateDAO();

	@PostConstruct
	public void preparaPesquisa() {
		try {
			List<Fornecedores> lista = forDao.listarTeste();
			List<FornecedorDTO> listaAtivos = new ArrayList<FornecedorDTO>();
			for( Fornecedores aux: lista) {
				FornecedorDTO aux1 = new FornecedorDTO();
				aux1.setCodigo(aux.getCodigo());
				aux1.setDescricao(aux.getDescricao());
				if(aux.getAtivo().equals(Ativo.SIM)) {
					listaAtivos.add(aux1);
				}
			}
			itens = new ArrayList<FornecedorDTO>(listaAtivos);
		}catch(Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Ocorreu um erro desconhecido");
			e.printStackTrace();
		}
	}

	public void inserirFornecedor() {
		try {
			fornecedores.setAtivo(Ativo.SIM);
			forDao.salvar(fornecedores);
			preparaPesquisa();
			JSFUtil.adicionarMensagemSucesso("", "Fornecedor: "+fornecedores.getDescricao()+" Inserido com sucesso");
		}catch(Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel inserir dados");
			e.printStackTrace();
		}
	}
	
	public void removerFornecedor(Integer id) {
		try {
			if(!forDao.temProduto(id)) {
				Fornecedores aux = forDao.buscarPorId(id);
				aux.setAtivo(Ativo.NAO);
				forDao.atualizar(aux);
				preparaPesquisa();
				JSFUtil.adicionarMensagemSucesso("", "Fornecedor removido com sucesso");
			}
			else {
				JSFUtil.adicionarMensagemErro("", "ERRO: existem produtos vinculados ao fornecedor");
			}
		}catch(Exception e){
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel remover dados");
			e.printStackTrace();
		}
	}
	
	public void alterarFornecedor(Integer id, String value) {
		try {
			Fornecedores aux = new Fornecedores();
			aux = forDao.buscarPorId(id);
			if(value.equals(aux.getDescricao())) {
				JSFUtil.adicionarMensagemErro("", "ERRO: Não houve alteração dos dados");
			}else {
				aux.setDescricao(value);
				forDao.atualizar(aux);
				JSFUtil.adicionarMensagemSucesso("", "Fornecedor "+value+" alterado com sucesso");
			}
			preparaPesquisa();
		}catch(Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel alterar dados");
			e.printStackTrace();
		}
	}
	
	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ArrayList<FornecedorDTO> getItens() {
		return itens;
	}

	public void setItens(ArrayList<FornecedorDTO> itens) {
		this.itens = itens;
	}

	public ArrayList<FornecedorDTO> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<FornecedorDTO> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	
}
