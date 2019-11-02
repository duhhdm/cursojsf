package br.com.farmacia.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.engine.spi.Status;

import br.com.farmacia.dto.enums.Ativo;
import br.com.farmacia.model.Fornecedores;
import br.com.farmacia.util.HibernateUtil;

public class FornecedorHibernateDAO {
	
	
	public void salvar(Fornecedores fornecedor) {
		EntityManager conn = HibernateUtil.getSessionFactory();
		conn.getTransaction().begin();
		conn.persist(fornecedor);
		conn.getTransaction().commit();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Fornecedores> listar (){
		EntityManager conn = HibernateUtil.getSessionFactory();
		conn.getTransaction().begin();
		Query query = conn.createNativeQuery("SELECT * FROM tbFornecedor");
		List<Fornecedores> lista = new ArrayList<Fornecedores>();
		List<Object[]> result = query.getResultList();
		for(Object[] aux : result) {
			Fornecedores auxF = new Fornecedores();
			auxF.setCodigo((Integer)aux[0]);
			auxF.setDescricao((String)aux[2]);
			if(aux[1].toString().equals(Ativo.SIM.getDesc()))
				auxF.setAtivo(Ativo.SIM);
			else
				auxF.setAtivo(Ativo.NAO);
			lista.add(auxF);
		}
		conn.getTransaction().commit();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedores> listarTeste (){
		EntityManager conn = HibernateUtil.getSessionFactory();
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT fornecedor FROM Fornecedores fornecedor");
		List<Fornecedores> lista = query.getResultList();
		conn.getTransaction().commit();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedores> listarHql(){
		EntityManager conn = HibernateUtil.getSessionFactory();
		List<Fornecedores> result = new ArrayList<Fornecedores>();
		conn.getTransaction().begin();
		Query query = conn.createNamedQuery("Fornecedores.listarHql");
		result = query.getResultList();
		conn.getTransaction().commit();
		return result;
	}
	
	public Fornecedores buscarPorId(Integer id) {
		EntityManager conn = HibernateUtil.getSessionFactory();
		Fornecedores result = new Fornecedores();
		conn.getTransaction().begin();
		Query query = conn.createNamedQuery("Fornecedores.buscaPorId");
		query.setParameter("id", id);
		result = (Fornecedores)query.getSingleResult();
		conn.getTransaction().commit();
		return result;
	}
	
	public void deletar(Fornecedores fornecedor) {
		EntityManager conn = HibernateUtil.getSessionFactory();
		conn.getTransaction().begin();
		conn.remove(fornecedor);
		conn.getTransaction().commit();
	}
	
	
	public void atualizar(Fornecedores fornecedor) {
		EntityManager conn = HibernateUtil.getSessionFactory();
		conn.getTransaction().begin();
		conn.merge(fornecedor);
		conn.getTransaction().commit();
	}
	
	public Boolean temProduto(Integer id) {
		EntityManager conn = HibernateUtil.getSessionFactory();
		conn.getTransaction().begin();
		StringBuffer sql = new StringBuffer();
		 
		sql.append(" SELECT count(idProduto) ");
		sql.append(" FROM tbProduto ");
		sql.append(" WHERE fornecedorId = :id");
		Query query = conn.createNativeQuery(sql.toString());
		query.setParameter("id", id);
		BigInteger result = (BigInteger) query.getSingleResult();
		conn.getTransaction().commit();
		if(result.intValue() == 1)
			return true;
		return false;
	}
	
}
