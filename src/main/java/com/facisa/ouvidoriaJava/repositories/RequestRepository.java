package com.facisa.ouvidoriaJava.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.facisa.ouvidoriaJava.entities.Request;
import com.facisa.ouvidoriaJava.entities.RequestType;

public interface RequestRepository extends CrudRepository<Request, Integer>{
	
	Optional<Request> findByTitle(String title);
	Request findByRequestType(RequestType requestType);
} 