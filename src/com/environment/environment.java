package com.environment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.mySqlCon;

public class environment {
	public static void triggerprocess(){
		//creating db object and connecting it
		 Connection conn=mySqlCon.connect();
		 //creating arraylist for getting name of mailbox from db table
		 ArrayList<String> mailboxnamelist=new ArrayList<String>();
	     try {
			Statement st=conn.createStatement();
			ResultSet rs =st.executeQuery("Select distinct(name) from mailbox_mailid where status= 'ON'");
			while(rs.next()){
				
			System.out.println("name coulmn data "+rs.getString(1));
			mailboxnamelist.add(rs.getString(1));
			
			}
			for(String name:mailboxnamelist){
				Mailbox m1= new Mailbox(name);
				Thread t1= new Thread(m1);
				//System.out.println(m1.hashCode());
				t1.start();
				
			}
			System.out.println(Thread.currentThread().getName());
			
			System.out.println(mailboxnamelist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}