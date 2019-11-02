package br.com.farmacia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.farmacia.dto.enums.Ativo;

@Entity
@Table(name="tbfornecedor")
@NamedQueries({@NamedQuery(name = "Fornecedores.listarHql",query = "SELECT fornecedor FROM Fornecedores fornecedor"),
	@NamedQuery(name = "Fornecedores.buscaPorId", query = "SELECT fornecedor FROM Fornecedores fornecedor WHERE fornecedor.codigo = :id")})
public class Fornecedores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="idfornecedor")
	private Integer codigo;
	@Column(name="descricao", length = 50, nullable=false)
	private String descricao;
	@Column(name="ativo", nullable = false)
	@Enumerated(value=EnumType.STRING)
	private Ativo ativo;
	
	
	public Fornecedores() {
		
	}
	
	public Fornecedores(Integer codigo, String descricao, Ativo ativo) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.ativo = ativo;
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
	
	public Ativo getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
}
