package br.com.farmacia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.farmacia.dto.enums.Ativo;

@Entity
@Table(name = "tbPermissao")
public class Permissao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPermissao")
	private Integer codigo;
	@Column(name="descricao", length = 50, nullable=false)
	private String descricao;
	@Column(name="ativo", nullable = false)
	@Enumerated(value=EnumType.STRING)
	private Ativo ativo;
	
	public Permissao() {
		
	}
	
	public Permissao(Integer codigo, String descricao, Ativo ativo) {
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
