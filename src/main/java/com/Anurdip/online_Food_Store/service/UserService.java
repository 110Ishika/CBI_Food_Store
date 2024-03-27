package com.Anurdip.online_Food_Store.service;

import java.util.List;

import com.Anurdip.online_Food_Store.entity.Cart;
import com.Anurdip.online_Food_Store.entity.Food;

public interface UserService {

	//to validate User
	public boolean validateAdmin(String email, String password);
	
	//to display all food item
	public List<Food> getAllFood();
	
	//add food to cart
	public boolean addToCart(List<Food> cartFood,String cartUser);

	
	//to remove Food item
	public boolean removeItemFromCart(List<String> foodRemove,String email);
	
	//place order
	public boolean placeOrder(List<Food> f, String email);
	
	//getUserById()
	public boolean getUserById(String email,String role);

    // to get item in cart of a user
	public List<Cart> getUserCartDetails(String email);
	

	
}
