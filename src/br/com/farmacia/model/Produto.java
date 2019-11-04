package br.com.farmacia.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.farmacia.dto.enums.Ativo;

@Entity
@Table(name="tbproduto")
public class Produto {
	
	@Id
	@Column(name = "idProduto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "precoFornecedor")
	private Double precoFornecedor;
	
	@Column(name = "precoFinal")
	private Double precoFinal;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private Ativo ativo;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "fornecedorId", referencedColumnName = "idFornecedor", nullable = false)
	private Fornecedores idFornecedor = new Fornecedores();
	
	public Produto() {
		
	}
	
	
	public Produto(Integer codigo, String descricao, Double precoFornecedor, Double precoFinal, Integer quantidade,
			Ativo ativo, Fornecedores idFornecedor) {
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

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
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
