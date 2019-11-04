package br.com.farmacia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.farmacia.model.Funcionario;
import br.com.farmacia.util.HibernateUtil;

public class FuncionarioHibernateDAO {
	
	@PersistenceContext
	EntityManager conn = HibernateUtil.getSessionFactory();

	public void salvar(Funcionario funcionario) {
		conn.getTransaction().begin();
		conn.persist(funcionario);
		conn.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT funcionario FROM Funcionario funcionario");
		List<Funcionario> lista = query.getResultList();
		conn.getTransaction().commit();
		return lista;
	}

	public Funcionario buscarPorId(Integer id) {
		Funcionario result = new Funcionario();
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT funcionario FROM Funcionario funcionario WHERE funcionario.id=:id");
		query.setParameter("id", id);
		result = (Funcionario) query.getSingleResult();
		conn.getTransaction().commit();
		return result;
	}

	public void deletar(Funcionario funcionario) {
		conn.getTransaction().begin();
		conn.remove(funcionario);
		conn.getTransaction().commit();
		
	}

	public void atualizar(Funcionario funcionario) {
		conn.getTransaction().begin();
		conn.merge(funcionario);
		conn.getTransaction().commit();
		
	}
}
