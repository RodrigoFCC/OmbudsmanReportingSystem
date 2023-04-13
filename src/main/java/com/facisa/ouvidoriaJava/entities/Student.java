package com.facisa.ouvidoriaJava.entities;

import jakarta.persistence.Entity;

@Entity
public class Student extends Person{

	public Student() {
		
	}
	
	public Student(Long id, String name, String password, Integer access) {
		super(id, name, password, access);
	}

}
