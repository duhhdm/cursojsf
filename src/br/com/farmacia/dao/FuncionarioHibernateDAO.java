package br.com.farmacia.dao;

import java.sql.SQLException;
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
		Query query = conn.createQuery("SELECT funcionario FROM Funcionario funcionario");
		List<Funcionario> lista = query.getResultList();
		return lista;
	}

	public Funcionario buscarPorId(Long id) {
		Funcionario result = new Funcionario();
		conn.getTransaction().begin();
		Query query = conn.createQuery("SELECT funcionario FROM Funcionario funcionario WHERE funcionario.id=:id");
		query.setParameter("id", id);
		try{
			result = (Funcionario) query.getSingleResult();
			return result;
		}catch(Exception e) {
			return null;
		}
		
		
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
