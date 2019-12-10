package br.com.farmacia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.farmacia.dto.enums.Ativo;
import br.com.farmacia.factory.ConexaoFactory;
import br.com.farmacia.model.Fornecedores;

public class FornecedorDAO {

	public void insert(Fornecedores fornecedor) {
		String sql = "INSERT tbFornecedor VALUES (null,?,?)";
		
		try {
			PreparedStatement ps =  ConexaoFactory.Conectar().prepareStatement(sql);
			ps.setString(1, fornecedor.getDescricao());
			ps.setString(2, Ativo.SIM.getDesc());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Conexao falhou");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public Fornecedores findById(Integer id) {
		String sql = "SELECT * FROM tbFornecedor WHERE idFornecedor="+id;
		Fornecedores fornecedor = new Fornecedores();
		try {
			Statement ps = ConexaoFactory.Conectar().createStatement();
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()) {
				fornecedor.setCodigo(rs.getInt("idFornecedor"));
				fornecedor.setDescricao(rs.getString("descricao"));
				if(Ativo.SIM.equals(rs.getString("ativo")))
					fornecedor.setAtivo(Ativo.SIM);
				else
					fornecedor.setAtivo(Ativo.NAO);
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("Conexao falhou");
			e.printStackTrace();
		}
		return fornecedor;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public List<Fornecedores> findAll(){
		String sql = "SELECT * FROM tbFornecedor";
		List<Fornecedores> lista = new ArrayList<Fornecedores>();
		try {
			Statement ps = ConexaoFactory.Conectar().createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				Fornecedores fornecedor = new Fornecedores();
				fornecedor.setCodigo(rs.getInt("idFornecedor"));
				fornecedor.setDescricao(rs.getString("descricao"));
				if(Ativo.SIM.equals(rs.getString("ativo")))
					fornecedor.setAtivo(Ativo.SIM);
				else
					fornecedor.setAtivo(Ativo.NAO);
				if(fornecedor.getCodigo()!=null)
					lista.add(fornecedor);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Boolean remove(Integer id) {
		String sql = "UPDATE tbFornecedor SET ativo=0 WHERE idFornecedor = ?";
		try {
			PreparedStatement ps = ConexaoFactory.Conectar().prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean update(Integer id, String value) {
		String sql = "UPDATE tbFornecedor SET descricao = ? WHERE idFornecedor = ?";
		try {
			PreparedStatement ps = ConexaoFactory.Conectar().prepareStatement(sql);
			ps.setString(1, value);
			ps.setInt(2, id);
			ps.execute();
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean temProduto(Integer id) {
		String sql = "SELECT idProduto FROM tbProduto WHERE fornecedorId="+id+" AND ativo=1";
		Statement ps = null;
		ResultSet rs =null;
		try {
			ps = ConexaoFactory.Conectar().createStatement();
			rs = ps.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
