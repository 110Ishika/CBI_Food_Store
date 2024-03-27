package com.Anurdip.online_Food_Store.Controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.Anurdip.online_Food_Store.entity.Food;
import com.Anurdip.online_Food_Store.entity.User;
import com.Anurdip.online_Food_Store.service.AdminService;
import com.Anurdip.online_Food_Store.service.UserService;
import com.Anurdip.online_Food_Store.service.serviceImpl.AdminServiceImp;
import com.Anurdip.online_Food_Store.service.serviceImpl.UserServiceImp;

public class AdminController {

	AdminService admin = new AdminServiceImp();
	//Scanner s=new Scanner(System.in);

	public boolean authenticateAdmin(Scanner sc) {
		String email,password;
		System.out.println("Enter the email");
		email = sc.nextLine();
		if(admin.getElemnetById(email,"Admin")==true)
		{
		//System.out.println(email);
		System.out.println("Enter the password");
		password = sc.nextLine();
		//System.out.println(password);
		return admin.validateAdmin(email, password);
		}
		else
		{
		//System.out.println("Invalid Email-id ! Admin not exist");
	    return false;
		}
		
		// boolean isUserValidated=user.validateAdmin(email, password);
		

	}

	// Search a customer
	public User searchCustomerDetails(String name) {
		return admin.searchCustomerDetails(name);
	}

	// get customer details
	public List<User> getCustomerList() {
		return admin.getCustomerList();
	}
	// add Food item
	public boolean addFoodItem(Food f) {
		return admin.addFoodItem(f);

	}
    // update Food item
	public boolean updateFoodItem(Food f, String previousFoodName) {
		return admin.updateFoodItem(f,previousFoodName);
	}

	// delete Food Item
	public boolean deleteFoodItem(String f) {
		return admin.deleteFoodItem(f);
	}

	 
	 //to search Food details
	 public Food getFoodDetails(String foodName)
	 {
		 return admin.getFoodDetails(foodName);
	 }
	 
	 //Displaying all food item
	 public List<Food> getFoodList()
	 {
		 return admin.getFoodList();
	 }
}
	 
