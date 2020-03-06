package br.com.coldigogeladeiras.modelo;

import java.io.Serializable;

public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String categoria;
	private int marcaId;
	private String modelo;
	private int capacidade;
	private float valor;
	
	public int getId() {
		return this.id;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public String getModelo() {
		return this.modelo;
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	public int getMarcaId() {
		return this.marcaId;
	}

	public float getValor() {
		// TODO Auto-generated method stub
		return 0;
	}
}
