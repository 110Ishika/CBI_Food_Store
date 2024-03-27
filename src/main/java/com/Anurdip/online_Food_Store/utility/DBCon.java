package com.Anurdip.online_Food_Store.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBCon {
	
	public static Connection establishConnection()
	{
		Connection con=null;
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("Driver Loader.....");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6534", "root", "Rootishika@1");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void main(String arg[])
	{
		if(DBCon.establishConnection()!=null)
		{
			System.out.println("Connected...");
		}
		else
		{
			System.out.println("not conncete..");
		}
		
		
	}

}
