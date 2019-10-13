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
@Table(name = "tbFuncionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idFuncionario")
	private Long codigo;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "funcao")
	private String funcao;
	
	@Column(name="senha")
	private String senha;

	@Column(name = "ativo")
	@Enumerated(value =  EnumType.STRING)
	private Ativo ativo;
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Funcionario(){
		
	}

	public Funcionario(Long codigo, String nome, String funcao, String senha, Ativo ativo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.funcao = funcao;
		this.senha = senha;
		this.ativo = ativo;
	}
	
}
