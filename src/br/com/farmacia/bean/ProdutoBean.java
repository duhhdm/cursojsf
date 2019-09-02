package br.com.farmacia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.dao.FornecedorDAO;
import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produto;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	
	private List<Produto> itens;
	private List<Produto> itensFiltrados;
	private List<Fornecedores> fornecedor;
	private Produto produto = new Produto();
	private ProdutoDAO pDao = new ProdutoDAO();
	private FornecedorDAO fDao = new FornecedorDAO();
	
	@PostConstruct
	public void init() {
		try {
			itens = pDao.findAll();
			setFornecedor(fDao.findAll());
		}catch(Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Ocorreu um erro desconhecido");
			e.printStackTrace();
		}
	}

	public void inserirProduto() {
		try {
			produto.setAtivo(1);
			pDao.insert(produto);
			init();
			JSFUtil.adicionarMensagemSucesso("", "produto: "+produto.getDescricao()+" Inserido com sucesso");
		}catch(Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel inserir dados");
			e.printStackTrace();
		}
	}
	
	public void removerProduto(Integer id) {
		try {
			pDao.remove(id);
			init();
			JSFUtil.adicionarMensagemSucesso("", "produto: "+produto.getDescricao()+" removido com sucesso");
		}catch(Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel remover dados");
			e.printStackTrace();
		}
	}
	
	public void alterarProduto(Integer id, String descricao,Double precoFornecedor,Double precoFinal) {
		try{
			pDao.update(id,descricao,precoFornecedor,precoFinal);
			init();
			JSFUtil.adicionarMensagemSucesso("", "produto: "+produto.getDescricao()+" alterado com sucesso");
		}catch(Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel remover dados");
			e.printStackTrace();
		}
		
	}
	
	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public List<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoDAO getpDao() {
		return pDao;
	}

	public void setpDao(ProdutoDAO pDao) {
		this.pDao = pDao;
	}

	public List<Fornecedores> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(List<Fornecedores> fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
}
