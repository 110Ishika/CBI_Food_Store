package com.Anurdip.online_Food_Store.entity;

public class Food {
  public Food() {
		// TODO Auto-generated constructor stub
	}



  private String foodName;
  private double price;
  private int quantity;
  private String description;
  
public String getFoodName() {
	return foodName;
}

public void setFoodName(String foodName) {
	this.foodName = foodName;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

@Override
public String toString() {
	return "foodName= " + foodName + ", price= " + price + ", quantity= " + quantity + ", description=" + description+"\n";
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	//System.out.println(description.charAt(1));
	this.description = description;
}



public Food(String foodName, double price, int quantity, String description) {
	this.foodName = foodName;
	this.price = price;
	this.quantity = quantity;
	this.description = description;
}
  
  

}
