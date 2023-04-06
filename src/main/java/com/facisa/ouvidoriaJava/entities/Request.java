package com.facisa.ouvidoriaJava.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private RequestType requestType;
	private String title;
	private String description;
	private Person person;

	public Request() {

	}
	
	
	
	public Request(Integer id,RequestType requestType, String title,String description, Person person) {
		this.Id = id;
		this.requestType = requestType;
		this.title = title;
		this.description = description;
		this.person = person;
	}

	
	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getId() {
		return Id;
	}



	public RequestType getRequestType() {
		return requestType;
	}



	public String getTitle() {
		return title;
	}



	public Person getPerson() {
		return person;
	}



	@Override
	public String toString() {
		return "Request ID: " + Id + "\nRequest Type: " + requestType + "\nTitle: " + title + "\nDescription: " + description + "\nStudentID: " + person.getId() + "\nStudent Name: " + person.getName() + "\n-----------------------";
	}
	
	
}
