package com.Anurdip.online_Food_Store.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import com.Anurdip.online_Food_Store.Controller.AdminController;
import com.Anurdip.online_Food_Store.entity.Food;
import com.Anurdip.online_Food_Store.entity.User;

public class AdminModel {
	// Scanner s=new Scanner(System.in);
	int option = 0;

	// {
	// displayAdminModel(sc);
	// }

	// for integer input
	int checkIntegerInput(Scanner sc) {
		int q = 0;
		while (true) {
			try {
				q = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter the interger only or the available options ! ");
				sc.nextLine();
			}
		}
		return q;
	}

	// for double input
	double checkDoubleInput(Scanner sc) {
		double q = 0;
		while (true) {
			try {
				q = sc.nextDouble();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter interger/Double only! ");
				sc.nextLine();
			}
		}
		return q;
	}

	AdminModel(Scanner sc) {
		displayAdminModel(sc);
	}

	void displayAdminModel(Scanner sc) {
		AdminController a = new AdminController();
		List<User> listOfUser = new ArrayList<>();
		do {
			System.out.println("Enter 1 to Search any customer\n" + "Enter 2 to Display all customers\n"
					+ "Enter 3 to add food\n" + "Enter 4 to Update Food\n" + "Enter 5 to delete food\n"
					+ "enter 6 to search food details\n" + "enter 7 to display All food items\n" + "enter 8 to exit;");
			option = checkIntegerInput(sc);
											
			sc.nextLine();
			switch (option) {
			case 1:
				System.out.println("Enter customer name for search");
				String s = sc.next();
				User u = a.searchCustomerDetails(s);
				if (u.getEmail() != null) {
					System.out.println(u);
				} else {
					System.out.println("Invalid user");
				}
				break;
			case 2:

				listOfUser = a.getCustomerList();
				System.out.println("\n avilable customer: " + listOfUser.size());
				for (User l : listOfUser) {
					System.out.println(l);
					System.out.println("--------------------------------------");
				}
				break;
			case 3:
				Food food = new Food();
				System.out.println("Enter the details to add food items");
				System.out.println("Enter the food name");
				String name = sc.nextLine();
				food = a.getFoodDetails(name);
				if (food.getFoodName().equalsIgnoreCase(name)) {
					System.out.println("Food already Exist");
					break;
				}
				// sc.next();
				food.setFoodName(name);
				System.out.println("Enter the food Price");
				double p = checkDoubleInput(sc);
				food.setPrice(p);
				System.out.println("Enter the food Quantity");
				int q = checkIntegerInput(sc);
				food.setQuantity(q);
				System.out.println("Enter the food Description");
				sc.nextLine();
				String d = sc.nextLine();
				food.setDescription(d);
				if (a.addFoodItem(food)) {
					System.out.println("Item added");
				} else {
					System.out.println("error");
				}
				break;
			case 4:
				System.out.println("Enter details to update food\n-------------------------------------");
				System.out.println("Enter the food name");
				String n = sc.nextLine();
				food = a.getFoodDetails(n);
				if (food.getFoodName() != null) {
					System.out.println("Food Name: " + food.getFoodName() + "\nFood Price:  " + food.getPrice()
							+ "\nFood Quantity:  " + food.getQuantity());
				} else {
					System.out.println("Food not exist");
					break;
				}
				// sc.next();

				System.out.println("Enter the new food name");
				String newFood = sc.nextLine();
				System.out.println(newFood);
				food.setFoodName(newFood);
				System.out.println("Enter the food Price");
				double price = checkDoubleInput(sc);
				food.setPrice(price);
				System.out.println("Enter the food Quantity");
				int quantity = checkIntegerInput(sc);
				food.setQuantity(quantity);
				System.out.println("Enter the food Description");
				sc.nextLine();
				String des = sc.nextLine();
				food.setDescription(des);
				if (a.updateFoodItem(food, n)) {
					System.out.println("Item updated");
				} else {
					System.out.println("error");
				}
				break;
			case 5:
				System.out.println("Enter the food name u want to delete");
				String n1 = sc.nextLine();
				food = a.getFoodDetails(n1);
				if (food.getFoodName() != null) {
					
                     System.out.println("Food  Name= " + food.getFoodName() + "\nFood Price= " + food.getPrice()
							+ "\nFood quantity=" + food.getQuantity());
					System.out.println("Do you want to delete");
					String o=sc.nextLine();;
					do {
					if (o.equalsIgnoreCase("yes")) {
						if (a.deleteFoodItem(n1))
							System.out.println("item deteted");
						else
							System.out.println("error");
						break;
					} else if (o.equalsIgnoreCase("no")) {
						System.out.println("Food is saved");
						break;
					} else {
						System.out.println("You have enter a wrong value \nplease enter Yes to delete the food\n");
						o=sc.nextLine();
						}
				}while(o.equalsIgnoreCase("yes"));
				} 
				else {
					System.out.println("Food not exist");
				}
				break;
			case 6:
				System.out.println("Enter the food name");
				n1 = sc.nextLine();
				food = a.getFoodDetails(n1);
				if (food.getFoodName() != null) {
					System.out.println("Food Name: " + food.getFoodName() + "\nFood Price: " + food.getPrice()
							+ "\nFood quantity: " + food.getQuantity() + "\nFood description: "
							+ food.getDescription());
				} else {
					System.out.println("Food item not exist");
				}
				break;
			case 7:
				List<Food> listOfFood = a.getFoodList();
				System.out.println("Total " +listOfFood.size()+ " Available food items");
				ListIterator<Food> lt = listOfFood.listIterator();
				while (lt.hasNext()) {
					food = lt.next();
					System.out.println("Food Name: "+food.getFoodName()+"\nFood Price: "+food.getPrice()+"\nFood Quantity: "+ food.getQuantity()+"\nFood Description: "+food.getDescription());
					System.out.println("----------------------------------------------");
				}
				break;
			case 8:
				System.out.println("Exited from Admin Section means\nlogged-out......");
				break;
			default:
				System.out.println("invalid input");
			}

		} while (option != 8);

	}
}
