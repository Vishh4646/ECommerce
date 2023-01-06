package com.admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import com.connection.ConnectionProvider;

public class Admin implements adminInterface
{
	final String uname="admin";
	final String pass= "admin";
	ConnectionProvider connection= new ConnectionProvider();
	Scanner sc = new Scanner(System.in);
	Connection con;
	
	public void adminLogin()
	{		
		System.out.println("-------------------------------");
		System.out.println("Enter USERNAME for Administration");
		String tempuname=sc.next();
		System.out.println("Enter PASSWORD for Administration");
		String temppass=sc.next();
		System.out.println("-------------------------------");
		if(tempuname.contentEquals(tempuname) && temppass.contentEquals(pass))
		{
			System.out.println("LOGIN SUCCESSFULL FOR ADMIN");
			startAdmin();
		}
		else
		{
			System.err.println("WRONG CREDENTIALS FOR ADMIN");
		}
	}
	
	
	public void startAdmin()
	{
		System.out.println("--------------------------------------------");
		System.out.println("1: CHECK QUANTITY OF PRODUCT ");
		System.out.println("2: REGISTERED USER LIST ");
		System.out.println("3: CHECK HISTORY OF PURCHASE ORDER OF USER ");
		System.out.println("--------------------------------------------");
		System.out.println("Enter Your Choice: ");
		System.out.println("--------------------------------------------");
		int input= sc.nextInt();
		if(input == 1)
		{
			checkQuantityOfProduct();
		}else if(input ==2)
		{
			viewRegisteredUser();
		}
	}
	
	
	public void viewRegisteredUser()
	{
		try
		{
			con=connection.getCon();
			PreparedStatement preparedS = con.prepareStatement("select * from user");
			ResultSet resultset = preparedS.executeQuery();
			
			while(resultset.next())
			{
				System.out.println("id: "+resultset.getInt(1)+"  |   Username: "+resultset.getString(2)+"   |   Contact: "+resultset.getString(4)+"   |Email: "+resultset.getString(5));
				
			}
			startAdmin();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public void checkPurchaseOrder()
	{
		
	}
	
	
	
	public void checkQuantityOfProduct()
	{
		System.out.println("Enter Product Id to check quantity: ");
		int pid = sc.nextInt();
		try
		{
			con=connection.getCon();
			PreparedStatement preparedS = con.prepareStatement("select quantity,descp from product where pid=?");
			preparedS.setInt(1, pid );
			ResultSet resultset = preparedS.executeQuery();
			while(resultset.next())
			{
				System.out.println("Quantity of the product "+resultset.getString(2)+":"+resultset.getInt(1));
				startAdmin();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
