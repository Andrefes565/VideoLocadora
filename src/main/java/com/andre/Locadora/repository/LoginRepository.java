package com.andre.Locadora.repository;

import org.springframework.data.repository.CrudRepository;

import com.andre.Locadora.models.Login;



public interface LoginRepository extends CrudRepository<Login, String>{
	
	Login findByEmail(String email);
	
	
}
