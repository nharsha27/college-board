package com.environment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import com.connect.fetchemail;
import com.util.mySqlCon;

public class Mailbox implements Runnable{
String mailboxname;
String status;
public Mailbox(String maiboxname, String status) {
	super();
	this.mailboxname = mailboxname;
	this.status = status;
}
public Mailbox(String name) {
	// TODO Auto-generated constructor stubt
	this.mailboxname=name;
	
}
@Override
public void run() {
	Thread.currentThread().setName(mailboxname);
	System.out.println("name of thread "+Thread.currentThread().getName());
	
	Connection con=mySqlCon.connect();
	HashMap<String,String> folderdata= new HashMap<String,String>();
	try {
		Statement st=con.createStatement();
		ResultSet rs= st.executeQuery("Select *from mailboxinfo_config where name='"+mailboxname+"'");
		while(rs.next()){
			//System.out.println("keys is "+rs.getString(1)+" values is  "+rs.getString(2));
			folderdata.put(rs.getString(1), rs.getString(2));
		}
		System.out.println(folderdata);
		
		String emailaddress=folderdata.get("emailaddress");
		String host=folderdata.get("pop3Host");
		String type=folderdata.get("storeType");
		String oldfolder=folderdata.get("foldertomonitor");
		String newfolder=folderdata.get("foldertomove");
		int batchsize=Integer.parseInt(folderdata.get("batchsize"));
		fetchemail.fetch(host, type, emailaddress, "testingnishith",oldfolder,newfolder,batchsize);	
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// TODO Auto-generated method stub

	
}

}



