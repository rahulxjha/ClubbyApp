package com.clubby.clubbyApp;

import com.clubby.clubbyApp.dao.AdminDAO;
import com.clubby.clubbyApp.dao.VisitorDAO;
import com.clubby.clubbyApp.entity.Admin;
import com.clubby.clubbyApp.entity.Visitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class ClubbyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubbyAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AdminDAO adminDAO, VisitorDAO visitorDAO){
		return runner ->{
			Scanner scn = new Scanner(System.in);
			System.out.println("Are you a Admin or a User?\nPress 1 for Admin\nPress 2 for User");
			int role = scn.nextInt();

			switch (role) {
				case 1 :
					System.out.println("Enter your login credentials..");
					String userName = scn.next();
					String password = scn.next();
					adminDAO.adminLogin(userName, password);
					System.out.println("Login Successful");

					System.out.println("Choose correct task...\n" +
							"1. Find Visitor By id\n" +
							"2. Find all registered Visitor\n" +
							"3. Find by last name\n" +
							"4. Delete registered User\n" +
							"5. Delete all registered user "
					);
					int task = scn.nextInt();

					switch (task) {
						case 1 :
							System.out.println("Enter Id");
							int id = scn.nextInt();
							System.out.println(adminDAO.findById(id));
							break;

						case 2 :
							adminDAO.findAll();
							break;

						case 3 :
							System.out.println("Enter last name");
							String lastName = scn.next();
							adminDAO.findByLastName(lastName);
							break;

						case 4 :
							System.out.println("Enter id");
							int deleteId = scn.nextInt();
							adminDAO.delete(deleteId);
							break;

						case 5 :
							adminDAO.deleteAll();
							break;
					}

				break;

				case 2 :
					System.out.println("Welcome to the Clubby: The leading club in your city");
					System.out.println("If you are a first time user then start by registering your details.\n" +
							"Press 1 for register otherwise choose from following :\n" +
							"2. Find your details by using id.\n" +
							"3. Update your all details.\n" +
							"4. Update your email.\n" +
							"5. Update your Password.");
					int choice = scn.nextInt();
					switch (choice) {
						case 1 :
							Visitor visitor = new Visitor();
							System.out.println("Enter your first name");
							String firstName = scn.next();
							visitor.setName(firstName);

							System.out.println("Enter your last name");
							String lastName = scn.next();
							visitor.setLastName(lastName);

							System.out.println("Enter your email");
							String email = scn.next();
							visitor.setEmail(email);

							System.out.println("Set a password");
							String userPassword = scn.next();
							visitor.setPassword(userPassword);

							System.out.println("Enter your phone number");
							String phoneNum = scn.next();
							visitor.setPhoneNum(phoneNum);

							System.out.println("Enter your gender");
							String gender = scn.next();
							visitor.setGender(gender);

							System.out.println("Enter your date of birth");
							String dob = scn.next();
							visitor.setDateOfBirth(dob);

							System.out.println("Enter your age");
							int age = scn.nextInt();
							visitor.setAge(age);

							System.out.println("Enter your address");
							String address = scn.nextLine();
							visitor.setAddress(address);

							visitorDAO.save(visitor);

							System.out.println("Registered Successfully");
							break;

						case 2 :
							System.out.println("Enter your id");
							int id = scn.nextInt();
							visitorDAO.findById(id);
					}

			}
		};
	}



}
