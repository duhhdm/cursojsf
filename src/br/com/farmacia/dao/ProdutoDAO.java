package br.com.farmacia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.farmacia.domain.Produto;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutoDAO {
	
	public void insert(Produto produto) {
		String sql = "INSERT tbProduto VALUES (null,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoFactory.Conectar().prepareStatement(sql);
			ps.setString(1, produto.getDescricao());
			ps.setInt(2, produto.getQuantidade());
			ps.setInt(3, produto.getAtivo());
			ps.setDouble(4, produto.getPrecoFornecedor());
			ps.setDouble(5, produto.getPrecoFinal());
			ps.setInt(6, produto.getIdFornecedor());
			ps.close();
		} catch (SQLException e) {
			System.out.println("ocasionou erro no banco");
			e.printStackTrace();
		}
		
	}
	
	public Produto findById(Integer id) {
		String sql = "SELECT * FROM tbProduto WHERE idProduto="+id;
		Produto produto = new Produto();
		try {
			Statement ps = ConexaoFactory.Conectar().createStatement();
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()) {
				produto.setCodigo(rs.getInt("idProduto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPrecoFornecedor(rs.getDouble("precoFornecedor"));
				produto.setPrecoFinal(rs.getDouble("precoFinal"));
				produto.setAtivo(rs.getInt("ativo"));
				produto.setIdFornecedor(rs.getInt("fornecedorId"));
				ps.close();
			}
		}catch(SQLException e) {
			System.out.println("ocasionou erro no banco");
			e.printStackTrace();
		}
		return produto;
	}
}
