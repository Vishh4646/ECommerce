package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import com.connection.ConnectionProvider;


public class User implements userInterface
{
	ConnectionProvider connection = new ConnectionProvider();
	Connection con;
	private int uid;
	private String name;
	private String pass;
	private String contact;
	private String email;
	
	public int getUid() 
	{
		return uid;
	}

	public void setUid(int uid) 
	{
		this.uid = uid;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getPass() 
	{
		return pass;
	}

	public void setPass(String pass)
	{
		this.pass = pass;
	}

	public String getContact()
	{
		return contact;
	}

	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override
	public void registerUser() 
	{
		con=connection.getCon();
		Scanner scanner = new Scanner(System.in);
		System.out.println("-------------------------------");
		System.out.println("Enter Your Unique id: ");
		int uid=scanner.nextInt();
		setUid(uid);
		System.out.println("Enter Your Name: ");
		String name=scanner.next();
		setName(name);
		System.out.println("Enter your password for login: ");
		String pass= scanner.next();
		setPass(pass);
		System.out.println("Enter your contact no: ");
		String contact = scanner.next();
		setContact(contact);
		System.out.println("Enter your Email: ");
		String email = scanner.next();
		setEmail(email);
		try
		{
			PreparedStatement prepare=  con.prepareStatement("insert into user(uid,name, password, contact, email)values (?,?,?,?,?)");
			prepare.setInt(1, uid);		
			prepare.setString(2, name);
			prepare.setString(3, pass);
			prepare.setString(4, contact);
			prepare.setString(5, email);
			prepare.executeUpdate();
			System.out.println("User Registration Successfully");
			System.out.println("-------------------------------------");
			System.out.println("Please login now");
			userLogin();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}		
	}
	
	

	@Override
	public void addToCart() 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter many products you want to purchase??");
		int input=scanner.nextInt();
		for(int i= 0 ;i<input; i++)
		{
			System.out.println("Enter product id of Product which need to be purchased: ");
			int pid= scanner.nextInt();
			
			
			System.out.println("Enter Quantity: ");
			int quant = scanner.nextInt();
			try
			{
				con=connection.getCon();
				PreparedStatement prepare=  con.prepareStatement("select * from product where pid =?");
				prepare.setInt(1, pid);
				ResultSet rs=prepare.executeQuery();
				while(rs.next())
				{
					ArrayList al = new ArrayList();
//					al.add(rs.getString(columnIndex))
				}
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			System.out.println("Added to the CART");
		}
		
	}

	@Override
	public void viewProductSort() 
	{
		try
		{
			con=connection.getCon();
			PreparedStatement prepare=  con.prepareStatement("select pid,name,descp,quantity,price from product order by pid asc");
			ResultSet rs=prepare.executeQuery();
			while(rs.next())
			{
				System.out.println("Product id:"+rs.getInt(1)+" |    Product name: "+rs.getString(2)+"  |    Description: "+rs.getString(3)+"  |    Quantitiy: "+rs.getInt(4)+"   |   Price: "+rs.getInt(5));
				//addToCart();
			}
			//addtoCart();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void userLogin()
	{
		con=connection.getCon();
		try
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your Contact number");
			String contact=scanner.next();
			System.out.println("Enter your Password");
			String pass=scanner.next();
			PreparedStatement prepare=  con.prepareStatement("select contact,password from user");
//				
			ResultSet rs=prepare.executeQuery();
			while(rs.next())
			{
				if(contact.contentEquals(rs.getString(1)) && pass.contentEquals(rs.getString(2)))
				{
					System.out.println("Login Successful");
					viewProductSort();
					break;
				}
				else 
				{
					System.err.println("WRONG CRDENTIALS");
					userLogin();
					break;
				}
			}
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
