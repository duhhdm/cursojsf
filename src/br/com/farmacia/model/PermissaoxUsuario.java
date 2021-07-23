package br.com.farmacia.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbpermissaoxusuario")
public class PermissaoxUsuario {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "funcionarioId", referencedColumnName = "idFuncionario", nullable = false)
	private Funcionario funcionarioId = new Funcionario();
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "permissaoId", referencedColumnName = "idPermissao", nullable = false)
	private Permissao permissaoId = new Permissao();
	
	public PermissaoxUsuario() {
		
	}

	public PermissaoxUsuario(Long codigo, Funcionario funcionarioId, Permissao permissaoId) {
		super();
		this.codigo = codigo;
		this.funcionarioId = funcionarioId;
		this.permissaoId = permissaoId;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Funcionario getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Funcionario funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public Permissao getPermissaoId() {
		return permissaoId;
	}

	public void setPermissaoId(Permissao permissaoId) {
		this.permissaoId = permissaoId;
	}
	
}
