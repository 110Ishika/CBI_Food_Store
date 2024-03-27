package com.Anurdip.online_Food_Store.dao;

import java.util.List;

import com.Anurdip.online_Food_Store.entity.Food;

public interface UserDao {

	// fetch User for validation
	public boolean fatchUserRecord(String role, String email, String password);

	// to display all food item
	public List<Food> getAllFood();

	// add food to cart
	public boolean addToCart(List<Food> cartFood,String user);
	
  
	// to remove Food item
	public boolean removeItemFromCart(List<String> food,String email);
	
	
	//to get the cartDetails of a particular user
	//public 
	

	// place order
	public boolean placeOrder(List<Food> f,String email);
	
	//getUserById()
	public boolean getUserById(String email,String role);
	
	 //to search Food details
	 public Food getFoodDetails(String foodName);

}
