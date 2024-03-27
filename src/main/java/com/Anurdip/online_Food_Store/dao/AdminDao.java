package com.Anurdip.online_Food_Store.dao;

import java.util.List;

import com.Anurdip.online_Food_Store.entity.Food;
import com.Anurdip.online_Food_Store.entity.User;

public interface AdminDao {
	//fetch and validate the admin
	public boolean fatchRecord(String role,String email,String password);
	
	//get elementById
	public boolean getElemnetById(String email,String role);
	
	//to search Admin Details
	public User searchAdminDetails(String mail,String role);
	
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
		 
		 //Displaying all food item
		 public List<Food> getFoodList();
		 
		 
		 public boolean updateFoodQuantity(String food, int quantity);
		 

}
