package com.andre.Locadora.models;

import java.io.Serializable;
import javax.persistence.*;


@Table(name = "Filmes")
@Entity
public class Filme implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codigo", nullable = false, unique = true, length = 60)
	
	private long codigo;
	
	@Column(name = "filme", nullable = false, unique = true, length = 60)
	private String filme;
	
	@Column(name = "data", nullable = true, unique = false, length = 60)
	private String data;
	
	@Column(name = "cliente", nullable = true, unique = false, length = 60)
	private String cliente;
	
	@Column(name = "genero", nullable = false, unique = false, length = 60)
	private String genero;
	
	@Column(name = "locado", nullable = false, unique = false, length = 10)
	private Boolean locado = false;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getFilme() {
		return filme;
	}
	public void setFilme(String filme) {
		this.filme = filme;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Boolean getLocado() {
		return locado;
	}
	public void setLocado(Boolean locado) {
		this.locado = locado;
	}

	
}
