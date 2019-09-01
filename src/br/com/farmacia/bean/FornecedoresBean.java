package br.com.farmacia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.dao.FornecedorDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.dto.FornecedorDTO;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {
	//private ListDataModel<FornecedorDTO> itens;
	private ArrayList<FornecedorDTO> itens;
	private ArrayList<FornecedorDTO> itensFiltrados;

	private Fornecedores fornecedores = new Fornecedores();

	private FornecedorDAO forDao = new FornecedorDAO();

	@PostConstruct
	public void preparaPesquisa() {
		try {
			List<Fornecedores> lista = forDao.findAll();
			List<FornecedorDTO> listaAtivos = new ArrayList<FornecedorDTO>();
			for( Fornecedores aux: lista) {
				FornecedorDTO aux1 = new FornecedorDTO();
				aux1.setCodigo(aux.getCodigo());
				aux1.setDescricao(aux.getDescricao());
				if(aux.getAtivo()!=0) {
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
			fornecedores.setAtivo(1);
			forDao.insert(fornecedores);
			preparaPesquisa();
			JSFUtil.adicionarMensagemSucesso("", "Fornecedor: "+fornecedores.getDescricao()+" Inserido com sucesso");
		}catch(Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel inserir dados");
			e.printStackTrace();
		}
	}
	
	public void removerFornecedor(Integer id) {
		try {
			forDao.remove(id);
			preparaPesquisa();
			JSFUtil.adicionarMensagemSucesso("", "Fornecedor removido com sucesso");
		}catch(Exception e){
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel remover dados");
			e.printStackTrace();
		}
	}
	
	public void alterarFornecedor(Integer id, String value) {
		try {
			Fornecedores aux = new Fornecedores();
			aux = forDao.findById(id);
			if(value.equals(aux.getDescricao())) {
				JSFUtil.adicionarMensagemErro("", "ERRO: Não houve alteração dos dados");
			}else {
				forDao.update(id, value);
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

	public void setItensAtivos(ArrayList<FornecedorDTO> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}	
}
