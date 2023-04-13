package com.facisa.ouvidoriaJava.entities;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.facisa.ouvidoriaJava.repositories.PersonRepository;
import com.facisa.ouvidoriaJava.services.PersonServices;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String password;
	private Integer access;
	
	public Person() {
	}

	
	public Person(Long id, String name, String password, Integer access) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.access = access;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Long getId() {
		return id;
	}
	
	public int getAccess() {
		return access;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	
	
	
}
