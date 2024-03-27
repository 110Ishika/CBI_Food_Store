package com.Anurdip.online_Food_Store.dao.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Anurdip.online_Food_Store.dao.AdminDao;
import com.Anurdip.online_Food_Store.entity.Food;
import com.Anurdip.online_Food_Store.entity.User;
import com.Anurdip.online_Food_Store.utility.DBCon;

public class AdminDaoImp implements AdminDao {
	private Connection con = null;
	private PreparedStatement ps = null;

	@Override
	public boolean fatchRecord(String role, String email, String password) {
		try {
			con = DBCon.establishConnection();
			ps = con.prepareStatement("Select email,password from tbl_user where email=? and role=?");
			ps.setString(1, email);
			ps.setString(2, role);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String mail = rs.getString(1);
				String psw = rs.getString(2);
				if (mail.equals(email) && (psw.equals(password)))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((con != null) && (ps != null)) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public User searchAdminDetails(String email, String role) {
		User u = new User();

		try {
			con = DBCon.establishConnection();
			ps = con.prepareStatement("Select * from tbl_user where email=? and role=role");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setAddress(rs.getString(6));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((con != null) && (ps != null)) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return u;
	}

	@Override
	public List<User> getCustomerList() {
		List<User> ul = new ArrayList<>();
		try {
			con = DBCon.establishConnection();
			ps = con.prepareStatement("Select * from tbl_user where role=?");
			ps.setString(1, "User");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setAddress(rs.getString(6));
				ul.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((con != null) && (ps != null)) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ul;
	}

	@Override
	public boolean addFoodItem(Food f) {

		try {
			con = DBCon.establishConnection();
			ps = con.prepareStatement("insert into tbl_food(food_name,price,quantity,description)values(?,?,?,?)");
			ps.setString(1, f.getFoodName());
			ps.setDouble(2, f.getPrice());
			ps.setDouble(3, f.getQuantity());
			ps.setString(4, f.getDescription());
			int row = ps.executeUpdate();
			if (row != 0)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((con != null) && (ps != null)) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateFoodItem(Food f,String previousFoodName) {
		try {
			con = DBCon.establishConnection();
			ps = con.prepareStatement("update  tbl_food set price=?,quantity=?,description=?,food_name=? where food_name=? ");

			ps.setDouble(1, f.getPrice());
			ps.setDouble(2, f.getQuantity());
			ps.setString(3, f.getDescription());
			ps.setString(4, f.getFoodName());
			ps.setString(5, previousFoodName );
			int row = ps.executeUpdate();
			if (row != 0)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((con != null) && (ps != null)) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	@Override
	public boolean deleteFoodItem(String f) {
		try {
			con = DBCon.establishConnection();
			ps = con.prepareStatement("delete from tbl_food where food_name=?");
			ps.setString(1, f);
			int row = ps.executeUpdate();
			if (row != 0)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((con != null) && (ps != null)) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Food getFoodDetails(String foodName) {
		Food f = new Food();
		try {
			con = DBCon.establishConnection();
			ps = con.prepareStatement("select *  from tbl_food where food_name=?");
			ps.setString(1, foodName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				f.setFoodName(rs.getString(2));
				f.setPrice(rs.getDouble(3));
				f.setQuantity(rs.getInt(4));
				// f.setDescription(rs.getString(5));

			}
			else
			{
				f=null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((con != null) && (ps != null)) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return f;
	}

	@Override
	public List<Food> getFoodList() {
		List<Food> foodList = new ArrayList<>();
		try {

			con = DBCon.establishConnection();
			ps = con.prepareStatement("select *  from tbl_food");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Food f = new Food();
				f.setFoodName(rs.getString(2));
				f.setPrice(rs.getDouble(3));
				f.setQuantity(rs.getInt(4));
				f.setDescription(rs.getString(5));
				foodList.add(f);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((con != null) && (ps != null)) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return foodList;
	}

	@Override
	public boolean getElemnetById(String email, String role) {
		try {
			con = DBCon.establishConnection();
			ps = con.prepareStatement("Select email from tbl_user where email=? and role=?");
			ps.setString(1, email);
			ps.setString(2, role);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String mail = rs.getString(1);

				if (mail.equals(email))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((con != null) && (ps != null)) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateFoodQuantity(String food, int quantity) {
		
		try {
			con = DBCon.establishConnection();
			
			ps = con.prepareStatement("update tbl_food set quantity=? where food_name=?");
			ps.setInt(1,quantity);
			ps.setString(2,food);
			int row=ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
			
		}catch(Exception e)
		{
			System.out.println("error in updates quantity");
		}
		
		
		return false;
	}
}
