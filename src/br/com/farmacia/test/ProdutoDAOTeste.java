package br.com.farmacia.test;

import org.junit.Test;

import br.com.farmacia.util.HibernateUtil;

public class ProdutoDAOTeste {
	
	@Test
	public void testando() {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}
	
}
