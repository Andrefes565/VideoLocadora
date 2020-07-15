package com.andre.Locadora.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Login")
@Entity
public class Login implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	public Login() {
		
	}

	public Login(String email, long senha) {
		this.email = email;
		this.senha = senha;
	}

	@Id
	@Column(name = "email", nullable = false, unique = true, length = 60)
	private String email;
	
	@Column(name = "senha", nullable = false, unique = false, length = 60)
	
	private long senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getSenha() {
		return senha;
	}

	public void setSenha(long senha) {
		this.senha = senha;
	}

	
	
	

}
