package br.com.farmacia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.farmacia.model.Venda;
import br.com.farmacia.util.HibernateUtil;

public class VendaHibernateDAO {
	
	@PersistenceContext
	EntityManager conn = HibernateUtil.getSessionFactory();

	public void salvar(Venda venda) {
		conn.getTransaction().begin();
		conn.persist(venda);
		conn.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listar() {
		EntityManager conn = HibernateUtil.getSessionFactory();
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT venda FROM Venda venda");
		List<Venda> lista = query.getResultList();
		conn.getTransaction().commit();
		return lista;
	}

	public Venda buscarPorId(Integer id) {
		Venda result = new Venda();
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT venda FROM Venda venda WHERE venda.id=:id");
		query.setParameter("id", id);
		result = (Venda) query.getSingleResult();
		conn.getTransaction().commit();
		return result;
	}

	public void deletar(Venda venda) {
		conn.getTransaction().begin();
		conn.remove(venda);
		conn.getTransaction().commit();
		
	}

	public void atualizar(Venda venda) {
		conn.getTransaction().begin();
		conn.merge(venda);
		conn.getTransaction().commit();
		
	}
	
}
