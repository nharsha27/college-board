package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class mySqlCon{  
		public static Connection con;
		public static Connection connect()
		{  
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","ChorMegha$1");  
				//here sonoo is database name, root is username and password  
				System.out.println("DB Connection successfull");
				   }
			catch(Exception e)
			{ 
			System.out.println(e);
			}
			return con;
		}
	}  

