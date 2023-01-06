package com.main;

import java.util.Scanner;

import com.admin.Admin;
import com.user.User;

public class EcommerceMain 
{
	User user = new User();	
	
	
	public void startMethod()
	{
		System.out.println("1: ADMIN LOGIN");
		System.out.println("2: USER LOGIN");
		System.out.println("3: NEW USER REGISTRATION");
		System.out.println("------------------------------------");
		Scanner scanner = new Scanner(System.in);
		System.out.println("ENTER YOUR CHOICE");
		System.out.println("------------------------------------");
		int loginput=scanner.nextInt();
		if (loginput==1)
		{
			Admin admin = new Admin();
			admin.adminLogin();
		}
		else if(loginput==2)
		{
			user.userLogin();
		}else if(loginput==3)
		{
			user.registerUser();
		}else
		{
			System.out.println("ENTER CORRECT CHOICE");
			startMethod();
		}
	}
	public static void main(String agrs[])
	{
		
		EcommerceMain ecom = new EcommerceMain();
		ecom.startMethod();
//		User user = new User();
//		user.viewProductSort();
	}
}

