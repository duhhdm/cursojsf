package br.com.farmacia.model;

import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbVenda")
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVenda")
	private Long codigo;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false)
	private Calendar data;
	@Column(name = "total")
	private Double total;
	
	@JoinColumn(name = "funcionarioId",referencedColumnName = "idFuncionario", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	private Funcionario funcionario;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Venda() {
		
	}
	
	public Venda(Long codigo, Calendar data, Double total,Funcionario funcionario) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.total = total;
		this.funcionario=funcionario;
	}
	
	
}
