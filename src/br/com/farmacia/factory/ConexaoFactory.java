package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.farmacia.dao.FornecedorDAO;
import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.model.Fornecedores;
import br.com.farmacia.model.Produto;

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
	
	
}
