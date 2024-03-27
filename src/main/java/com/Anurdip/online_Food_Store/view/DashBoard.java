package com.Anurdip.online_Food_Store.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Anurdip.online_Food_Store.Controller.AdminController;
import com.Anurdip.online_Food_Store.Controller.UserController;


public class DashBoard {
	
	

	// methods for dashboard
	private void projectDashBoard() {
		
		Scanner sc = new Scanner(System.in);
		int option = 0;
       do {
			
			try { 
				
				System.out.println("Welcome to DashBoard");
				System.out.println("========================");
				System.out.println("Enter 1 to Admin login\n"
						+ "Enter 2 to User Login\n"
						+ "Enter 3 to Create Account and User Management\n"
						+ "Enter 4 exit");
				
				while (true) {
					try {
						option = Integer.parseInt(sc.nextLine());
						break;
					} catch (NumberFormatException e) {
						System.out.println("Please  enter a available option ! ");
						
					}
				}
				switch (option) {
				case 1:
					
					boolean authenticateAdmin = new AdminController().authenticateAdmin(sc);
					if (authenticateAdmin)
					new AdminModel(sc);
					else
						System.out.println("Invalid Email-id ! Admin not exist");
					break;

				case 2:
				{
				    UserController u = new UserController();
					boolean authenticateUser = u.authenticateUser(sc);
					if (authenticateUser)
						new UserModel(sc);
					else
						System.out.println("Invalid User-id/password ! try again");
					break;
				}
					
				case 3:
					boolean checkUserManagement = true;
					if (checkUserManagement) {
						do {
							System.out.println(
									"Enter 1 to Create User Account\n"
									+ "Enter 2 to Update User\n"
									+ "Enter 3 to Delete User\n"
									+ "Enter 4 to exit");
							
							option = sc.nextInt();
							switch (option) {
							case 1:
								System.out.println("Create User Account");
								break;
							case 2:
								System.out.println("Update User");
								break;
							case 3:
								System.out.println("Delete User");
								break;
							case 4:
								System.out.println("Exit from user Section\n****************");
								break;

							default:
								System.out.println("invalid input");
							}
						} while (option != 4);
					}
					
					break;
				case 4:
					System.out.println("Exited from the Deshboard.........\nThanks for your visit");
					break;
				default:
					System.out.println("Please enter the available options !");
					break;
				}
			} catch (Exception e) {
				System.out.println("Something went wrong! ");
				// projectDashBoard();
			}

		} while (option != 4);

	}

	// main methods
	public static void main(String arg[]) {
		DashBoard d = new DashBoard();
		d.projectDashBoard();

	}
}
