package com.facisa.ouvidoriaJava;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.facisa.ouvidoriaJava.entities.Person;
import com.facisa.ouvidoriaJava.entities.RequestType;
import com.facisa.ouvidoriaJava.exceptions.DomainException;
import com.facisa.ouvidoriaJava.repositories.PersonRepository;
import com.facisa.ouvidoriaJava.repositories.RequestRepository;
import com.facisa.ouvidoriaJava.services.PersonServices;
import com.facisa.ouvidoriaJava.services.RequestServices;


@SpringBootApplication
public class OuvidoriaJavaApplication implements CommandLineRunner{

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonServices personServices;
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	RequestServices requestServices;
	
	public static void main(String[] args) {
		SpringApplication.run(OuvidoriaJavaApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception{
		
		String title;
		String name;
		String password;
		String description;
		int id;
		int option = 1;
		int login = 0;
		Scanner sc = new Scanner(System.in);
		Person p = null;

		while(option != 0) {
			
			try {
				System.out.println("--==--==--==--==--==--==--==--==--==--==--");
				System.out.println("----------        Login:        ----------");
				System.out.println("--==--==--==--==--==--==--==--==--==--==--");

				System.out.println("1- Register an employee");
				System.out.println("2- Register a student");
				System.out.println("3- Sign in");
				System.out.println("0- Exit");
				option = Integer.parseInt(sc.nextLine());

				if (option == 1) {
					System.out.println("Name:");
					name = sc.nextLine();
					System.out.println("Password:");
					password = sc.nextLine();
					personServices.addPerson(null, name, password, option);
					
				} else if (option == 2) {
					System.out.println("Name:");
					name = sc.nextLine();
					System.out.println("Password: ");
					password = sc.nextLine();
					personServices.addPerson(null, name, password, option);
					
				} else if (option == 3) {
					System.out.println("Login:");
					name = sc.nextLine();
					if (name.equals("0")) {
						return;
					}
					p = personServices.getByName(name);
					while (p == null) {
						System.out.println("User does not exist ");
						System.out.println("Write your login again:");
						name = sc.nextLine();
						p = personServices.getByName(name);
					}
					System.out.println("Password:");
					password = sc.nextLine();
					while (!p.getPassword().equals(password)) {
						System.out.println("Incorrect password!");
						System.out.println("Write your password again:");
						password = sc.nextLine();
					}
					option = 0;
					login = 1;
				} else if (option == 0) {
					System.out.println("Bye!");
					break;
				}
			} catch (DomainException e) {
				System.out.println("Unexpected error: " + e.getMessage());
			}catch (NumberFormatException e) {
			    System.out.println("Error: write only numbers");
			}catch (RuntimeException e) {
				System.out.println("Unexpected error");
			}
			if (login == 1) {

				System.out.println("--==--==--==--==--==--==--==--==--==--==--");
				System.out.println("---------       Welcome!        ----------");
				System.out.println("--==--==--==--==--==--==--==--==--==--==--");
			} else {
				option = 7;
			}

			
		
			while (option != 7) {
				//try {
				if (p.getAccess() == 0) {
					System.out.println("1- Register a Request");
					System.out.println("2- List Requests");
					System.out.println("7- Exit");
					option = Integer.parseInt(sc.nextLine());
				} else if (p.getAccess() == 1) {
	
					System.out.println("2- List Requests");
					System.out.println("3- Delete Request(s)");
					System.out.println("7- Exit");
					option = Integer.parseInt(sc.nextLine());
				}
				if (option == 1) {
					if (p.getAccess() == 0) {
						System.out.println("1- Register a Praise");
						System.out.println("2- Register a Suggestion");
						System.out.println("3- Register a Complaint");
						System.out.println("0- Return");
						option = Integer.parseInt(sc.nextLine());
					}
	
					if (option == 0) {
						System.out.println("Returning to the menu...");
						System.out.println("------------------------------");
					} else if (option == 1) {
						System.out.println("Write your title: ");
						title = sc.nextLine();
						System.out.println("Write your request: ");
						description = sc.nextLine();
						requestServices.addRequest(null, null, title, description, p, option);
					} else if (option == 2) {
						System.out.println("Write your title: ");
						title = sc.nextLine();
						System.out.println("Write your request: ");
						description = sc.nextLine();
						requestServices.addRequest(null, null, title, description, p, option);
					} else if (option == 3) {
						System.out.println("Write your title: ");
						title = sc.nextLine();
						System.out.println("Write your request: ");
						description = sc.nextLine();
						requestServices.addRequest(null, null, title, description, p, option);
					}
				} else if (option == 2) {
					if (p.getAccess() == 0) {
						System.out.println("1- List Praise Request(s)");
						System.out.println("2- List Suggestion Request(s)");
						System.out.println("3- List Complaint Request(s)");
						System.out.println("6- List all Requests");
						System.out.println("0- Return");
						option = Integer.parseInt(sc.nextLine());
					} else if (p.getAccess() == 1) {
						System.out.println("1- List Praise Request(s)");
						System.out.println("2- List Suggestion Request(s)");
						System.out.println("3- List Complaint Request(s)");
						System.out.println("4- List by ID");
						System.out.println("5- List by title");
						System.out.println("6- List all requets");
						System.out.println("0- Return");
						option = Integer.parseInt(sc.nextLine());
					}
					// Cheking by type(ENUM)
					if (option == 1) {
						if (p.getAccess() == 0) {
							requestServices.listByType(RequestType.PRAISE, p);
						} else if (p.getAccess() == 1) {
							requestServices.listByType(RequestType.PRAISE, p);
						}
					}else if (option == 2) {
						if (p.getAccess() == 0) {
							requestServices.listByType(RequestType.SUGGESTION, p);
						} else if (p.getAccess() == 1) {
							requestServices.listByType(RequestType.SUGGESTION, p);
						}
					}else if (option == 3) {
						if (p.getAccess() == 0) {
							requestServices.listByType(RequestType.COMPLAINT, p);
						} else if (p.getAccess() == 1) {
							requestServices.listByType(RequestType.COMPLAINT, p);
						}
					} else if (option == 4 && p.getAccess() == 1) {
							System.out.println("Write the Request Id: ");
							id = Integer.parseInt(sc.nextLine());
							requestServices.listById(id);
					} else if (option == 5 && p.getAccess() == 1) {
							System.out.println("Write the request title: ");
							title = sc.nextLine();
							requestServices.listByTitle(title);
					} else if (option == 6) {
						if (p.getAccess() == 0) {
							requestServices.listAll(p);
						}else if (p.getAccess() == 1) {
							requestServices.listAll(p);
						}
					} else if (option == 0) {
						System.out.println("Returning to the menu...");
						System.out.println("------------------------------");
					}
	
				} else if (option == 3 && p.getAccess() == 1) {
	
					System.out.println("1- Delete one Praise Request");
					System.out.println("2- Delete one Suggestion Request");
					System.out.println("3- Delete one Complaint Request");
					System.out.println("4- Delete all");
					System.out.println("0- Return");
					option = Integer.parseInt(sc.nextLine());
					if (option == 1) {
							// deletar um elogio
						requestServices.listByType(RequestType.PRAISE, p);
						System.out.println("Choose the request Id to delete:");
						id = Integer.parseInt(sc.nextLine());
						requestServices.deleteById(id);
					}else if (option == 2) {
						// deletar uma sugestao
						requestServices.listByType(RequestType.SUGGESTION, p);
						System.out.println("Choose the request Id to delete:");
						id = Integer.parseInt(sc.nextLine());
						requestServices.deleteById(id);
					}else if (option == 3) {
						// deletar uma reclamação
						requestServices.listByType(RequestType.COMPLAINT, p);
						System.out.println("Choose the request Id to delete:");
						id = Integer.parseInt(sc.nextLine());
						requestServices.deleteById(id);
					} else if (option == 4) {
						// deletar tudo
						requestServices.deleteAll();
					} else if (option == 0) {
						System.out.println("Returning to the menu...");
						System.out.println("------------------------------");
					}
	
				} else if (option == 7) {
	//				login = 0;
					System.out.println("Returning to the login menu");
					System.out.println("------------------------------");
				}
				
	//			}catch (DomainException e) {
	//			    System.out.println("Erro: " + e.getMessage());
	//			}catch (NumberFormatException e) {
	//			    System.out.println("Erro: digite apenas números");
	//			}catch (RuntimeException e) {
	//				System.out.println("Erro indesejado");
	//			}		
		}
		}	
		sc.close();
	}
}
