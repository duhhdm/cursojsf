package br.com.farmacia.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.farmacia.dto.enums.Ativo;
import br.com.farmacia.model.Fornecedores;
import br.com.farmacia.model.Produto;

public class testando {
	public static void main(String args[]) {
		
		Fornecedores forn = new Fornecedores();
		
		forn.setAtivo(Ativo.SIM);
		forn.setCodigo(1);
		forn.setDescricao("danadinho");
		Produto produto = new Produto(null,"shampoo",1.00,4.00,5,Ativo.SIM,forn);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmacias");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		System.out.println(produto.getIdFornecedor().getCodigo());
		manager.close();
	}
}
