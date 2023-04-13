package com.facisa.ouvidoriaJava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.facisa.ouvidoriaJava.entities.Person;
import com.facisa.ouvidoriaJava.entities.Request;

public interface PersonRepository extends CrudRepository<Person, Long>{
	
	Person findByName(String name);
}
