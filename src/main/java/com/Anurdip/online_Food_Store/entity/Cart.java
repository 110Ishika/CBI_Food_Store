package com.Anurdip.online_Food_Store.entity;

public class Cart {
	String foodName;
	String userName;
	String email;
	int quantity;
	public Cart(String foodName, String userName, String email, int quantity) {
		super();
		this.foodName = foodName;
		this.userName = userName;
		this.email = email;
		this.quantity = quantity;
	}
	public Cart()
	{
		
	}

	public Cart(String foodName, String userName, int quantity) {
		this.foodName = foodName;
		this.userName = userName;
		this.quantity = quantity;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getFoodName() {
		return foodName;
	}


	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Cart [foodName=" + foodName + ", userName=" + userName + ", email=" + email + ", quantity=" + quantity
				+ "]";
	}
	
	

}
