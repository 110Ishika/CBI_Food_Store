package com.Anurdip.online_Food_Store.service.serviceImpl;

import java.util.List;

import com.Anurdip.online_Food_Store.dao.daoImp.UserDaoImp;
import com.Anurdip.online_Food_Store.entity.Cart;
import com.Anurdip.online_Food_Store.entity.Food;
import com.Anurdip.online_Food_Store.service.UserService;

public class UserServiceImp implements UserService{
	
	UserDaoImp user=new UserDaoImp();

	
	@Override
	public boolean validateAdmin(String email, String password) {
		
		return user.fatchUserRecord("user", email, password);
	}

	@Override
	public List<Food> getAllFood() {
		return user.getAllFood();
	}

	@Override
	public boolean addToCart(List<Food> cartFood,String cartUser) {
				return user.addToCart(cartFood,cartUser);
	}

	@Override
	public boolean removeItemFromCart(List<String> removeFood,String name) {
		
		return user.removeItemFromCart(removeFood,name);
	}

	@Override
	public boolean placeOrder(List<Food> f,String email) {
		
		return user.placeOrder(f,email);
	}

	@Override
	public boolean getUserById(String email, String role) {
		return new UserDaoImp().getUserById(email,role) ;
	}

	
	public List<Cart> getUserCartDetails(String email)
	{
		return user.getUserCartDetails(email);
		
	}
	
	
	
}
