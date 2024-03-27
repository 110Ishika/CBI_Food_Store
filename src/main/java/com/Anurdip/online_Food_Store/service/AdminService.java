package com.Anurdip.online_Food_Store.service;

import java.util.List;

import com.Anurdip.online_Food_Store.entity.Food;
import com.Anurdip.online_Food_Store.entity.User;

public interface AdminService {
	
	 //to validate the admin user
	 public boolean validateAdmin(String email,String password);
	
	 //validate admin by id
	 public boolean getElemnetById(String email,String role);
	 
	 
     //to search any customer
	 public User searchCustomerDetails(String email);
	 
	 //get customer details
	 public List<User> getCustomerList();
	 
	 //add Food item
	 public boolean addFoodItem(Food f);
	 
	 //update Food item
	 public boolean updateFoodItem(Food f,String previousFoodName);
	 
	 //delete Food Item
	 public boolean deleteFoodItem(String f);
	 
	 //to search Food details
	 public Food getFoodDetails(String foodName);
	 
	 //displaying all food item
	 public List<Food> getFoodList();
	 
}
