package com.Anurdip.online_Food_Store.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import com.Anurdip.online_Food_Store.Controller.AdminController;
import com.Anurdip.online_Food_Store.Controller.UserController;
import com.Anurdip.online_Food_Store.entity.Cart;
import com.Anurdip.online_Food_Store.entity.Food;

public class UserModel {

	int option = 0;

//	{
//		displayAdminModel();
//	}
	UserModel(Scanner sc) {
		displayAdminModel(sc);
	}

	int checkIntegerInput(Scanner sc) {
		int quan;
		while (true) {
			try {
				quan = Integer.parseInt(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please  enter a available option ! ");

			}
		}
		return quan;
	}

	void displayAdminModel(Scanner sc) {
		UserController user = new UserController();
		AdminController admin = new AdminController();
		List<Cart> cartData = new ArrayList<>();
		do {
			System.out.println(
					"Enter 1 to display food items \nEnter 2  to add food in the cart \nEnter 3 to remove food from the cart \nEnter 4 to place order \nEnter 5 to exit");

			while (true) {
				try {
					option = Integer.parseInt(sc.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("only Enter the available options !");
				}
			}
			switch (option) {
			case 1:
				List<Food> listOfFood = user.getAllFood();
				if (listOfFood.size() != 0) {
					Food food;
					System.out.println("Total " + listOfFood.size() + " Available food items");
					ListIterator<Food> lt = listOfFood.listIterator();
					while (lt.hasNext()) {
						food = lt.next();
						System.out.println("Food Name: " + food.getFoodName() + "\nFood Price: " + food.getPrice()
								+ "\nFood Quantity: " + food.getQuantity() + "\nFood Description: "
								+ food.getDescription());
						System.out.println("----------------------------------------------");
					}
				}
				break;
			case 2:
				System.out.println("Enter the food item to add in cart");
				List<Food> cart = new ArrayList<Food>();
				System.out.println("Alert: Kindly type done we are done with your cart work!");
				String cartFood = "-1";
				String choice;
				do {
					// System.out.println("heelo ");
					Food cFood = new Food();
					System.out.println("Food name: ");
					cartFood = sc.nextLine();
					if (admin.getFoodDetails(cartFood).getFoodName() != null) {
						cFood.setFoodName(cartFood);
						if (admin.getFoodDetails(cartFood).getQuantity() > 0) {
							System.out.println("quantity: ");
							int quan = checkIntegerInput(sc);
							int avlquan = admin.getFoodDetails(cartFood).getQuantity();
							if (admin.getFoodDetails(cartFood).getQuantity() >= quan) {
								cFood.setQuantity(quan);
								cart.add(cFood);
							} else {
								System.out.println("item out of stock only" + avlquan
										+ " item is available \nwould u like to add the available stock Type:yes");
								String c = sc.nextLine();
								if (c.equalsIgnoreCase("yes")) {
									cFood.setQuantity(avlquan);
									cart.add(cFood);
								} else {
									System.out.println("please have our other available item!");
								}
							}
						} else {
							System.out.println("item out of stock");
						}
					} else
						System.out.println("item not exist");
					System.out.println("do u want to continue");
					choice = sc.nextLine();
				} while (choice.equalsIgnoreCase("Yes"));
				if (user.addToCart(cart)) {
					System.out.println("item successfully added to cart");
				}

				break;
			case 3:
				List<String> removeCart = new ArrayList<>();

				cartData = user.getUserCartDetails();
				if (cartData.isEmpty()) {
					System.out.println("Your cart is empty");
				} else {
					System.out.println(cartData.get(0).getUserName()
							+ " welcome to your cart: \n---------------------------------------------");
					for (Cart c : cartData) {
						System.out.println("Food Name: " + c.getFoodName() + "  Quantity: " + c.getQuantity());
					}
					System.out.println("--------------------------------------------------");
					do {
						boolean flag = false;
						System.out.println("enter the item to remove");
						String name = sc.nextLine();
						for (Cart c : cartData) {
							if (c.getFoodName().equalsIgnoreCase(name)) {
								flag = true;
								removeCart.add(name);
							}
						}
						if (flag == false) {
							System.out.println("Item not exist....");
						}

						System.out.println("do u want to continue");
						choice = sc.nextLine();
					} while (choice.equalsIgnoreCase("Yes"));
					if (user.removeFromCart(removeCart)) {
						System.out.println("item successfully removed");
					}

					cartData.clear();
				}
				break;
			case 4:
				List<Food> listsOfFoodToOrder = new ArrayList<>();
				cartData = user.getUserCartDetails();
				String name;int q=0;
				//adding carts order================================
				if (!cartData.isEmpty()) {
					name = cartData.get(0).getUserName();
					System.out.println("Hey " + name + " ! you have " + cartData.size()
								+ " items in Your cart\nTo confirm (yes/no) .");
						System.out.println(
								"----------------------------------------------------------------------------");
						for (Cart c : cartData) {
							q = admin.getFoodDetails(c.getFoodName()).getQuantity();
							if (c.getQuantity() > q)
								System.out.println("Food Name: " + c.getFoodName() + "  Quantity: " + c.getQuantity()
										+ " out of stock available stock: " + q);
							else
								System.out.println("Food Name: " + c.getFoodName() + "  Quantity: " + c.getQuantity());
						}
						System.out.println("------------------------------------------------------------------");
						String c = sc.nextLine();
						if (c.equalsIgnoreCase("yes")) {
							List<String> removeItems=new ArrayList<>();
							for (Cart ct : cartData) {
								Food orderFood = new Food();
								name = ct.getFoodName();
								orderFood.setFoodName(name);
								int qnt = ct.getQuantity();
								if(qnt>q)
								{
							      qnt=q;
								  orderFood.setQuantity(qnt);
				                  }
								else
								{
									orderFood.setQuantity(qnt);	
								}
								user.updateFoodQuantity(name,q-qnt);
								listsOfFoodToOrder.add(orderFood);
								removeItems.add(name);
								
							}
							user.removeFromCart(removeItems);
							removeItems.clear();

						}
					
				}
				
				// adding new orders to place order===========================
			String ch;
				do {
                    Food orderFood = new Food();
					System.out.println("Enter the food item: ");
					name = sc.nextLine();
					if(admin.getFoodDetails(name)!=null) {
					orderFood.setFoodName(name);
					System.out.println("Quantity: ");
					q = checkIntegerInput(sc);
					int curqnt=admin.getFoodDetails(name).getQuantity();
                    if (curqnt>=q)
					{
						orderFood.setQuantity(q);	
					}
                  else
                  {
                	  System.out.println("food out of stock! only "+ curqnt+ " quantity is left ! \ncontinue with available quantity(yes/no)");
                	  String chce=sc.nextLine();
                	  if(chce.equalsIgnoreCase("yes"))
                	  {
                			orderFood.setQuantity(curqnt);	  
                	  }
                      }
                     listsOfFoodToOrder.add(orderFood);
					}else
					{
						System.out.println("Item not exist...");
					}
                   System.out.println("want to add more type:yes");
					ch = sc.nextLine();
                     }while (ch.equalsIgnoreCase("yes"));

	              if (user.placeOrder(listsOfFoodToOrder)) {
					System.out.println("order Placed Successfully");
				} else {
					System.out.println("order failed");
				}
	              
	              cartData.clear();
	              
				/*
				 * else {
				 * 
				 * String ch; do { Food orderFood = new Food();
				 * System.out.println("Enter the food item: "); name = sc.nextLine();
				 * orderFood.setFoodName(name); System.out.println("Quantity: "); int q =
				 * checkIntegerInput(sc); orderFood.setQuantity(q);
				 * System.out.println("do u cotinue"); ch = sc.nextLine();
				 * listsOfFoodToOrder.add(orderFood);
				 * 
				 * } while (ch.equalsIgnoreCase("yes"));
				 * 
				 * }
				 */

				break;
			case 5:
				System.out.println("Exiting  from customer Section means \nLogged-out..........\n******************");
				break;

			default:
				System.out.println("invalid input");
			}

		} while (option != 6);

	}
}
