package br.com.coldigogeladeiras.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.JsonObject;

import br.com.coldigogeladeiras.jdbcinterface.ProdutoDAO;
import br.com.coldigogeladeiras.modelo.Produto;
import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.jdbc.JDBCMarcaDAO;

public class JDBCProdutoDAO implements ProdutoDAO {
	public class Resultado {
		public boolean status;
		public String msg;
	}
	
	private Connection conexao;
	
	public JDBCProdutoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public Resultado inserir(Produto produto) {
		Resultado resultado = new Resultado();
		
		String comando = "INSERT INTO produtos "
				+ "(id, categoria, modelo, capacidade, valor, marcas_id) "
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement p;
		
		if(this.verificaMarca(produto.getMarcaId()) == false) {
			resultado.status = false;
			resultado.msg = "A marca selecionada não existe, recarregue a página e tente novamente.";
		} else {
			try {
				p = this.conexao.prepareStatement(comando);
				
				p.setInt(1, produto.getId());
				p.setString(2, produto.getCategoria());
				p.setString(3,  produto.getModelo());
				p.setInt(4, produto.getCapacidade());
				p.setFloat(5, produto.getValor());
				p.setInt(6, produto.getMarcaId());
				
				p.execute();
				resultado.status = true;
				resultado.msg = "Produto cadastrado com sucesso!";
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.status = false;
				resultado.msg = "Erro ao cadastrar produto!";
			}
		}
		
		return resultado;
	}
	
	private boolean verificaMarca(int id) {
		JDBCMarcaDAO jdbcmarca = new JDBCMarcaDAO(conexao);
		Marca marca = jdbcmarca.buscarPorId(id);
		
		if(marca.getNome() == null) {
			return false;
		} else {
			return true;
		}
	}

	public List<JsonObject> buscarPorNome(String nome) {
		String comando = "SELECT produtos.*, marcas.nome as marca FROM produtos "
				+ "INNER JOIN marcas ON produtos.marcas_id = marcas.id ";
		
		if(!nome.equals("")) {
			comando += "WHERE modelo LIKE '%" + nome + "%' ";
		}
		
		comando += "ORDER BY categoria ASC, marcas.nome ASC, modelo ASC";
		
		List<JsonObject> listaProdutos = new ArrayList<JsonObject>();
		JsonObject produto = null;
		
		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				String marcaNome = rs.getString("marca");
				
				if(categoria.equals("1")) {
					categoria = "Geladeira";
				} else if(categoria.equals("2")) {
					categoria = "Freezer";
				}
				
				produto = new JsonObject();
				produto.addProperty("id", id);
				produto.addProperty("categoria", categoria);
				produto.addProperty("modelo", modelo);
				produto.addProperty("capacidade", capacidade);
				produto.addProperty("valor", valor);
				produto.addProperty("marcaNome", marcaNome);
				
				listaProdutos.add(produto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return listaProdutos;
	}

	public boolean deletar(int id) {
		String comando = "DELETE FROM produtos WHERE id = ?";
		PreparedStatement p;
		
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1,  id);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Produto buscarPorId(int id) {
		String comando = "SELECT * FROM produtos WHERE produtos.id = ?";
		Produto produto = new Produto();
		
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				int marcaId = rs.getInt("marcas_id");
				
				produto.setId(id);
				produto.setCategoria(categoria);
				produto.setMarcaId(marcaId);
				produto.setModelo(modelo);
				produto.setCapacidade(capacidade);
				produto.setValor(valor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}
	
	public Produto buscaPorMarcaId(int idMarca) {
		String comando = "SELECT * FROM produtos WHERE produtos.marcas_id = ?";
		Produto produto = new Produto();
		
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, idMarca);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				int marcaId = rs.getInt("marcas_id");
				
				produto.setId(id);
				produto.setCategoria(categoria);
				produto.setMarcaId(marcaId);
				produto.setModelo(modelo);
				produto.setCapacidade(capacidade);
				produto.setValor(valor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produto;
	}
	
	public boolean alterar(Produto produto) {
		String comando = "UPDATE produtos "
				+ "SET categoria=?, modelo=?, capacidade=?, valor=?, marcas_id=? "
				+ "WHERE id=?";
		PreparedStatement p;
		
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, produto.getCategoria());
			p.setString(2, produto.getModelo());
			p.setInt(3, produto.getCapacidade());
			p.setFloat(4, produto.getValor());
			p.setInt(5, produto.getMarcaId());
			p.setInt(6, produto.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
