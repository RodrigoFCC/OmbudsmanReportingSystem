package com.facisa.ouvidoriaJava.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facisa.ouvidoriaJava.entities.Person;
import com.facisa.ouvidoriaJava.exceptions.DomainException;
import com.facisa.ouvidoriaJava.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository personRepository;
	
	
	public String addPerson(Integer id, String name, String password, int option) {
		try {
			char firstCaracter = name.charAt(0);
			boolean firstCaracterIsNumber = Character.isDigit(firstCaracter);
			Person existingPerson = this.getByName(name);
			if (option == 1) {
			    if (existingPerson != null) {
			        throw new DomainException("A user with that name already exists");
			    }
				if (firstCaracterIsNumber == true) {
					throw new DomainException("Seu nome não pode começar com um número!");
				}
				Person person = new Person(id, name, password, 0);
				personRepository.save(person);
				return "Added Person";
			} else if (option == 2){
				
				if (existingPerson != null) {
			        throw new DomainException("A user with that name already exists");
			    }
				if (firstCaracterIsNumber == true) {
					throw new DomainException("Seu nome não pode começar com um número!");
				}
				Person person = new Person(id, name, password, 1);
				personRepository.save(person);
				return "Added Person";
			}
		} catch (Exception e) {
			System.out.println("Algo deu errado");
		}
		return "Error";
	}
	
	public void listAll() {
		for (Person p : personRepository.findAll()) {
			if (p != null) {
				System.out.println(p.toString());
			} else {
				System.out.println("Não existem Pessoas cadastradas.");
			}
		}
	}
	
	public Person getByName(String name) {
		ArrayList<Person> list = (ArrayList<Person>) personRepository.findAll();
		for (Person p : list) {
			return personRepository.findByName(name);
		}
		return null;
	}
	
	public void delete() {
		ArrayList<Person> list = (ArrayList<Person>) personRepository.findAll();
		for (Person p : list) {
			personRepository.delete(p);
		}
	}
	
	
	
}