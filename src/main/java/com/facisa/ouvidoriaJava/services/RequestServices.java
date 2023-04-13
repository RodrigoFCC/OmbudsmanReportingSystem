package com.facisa.ouvidoriaJava.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facisa.ouvidoriaJava.entities.Person;
import com.facisa.ouvidoriaJava.entities.Request;
import com.facisa.ouvidoriaJava.entities.RequestType;
import com.facisa.ouvidoriaJava.repositories.RequestRepository;

@Service
public class RequestServices {
		
	@Autowired
	RequestRepository requestRepository;
		
		public String addRequest(Integer id,RequestType requestType, String title,String description, Person person,int option) {
			try {
				if (option == 1) {
					Request request = new Request(id, requestType.PRAISE, title, description, person);
					requestRepository.save(request);
					return "Request added";
				} else if (option == 2){
					Request request = new Request(id, requestType.SUGGESTION, title, description, person);
					requestRepository.save(request);
					return "Request added";
				} else if (option == 3){
					Request request = new Request(id, requestType.COMPLAINT, title, description, person);
					requestRepository.save(request);
					return "Request added";
				}
			} catch (Exception e) {
				System.out.println("Unexpected error");
			}
			return "Error";
		}
		
		public void listAll(Person p) {
			ArrayList<Request> list = (ArrayList<Request>) requestRepository.findAll();
			if (p.getAccess() == 0) {
				for (Request r : list) {
					if (r.getPerson().getName().equals(p.getName())) {
						System.out.println(r.toString());
					}
				}
			} else if (p.getAccess() == 1) {
				for (Request r : requestRepository.findAll()) {
					if (r != null) {
						System.out.println(r.toString());
					} else {
						System.out.println("Requests is empty.");
						return;
					}
				}
			}
		}
		
		
		public void listById(Integer id) {
			System.out.println(requestRepository.findById(id)); 
			
		}
		
		public void listByTitle(String title) {
			System.out.println(requestRepository.findByTitle(title));
		}
		
		public void listByType(RequestType requestType, Person p) {
			ArrayList<Request> list = (ArrayList<Request>) requestRepository.findAll();
			if (p.getAccess() == 0) {
				for (Request r : list) {
					if (requestType != null && r.getRequestType() != null && r.getRequestType().equals(requestType) && r.getPerson().getName().equals(p.getName())) {
						System.out.println(r.toString());
					}
				}
			} else if (p.getAccess() == 1) {
				for (Request r : list) {
			        if (requestType != null && r.getRequestType() != null && r.getRequestType().equals(requestType)) {
						System.out.println(r.toString());
					} 
				}
			}
		}
			
		public void deleteAll() {
			ArrayList<Request> list = (ArrayList<Request>) requestRepository.findAll();
			for (Request r : list) {
				requestRepository.delete(r);
			}
		}
		
		public void deleteById(Integer id) {
			ArrayList<Request> list = (ArrayList<Request>) requestRepository.findAll();
			Request idRequest = null;
			for (Request r : list) {
				if (r.getId() == id) {
					idRequest = r;
				}
			}
			if (idRequest != null) {
				requestRepository.deleteById(id);
				System.out.println("Request deleted successfully");
			}else {
				System.out.println("Id not found");
			}
		}
		
		
}
