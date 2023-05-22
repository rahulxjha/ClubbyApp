package com.clubby.clubbyApp;

import com.clubby.clubbyApp.dao.AdminDAO;
import com.clubby.clubbyApp.dao.VisitorDAO;
import com.clubby.clubbyApp.entity.Visitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
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
							List<Visitor> Visitor = adminDAO.findAll();
							for (Visitor visitor: Visitor ) {
								System.out.println(visitor);
							}
							break;

						case 3 :
							System.out.println("Enter last name");
							String lastName = scn.next();
							List<Visitor> visitorByName = adminDAO.findByLastName(lastName);
							for (Visitor visitor: visitorByName ) {
								System.out.println(visitor);
							}
							break;

						case 4 :
							System.out.println("Enter id");
							int deleteId = scn.nextInt();
							adminDAO.delete(deleteId);
							break;

						case 5 :
							int numRowsDeleted = adminDAO.deleteAll();
							System.out.println(numRowsDeleted + " rows of data deleted.");
							break;
					}

				break;

				case 2 :
					System.out.println("Welcome to the Clubby: The leading club in your city");
					System.out.println("If you are a first time user then start by registering your details.\n" +
							"Press 1 for register otherwise choose from following by entering your id :\n" +
							"2. Find your details by using id.\n" +
							"3. Update your email.\n" +
							"4. Update your Password.\n" +
							"5. Update your Phone Number.\n" +
							"6. Delete your details");
					int choice = scn.nextInt();
					System.out.println("Enter your id");
					int id = scn.nextInt();
					Visitor visitor;
					switch (choice) {
						case 1 :
							Visitor newVisitor = new Visitor();
							System.out.println("Enter your first name");
							String firstName = scn.next();
							newVisitor.setName(firstName);

							System.out.println("Enter your last name");
							String lastName = scn.next();
							newVisitor.setLastName(lastName);

							System.out.println("Enter your email");
							String email = scn.next();
							newVisitor.setEmail(email);

							System.out.println("Set a password");
							String userPassword = scn.next();
							newVisitor.setPassword(userPassword);

							System.out.println("Enter your phone number");
							String phoneNum = scn.next();
							newVisitor.setPhoneNum(phoneNum);

							System.out.println("Enter your gender");
							String gender = scn.next();
							newVisitor.setGender(gender);

							System.out.println("Enter your date of birth");
							String dob = scn.next();
							newVisitor.setDateOfBirth(dob);

							System.out.println("Enter your age");
							int age = scn.nextInt();
							newVisitor.setAge(age);

							System.out.println("Enter your address");
							String address = scn.nextLine();
							newVisitor.setAddress(address);

							visitorDAO.save(newVisitor);

							System.out.println("Registered Successfully");
							break;

						case 2 :
							visitor = visitorDAO.findById(id);
							System.out.println(visitor);
							break;

						case 3 :
							visitor = visitorDAO.findById(id);
							System.out.println("Previous Details : "+ visitor);

							System.out.println("Enter your updated email");
							String updateEmail = scn.next();
							visitor.setEmail(updateEmail);
							System.out.println("Updated Details : "+ visitor);
							break;

						case 4 :
							visitor = visitorDAO.findById(id);
							System.out.println("Previous Details : "+ visitor);

							System.out.println("Enter your new password");
							String updatePassword = scn.next();
							visitor.setPassword(updatePassword);
							System.out.println("Updated details" + visitor);
							break;

						case 5 :
							visitor = visitorDAO.findById(id);
							System.out.println("Previous Details : "+ visitor);

							System.out.println("Enter new phone number to update");
							String updatePhoneNum = scn.next();
							visitor.setPhoneNum(updatePhoneNum);

							System.out.println("Updated details: "+ visitor);
							break;

						case 6 :
							visitorDAO.deleteYourDetails(id);
							System.out.println("Successfully Deleted");
							break;
					}
					break;
			}
		};
	}


	private void createVisitor(VisitorDAO visitorDAO){
		Visitor visitor = new Visitor("Rahul", "Jha", "rahul@gmail.com", "rahul", "9876543210", "male", "22-5-2000",
				23, "Jaipur");
		Visitor visitor1 = new Visitor("Rakesh", "Kumar", "raka@gmail.com", "raka", "9875445210", "male", "2-3-2001",
				22, "Jaipur");
		Visitor visitor2 = new Visitor("Ram", "Kumar", "ram@gmail.com", "ram", "9878543210", "male", "2-5-2000",
				23, "Jaipur");


		visitorDAO.save(visitor);
		visitorDAO.save(visitor1);
		visitorDAO.save(visitor2);
	}

}
