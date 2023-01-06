package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider 
{
Connection con;
	

	public Connection getCon()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root", "Vishal#4646");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}

}
