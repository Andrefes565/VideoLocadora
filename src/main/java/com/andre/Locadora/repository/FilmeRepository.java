package com.andre.Locadora.repository;

import org.springframework.data.repository.CrudRepository;

import com.andre.Locadora.models.Filme;


public interface FilmeRepository extends CrudRepository<Filme, String>{
	Filme findByCodigo(long codigo);

}
