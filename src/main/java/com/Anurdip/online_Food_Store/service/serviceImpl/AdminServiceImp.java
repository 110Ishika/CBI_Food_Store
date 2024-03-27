package com.Anurdip.online_Food_Store.service.serviceImpl;

import java.util.List;

import com.Anurdip.online_Food_Store.dao.AdminDao;
import com.Anurdip.online_Food_Store.dao.daoImp.AdminDaoImp;
import com.Anurdip.online_Food_Store.entity.Food;
import com.Anurdip.online_Food_Store.entity.User;
import com.Anurdip.online_Food_Store.service.AdminService;

public class AdminServiceImp implements AdminService {

	AdminDao admin=new AdminDaoImp();
	@Override
	public boolean validateAdmin(String email,String password) {
		
		return admin.fatchRecord("Admin",email,password);
	}
	
	@Override
	public User searchCustomerDetails(String email) {
		
		return admin.searchAdminDetails(email,"User");
	}
	@Override
	public List<User> getCustomerList() {
		return admin.getCustomerList();
	}
	@Override
	public boolean addFoodItem(Food f) {
		
		return admin.addFoodItem(f);
	}
	@Override
	public boolean updateFoodItem(Food f,String previousFoodName) {
		
		return admin.updateFoodItem(f,previousFoodName);
	}
	@Override
	public boolean deleteFoodItem(String f) {
		return admin.deleteFoodItem(f);
	}
	@Override
	public Food getFoodDetails(String foodName) {
		
		return admin.getFoodDetails(foodName);
	}

	@Override
	public List<Food> getFoodList() {
		return admin.getFoodList();
	}

	@Override
	public boolean getElemnetById(String email, String role) {
		
		return admin.getElemnetById(email,role);
	}

	
	public boolean updateFoodQuantity(String food,int quantity)
	{
		return admin.updateFoodQuantity(food, quantity);
	}
	
}
