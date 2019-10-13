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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="idPermissao")
	private Integer codigo;
	@Column(name="descricao", length = 50, nullable=false)
	private String descricao;
	@Column(name="ativo", nullable = false)
	@Enumerated(value=EnumType.STRING)
	private Ativo ativo;
}
