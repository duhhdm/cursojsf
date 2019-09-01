package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.farmacia.dao.FornecedorDAO;
import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produto;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/bancofarmacia?autoReconnect=true&useSSL=false";
	private static FornecedorDAO fornecedorDao = new FornecedorDAO();
	private static ProdutoDAO produtoDao = new ProdutoDAO();
	
	static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {

        }
	}
	
	public static Connection Conectar() throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancofarmacia?autoReconnect=true&useSSL=false","root","root");
		return conexao;
	}
	
	public static void main (String[] args)  {
		//Fornecedores fornecedor = new Fornecedores(null, "odontoprev",1);
		Fornecedores fornecedor = new Fornecedores();
		//fornecedorDao.insert(fornecedor);
		Produto produto = new Produto();
		produto = produtoDao.findById(2);
		fornecedor = fornecedorDao.findById(produto.getIdFornecedor());
		System.out.println("id " + produto.getCodigo() + " descricao " + produto.getDescricao() + " Fornecedor "+ fornecedor.getDescricao());
		//System.out.println("id " + fornecedor.getCodigo() + " descricao " + fornecedor.getDescricao());
		System.out.print("conexao success");
	}
}
