package com.mohan.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
		public static Connection databaseConnection()throws ClassNotFoundException
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			return con;
		}
}
