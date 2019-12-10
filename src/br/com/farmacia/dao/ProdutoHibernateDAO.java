package br.com.farmacia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.farmacia.model.Produto;
import br.com.farmacia.util.HibernateUtil;

public class ProdutoHibernateDAO {

	@PersistenceContext
	EntityManager conn = HibernateUtil.getSessionFactory();

	public void salvar(Produto produto) {
		conn.getTransaction().begin();
		conn.persist(produto);
		conn.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		EntityManager conn = HibernateUtil.getSessionFactory();
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT produto FROM Produto produto");
		List<Produto> lista = query.getResultList();
		conn.getTransaction().commit();
		return lista;
	}

	public Produto buscarPorId(Integer id) {
		Produto result = new Produto();
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT produto FROM Produto produto WHERE produto.id=:id");
		query.setParameter("id", id);
		result = (Produto) query.getSingleResult();
		conn.getTransaction().commit();
		return result;
	}

	public void deletar(Produto produto) {
		conn.getTransaction().begin();
		conn.remove(produto);
		conn.getTransaction().commit();
		
	}

	public void atualizar(Produto produto) {
		conn.getTransaction().begin();
		conn.merge(produto);
		conn.getTransaction().commit();
		
	}
}
