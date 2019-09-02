package br.com.farmacia.domain;

public class Produto {
	
	private Integer codigo;
	private String descricao;
	private Double precoFornecedor;
	private Double precoFinal;
	private Integer quantidade;
	private Integer ativo;
	private Fornecedores idFornecedor = new Fornecedores();
	
	public Produto() {
		
	}
	
	
	public Produto(Integer codigo, String descricao, Double precoFornecedor, Double precoFinal, Integer quantidade,
			Integer ativo, Fornecedores idFornecedor) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.precoFornecedor = precoFornecedor;
		this.precoFinal = precoFinal;
		this.quantidade = quantidade;
		this.ativo = ativo;
		this.idFornecedor=idFornecedor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPrecoFornecedor() {
		return precoFornecedor;
	}

	public void setPrecoFornecedor(Double precoFornecedor) {
		this.precoFornecedor = precoFornecedor;
	}

	public Double getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(Double precoFinal) {
		this.precoFinal = precoFinal;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedores getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Fornecedores idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
}
