package br.com.farmacia.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.farmacia.dao.FornecedorDAO;
import br.com.farmacia.dao.FornecedorHibernateDAO;
import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.dao.ProdutoHibernateDAO;
import br.com.farmacia.dto.enums.Ativo;
import br.com.farmacia.model.Fornecedores;
import br.com.farmacia.model.Produto;
import br.com.farmacia.util.JSFUtil;
import br.com.farmacia.util.SessionUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private List<Produto> itens;
	private List<Produto> itensFiltrados;
	private List<Fornecedores> fornecedor = new ArrayList<Fornecedores>();
	private Produto produto = new Produto();
	private ProdutoHibernateDAO pDao = new ProdutoHibernateDAO();
	private FornecedorHibernateDAO fDao = new FornecedorHibernateDAO();
	private String usuario = new String();

	@PostConstruct
	public void init() {
		if (SessionUtil.getParam("Logado") != null)
			usuario = SessionUtil.getParam("Logado").toString();
		if (usuario != null && !usuario.equals("")) {
			try {
				itens = pDao.listar();
				fornecedor = listaFornecedor();
				System.out.println(usuario);
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

	public void inserirProduto() {
		try {
			produto.setAtivo(Ativo.SIM);
			pDao.salvar(produto);
			init();
			JSFUtil.adicionarMensagemSucesso("", "produto: " + produto.getDescricao() + " Inserido com sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel inserir dados");
			e.printStackTrace();
		}
	}

	public void removerProduto(Integer id) {
		Produto aux = new Produto();
		aux = pDao.buscarPorId(id);
		try {

			if (aux != null) {
				pDao.deletar(produto);
				init();
				JSFUtil.adicionarMensagemSucesso("", "produto: " + produto.getDescricao() + " removido com sucesso");
			} else {
				JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel remover dados");
			}
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel remover dados");
			e.printStackTrace();
		}
	}

	public void alterarProduto(Integer id, String descricao, Double precoFornecedor, Double precoFinal) {
		Produto aux = new Produto();
		aux = pDao.buscarPorId(id);
		if (descricao.equals(aux.getDescricao()))
			aux.setDescricao(descricao);
		if (precoFornecedor != aux.getPrecoFornecedor())
			aux.setPrecoFornecedor(precoFornecedor);
		if (precoFinal != aux.getPrecoFinal())
			aux.setPrecoFinal(precoFinal);
		try {
			pDao.atualizar(aux);
			init();
			JSFUtil.adicionarMensagemSucesso("", "produto: " + produto.getDescricao() + " alterado com sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi possivel remover dados");
			e.printStackTrace();
		}

	}

	public void somaProduto(Integer id, Integer quantidade) {
		Produto aux = new Produto();
		aux = pDao.buscarPorId(id);
		if (quantidade < 0) {
			init();
			JSFUtil.adicionarMensagemErro("", "ERRO: Não foi adicionar mais Produtos");
		} else {
			Integer soma = aux.getQuantidade() + quantidade;
			aux.setQuantidade(soma);
			pDao.atualizar(aux);
			init();
			JSFUtil.adicionarMensagemSucesso("",
					"produto: Estoque do " + produto.getDescricao() + " atualizado com sucesso");
		}
	}

	public List<Fornecedores> listaFornecedor() {
		List<Fornecedores> lista = fDao.listarTeste();
		List<Fornecedores> aux = new ArrayList<Fornecedores>();
		for (Fornecedores i : lista) {
			if (i.getAtivo().equals(Ativo.SIM))
				aux.add(i);
		}
		return aux;
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

	public List<Fornecedores> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(List<Fornecedores> fornecedor) {
		this.fornecedor = fornecedor;
	}

}
