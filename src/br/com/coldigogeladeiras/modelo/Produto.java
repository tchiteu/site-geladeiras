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
		return this.valor;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public void setMarcaId(int marcaId) {
		this.marcaId = marcaId;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
}
