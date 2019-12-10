package br.com.farmacia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.farmacia.model.Item;
import br.com.farmacia.util.HibernateUtil;

public class ItemHibernateDAO {

	@PersistenceContext
	EntityManager conn = HibernateUtil.getSessionFactory();

	public void salvar(Item item) {
		conn.getTransaction().begin();
		conn.persist(item);
		conn.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Item> listar() {
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT item FROM Item item");
		List<Item> lista = query.getResultList();
		conn.getTransaction().commit();
		return lista;
	}

	public Item buscarPorId(Integer id) {
		Item result = new Item();
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT item FROM Item item WHERE item.id=:id");
		query.setParameter("id", id);
		result = (Item) query.getSingleResult();
		conn.getTransaction().commit();
		return result;
	}

	public void deletar(Item item) {
		conn.getTransaction().begin();
		conn.remove(item);
		conn.getTransaction().commit();
		
	}

	public void atualizar(Item item) {
		conn.getTransaction().begin();
		conn.merge(item);
		conn.getTransaction().commit();
		
	}
	
}
