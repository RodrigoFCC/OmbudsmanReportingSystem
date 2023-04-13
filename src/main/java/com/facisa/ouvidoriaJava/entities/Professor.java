package com.facisa.ouvidoriaJava.entities;

import jakarta.persistence.Entity;

@Entity
public class Professor extends Person{
	
	public Professor() {
		
	}
	
	public Professor(Long id, String name, String password, Integer access) {
		super(id, name, password, access);
	}

	
	
}
