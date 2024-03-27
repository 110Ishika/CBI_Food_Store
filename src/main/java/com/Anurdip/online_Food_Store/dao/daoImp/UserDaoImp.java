package com.Anurdip.online_Food_Store.dao.daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Anurdip.online_Food_Store.dao.UserDao;
import com.Anurdip.online_Food_Store.entity.Cart;
import com.Anurdip.online_Food_Store.entity.Food;
import com.Anurdip.online_Food_Store.utility.DBCon;

public class UserDaoImp implements UserDao {

	List<Cart> cartList = new ArrayList<>();
	AdminDaoImp admin = new AdminDaoImp();

	@Override
	public boolean fatchUserRecord(String role, String email, String password) {
		try {
			Connection con = DBCon.establishConnection();
			PreparedStatement ps = con.prepareStatement("Select email,password from tbl_user where email=? and role=?");
			ps.setString(1, email);
			ps.setString(2, "User");
			ResultSet st = ps.executeQuery();
			if (st.next()) {
				String mail = st.getString(1);
				String p = st.getString(2);
				if (mail.equals(email) && (p.equals(password)))
					return true;
				else
					return false;
			}
		} catch (Exception e) {
			System.out.println("Sql error");
		}

		return false;
	}

	@Override
	public List<Food> getAllFood() {
		List<Food> list = new ArrayList<Food>();

		try {
			Connection con = DBCon.establishConnection();
			PreparedStatement ps = con.prepareStatement("Select * from tbl_food");
			ResultSet st = ps.executeQuery();
			while (st.next()) {
				Food f = new Food();
				f.setFoodName(st.getString(2));
				f.setPrice(st.getDouble(3));
				f.setQuantity(st.getInt(4));
				f.setDescription(st.getString(5));
				list.add(f);
			}
		} catch (Exception e) {
			System.out.println("error in sql");
		}
		return list;
	}

	// to get Food Id
	public int getFoodId(String foodName) {
		try {
			Connection con = DBCon.establishConnection();
			PreparedStatement ps = con.prepareStatement("select food_id from tbl_food where food_name=?");
			ps.setString(1, foodName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

			else {
				return -1;
			}

		} catch (Exception e) {
			System.out.println("error in sql");
		}

		return -1;

	}

	// to get the user
	public int getUserId(String name) {

		try {
			Connection con = DBCon.establishConnection();
			PreparedStatement ps = con.prepareStatement("select user_id from tbl_user where email=? ");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception e) {

		}
		return -1;
	}

	@Override
	public boolean addToCart(List<Food> food, String user) {

		try {
			int flag = -1;
			Connection con = DBCon.establishConnection();
			for (Food f : food) {
				int foodId = getFoodId(f.getFoodName());
				System.out.println(foodId);
				int userId = getUserId(user);
				if ((foodId != -1) && (userId != -1)) {
					PreparedStatement ps = con
							.prepareStatement("insert into tbl_cart(food_id,user_id,quantity) values(?,?,?)");
					ps.setInt(1, foodId);
					ps.setInt(2, userId);
					ps.setInt(3, f.getQuantity());
					int row = ps.executeUpdate();
					if (row != 0) {
						flag = 1;
					}
				}
			}
			if (flag == 1)
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println("error in sql");
		}

		return false;
	}

	@Override
	public boolean removeItemFromCart(List<String> removeFood, String name) {
		try {
			int flag = -1;
			Connection con = DBCon.establishConnection();
			for (String item : removeFood) {
				int foodId = getFoodId(item);
				if (getFoodId(item) != -1) {
					PreparedStatement ps = con.prepareStatement("delete from tbl_cart where food_id=?");
					ps.setInt(1, foodId);
					int row = ps.executeUpdate();
					if (row != 0) {
						flag = 1;
						//System.out.println("item deleted");// delete for that particular user
					}
				}
			}
			if (flag == 1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("error in sql");
		}
		return false;

	}

	@Override
	public boolean placeOrder(List<Food> f, String email) {
		try {
			Connection con = DBCon.establishConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into tbl_order(food_id,user_id,quantity,total_price,order_date,order_status) values(?,?,?,?,?,?)");
			int count = 0;
			// System.out.println("hello");

			int userId = getUserId(email);
			ps.setInt(2, userId);
			double price = 0, netPrice = 0;
			if (f.size() > 0) {
				for (Food food : f) {
					int foodId = getFoodId(food.getFoodName());
					ps.setInt(1, foodId);
					ps.setInt(3, food.getQuantity());
					int quant = getFoodDetails(food.getFoodName()).getQuantity() - food.getQuantity();
					admin.updateFoodQuantity(food.getFoodName(), quant);
					price = getFoodDetails(food.getFoodName()).getPrice();
					netPrice = price * food.getQuantity();
					ps.setDouble(4, netPrice);
					java.sql.Date date = getCurrentDatetime();
					ps.setDate(5, date);
					ps.setInt(6, 1);
					int row = ps.executeUpdate();
					if (row > 0) {
						count++;
					}
				}
				if (count== f.size()) 
					return true;
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private Date getCurrentDatetime() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());

	}

	@Override
	public boolean getUserById(String email, String role) {

		try {
			Connection con = DBCon.establishConnection();
			PreparedStatement ps = con.prepareStatement("select email,role from tbl_user where email=? and role=?");
			ps.setString(1, email);
			ps.setString(2, role);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == true) {
				// System.out.println("correct");
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error in sql syntax");
		}

		return false;
	}

	@Override
	public Food getFoodDetails(String foodName) {
		Food f = new Food();
		try {
			Connection con = DBCon.establishConnection();
			PreparedStatement ps = con.prepareStatement("select *  from tbl_food where food_name=?");
			ps.setString(1, foodName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				f.setFoodName(rs.getString(2));
				f.setPrice(rs.getDouble(3));
				f.setQuantity(rs.getInt(4));
				// f.setDescription(rs.getString(5));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

//to get the cart details of a person

	public List<Cart> getUserCartDetails(String email) {
		try {
			
			Connection con = DBCon.establishConnection();
			int userId = getUserId(email);
			PreparedStatement ps = con.prepareStatement(
					"select u.first_name, u.last_name,f.food_name,c.quantity from tbl_user u, tbl_food f,tbl_cart c where u.user_id=c.user_id and c.food_id=f.food_id and u.user_id=?");
			// PreparedStatement ps = con.prepareStatement("select * from tbl_cart where
			// user_id=?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cart c = new Cart();
				c.setUserName(rs.getString(1) + " " + rs.getString(2));
				c.setFoodName(rs.getString(3));
				c.setQuantity(rs.getInt(4));
				cartList.add(c);
			}

		} catch (Exception e) {
			System.out.println("error in sql");
		}
//System.out.println(cartList);
		return cartList;
	}
}
