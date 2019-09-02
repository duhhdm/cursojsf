package br.com.farmacia.test;

import java.util.List;

import org.junit.Test;

import br.com.farmacia.dao.FornecedorDAO;
import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produto;

public class ProdutoDAOTeste {
	
	@Test
	public void testando() {
		Fornecedores f = new Fornecedores();
		f.setCodigo(1);
		FornecedorDAO fDao = new FornecedorDAO();
		//Produto p1 = new Produto(null, "arroz", 2.50, 5.80, 10,1,f);
		ProdutoDAO pDao = new ProdutoDAO();
		//pDao.insert(p1);
		//List<Produto> p = pDao.findAll();
		//System.out.println(fDao.temProduto(2));
		//pDao.remove(4);
		pDao.update(2, "chuchu", 2.0, 4.0);
		
	}
	
}
