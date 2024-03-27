package com.Anurdip.online_Food_Store.Controller;

import java.util.List;
import java.util.Scanner;

import com.Anurdip.online_Food_Store.entity.Cart;
import com.Anurdip.online_Food_Store.entity.Food;
import com.Anurdip.online_Food_Store.service.AdminService;
import com.Anurdip.online_Food_Store.service.UserService;
import com.Anurdip.online_Food_Store.service.serviceImpl.AdminServiceImp;
import com.Anurdip.online_Food_Store.service.serviceImpl.UserServiceImp;

public class UserController {
	UserService user = new UserServiceImp();
	AdminServiceImp admin = new AdminServiceImp();
	static String email = null;

	public UserController() {

	}

	// Scanner s=new Scanner(System.in);

	public boolean authenticateUser(Scanner sc) {
		System.out.println("Enter the your email");

		email = sc.nextLine();
		if (user.getUserById(email, "User") == true) {
			System.out.println("Enter the password");
			String password = sc.nextLine();
			// boolean isUserValidated=user.validateAdmin(email, password);
			return user.validateAdmin(email, password);
		} else {
			return false;
			// System.out.println("Invalid Email-id ! user not Exist");
		}

	}

	public List<Food> getAllFood() {
		return user.getAllFood();
	}

	public boolean addToCart(List<Food> cartFood) {
		// System.out.println(email);
		return user.addToCart(cartFood, email);
	}

	public boolean removeFromCart(List<String> removeCart) {

		return user.removeItemFromCart(removeCart, email);
	}

	public List<Cart> getUserCartDetails() {
		return user.getUserCartDetails(email);

	}

	public boolean updateFoodQuantity(String food,int quantity)
	{
		return admin.updateFoodQuantity(food,quantity);
		
	}
	
	public boolean placeOrder(List<Food> listOfFoodToOrder)
	{
		return user.placeOrder(listOfFoodToOrder,email);
	}

}
