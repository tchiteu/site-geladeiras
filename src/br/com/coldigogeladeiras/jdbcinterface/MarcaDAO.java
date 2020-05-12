package br.com.coldigogeladeiras.jdbcinterface;

import java.util.List;

import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.modelo.Produto;
import br.com.coldigogeladeiras.jdbc.JDBCMarcaDAO.Resultado;

public interface MarcaDAO {
	public List<Marca> buscar(String valorBusca);
	public boolean inserir(Marca marca);
	public Resultado deletar(int id);
	public Marca buscarPorId(int id);
	public boolean alterar(Marca marca);
	public boolean verificaVinculo(int id);
}
