package br.com.coldigogeladeiras.jdbcinterface;

import java.util.List;
import com.google.gson.JsonObject;
import br.com.coldigogeladeiras.modelo.Produto;
import br.com.coldigogeladeiras.jdbc.JDBCProdutoDAO.Resultado;

public interface ProdutoDAO {
	public Resultado inserir(Produto produto);
	public List<JsonObject> buscarPorNome(String nome);
	public boolean deletar(int id);
	public Produto buscarPorId(int id);
	public Produto buscaPorMarcaId(int marcaId);
	public boolean alterar(Produto produto);
}
