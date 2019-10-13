package br.com.farmacia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.farmacia.factory.ConexaoFactory;
import br.com.farmacia.model.Produto;

public class ProdutoDAO {
	
	private FornecedorDAO forDao = new FornecedorDAO();
	
	public void insert(Produto produto) {
		String sql = "INSERT tbProduto VALUES (null,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoFactory.Conectar().prepareStatement(sql);
			ps.setString(1, produto.getDescricao());
			ps.setInt(2, produto.getQuantidade());
			ps.setInt(3, produto.getAtivo());
			ps.setDouble(4, produto.getPrecoFornecedor());
			ps.setDouble(5, produto.getPrecoFinal());
			ps.setInt(6, produto.getIdFornecedor().getCodigo());
			ps.execute();
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
				produto.setIdFornecedor(forDao.findById(rs.getInt("fornecedorId")));
				ps.close();
			}
		}catch(SQLException e) {
			System.out.println("ocasionou erro no banco");
			e.printStackTrace();
		}
		return produto;
	}
	
	public List<Produto> findAll() {
		String sql = "SELECT * FROM tbProduto";
		List<Produto> lista = new ArrayList<Produto>();
		
		try {
			Statement ps = ConexaoFactory.Conectar().createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("idProduto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPrecoFornecedor(rs.getDouble("precoFornecedor"));
				produto.setPrecoFinal(rs.getDouble("precoFinal"));
				produto.setAtivo(rs.getInt("ativo"));
				produto.setIdFornecedor(forDao.findById(rs.getInt("fornecedorId")));
				if(produto.getCodigo()!=null)
					lista.add(produto);
			}
			ps.close();
		}catch(SQLException e) {
			System.out.println("ocasionou erro no banco");
			e.printStackTrace();
		}
		return lista;
	}
	
	public void remove(Integer id) {
		String sql = "UPDATE tbProduto SET ativo=0 WHERE idProduto=?";
		PreparedStatement ps = null;
		try {
			ps = ConexaoFactory.Conectar().prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Integer id,String descricao,Double precoFornecedor, Double precoFinal) {
		String sql = "UPDATE tbProduto SET descricao=?, precoFornecedor=?, precoFinal=? WHERE idProduto=?";
		PreparedStatement ps = null;
		try {
			ps = ConexaoFactory.Conectar().prepareStatement(sql);
			ps.setString(1, descricao);
			ps.setDouble(2, precoFornecedor);
			ps.setDouble(3, precoFinal);
			ps.setInt(4, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
