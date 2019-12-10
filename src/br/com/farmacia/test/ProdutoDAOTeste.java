package br.com.farmacia.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.transform.ToListResultTransformer;
import org.junit.Test;

import br.com.farmacia.dao.FornecedorHibernateDAO;
import br.com.farmacia.dto.enums.Ativo;
import br.com.farmacia.model.Fornecedores;

public class ProdutoDAOTeste {
	
	@Test
	public void testando() {
		FornecedorHibernateDAO dao = new FornecedorHibernateDAO();
		Fornecedores forn = new Fornecedores(1,"Unilever",Ativo.NAO);
		//dao.salvar(forn);
		List<Fornecedores> listas = dao.listarTeste();
		Fornecedores lista = listas.get(0);
		//Fornecedores teste = listas.get(0);
		//teste.setAtivo(Ativo.NAO);
		lista.setDescricao("danadinho");
		dao.atualizar(lista);
//		Fornecedores lista = dao.buscarPorId(1);
//		System.out.println(lista.get(0).getAtivo());
//		System.out.println(lista.get(0).getDescricao());
//		System.out.println(lista.get(0).getCodigo());
		System.out.println(lista.getAtivo());
		System.out.println(lista.getDescricao());
		System.out.println(lista.getCodigo());
	}
	
}
