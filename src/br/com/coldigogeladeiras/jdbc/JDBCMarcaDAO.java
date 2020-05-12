package br.com.coldigogeladeiras.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.coldigogeladeiras.jdbcinterface.MarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.modelo.Produto;

public class JDBCMarcaDAO implements MarcaDAO{
	public class Resultado {
		public boolean status;
		public String msg;
	}
	
	private Connection conexao;
	
	public JDBCMarcaDAO() {	}
	
	public JDBCMarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public boolean inserir(Marca marca) {
		String comando = "INSERT INTO marcas "
				+ "(id, nome) "
				+ "VALUES (?,?)";
		PreparedStatement p;
		
		try {
			p = this.conexao.prepareStatement(comando);
			
			p.setInt(1, marca.getId());
			p.setString(2, marca.getNome());
			
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Marca> buscar(String valorBusca) {
		String comando = "SELECT * FROM marcas ";
		
		if(valorBusca != null) {			
			comando += "WHERE nome LIKE '%" + valorBusca + "%';";
		}
		
		List<Marca> listMarcas = new ArrayList<Marca>();
		
		Marca marca = null;
		
		try { 
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			while(rs.next()) {
				marca = new Marca();
				
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				
				marca.setId(id);
				marca.setNome(nome);
				
				listMarcas.add(marca);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return listMarcas;
	}
	
	public boolean alterar(Marca marca) {
		String comando = "UPDATE marcas "
				+ "SET nome=? WHERE id=?";
		PreparedStatement p;
		
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, marca.getNome());
			p.setInt(2, marca.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Resultado deletar(int id) {
		String comando = "DELETE FROM marcas WHERE id = ?";
		PreparedStatement p;
		Resultado resultado = new Resultado();
		
		if(this.verificaVinculo(id)) {
			resultado.status = false;
			resultado.msg = "Marca vínculada a um produto.";
		} else {
			try {
				p = this.conexao.prepareStatement(comando);
				p.setInt(1,  id);
				p.execute();
				
				resultado.status = true;
				resultado.msg = "Marca deleta com sucesso.";
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.status = false;
				resultado.msg = "Erro ao deletar marca.";
			}
		}
		
		return resultado;
	}
	
	public boolean verificaVinculo(int id) {
		JDBCProdutoDAO produtoDAO = new JDBCProdutoDAO(conexao);
		Produto produto = produtoDAO.buscaPorMarcaId(id);
		
		if(produto.getModelo() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Marca buscarPorId(int id) {
		String comando = "SELECT * FROM marcas WHERE marcas.id = ?";
		Marca marca = new Marca();
		
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			
			while(rs.next()) {
				marca = new Marca();
				
				int idNovo = rs.getInt("id");
				String nome = rs.getString("nome");
				
				marca.setId(idNovo);
				marca.setNome(nome);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return marca;
	}
}

