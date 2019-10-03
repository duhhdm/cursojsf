package br.com.farmacia.domain;


public class Fornecedores {
	
	private Integer codigo;
	private String descricao;
	private Integer ativo;
	
	public Fornecedores() {
		
	}
	
	public Fornecedores(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Fornecedores(Integer codigo, String descricao, Integer ativo) {
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
	
	public Integer getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}
	
	
}
