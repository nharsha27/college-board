package com.sendalert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.connect.fetchemail;

public class SendEmail {
	public static void main1(ArrayList<String> usermailsubjtosent) {  
		try {
		String host="imap.gmail.com";  
		final String from="nharsha27@gmail.com";//change accordingly  
        final String user="nharsha27@gmail.com";//change accordingly  
		final String password="testingnishith";//change accordingly  
		String subject = "Test";
		
//		for(String message:usermailsubjtosent){
//			//System.out.println("dup mails sunbj is"+dupmessage.getSubject());
//			
//		}
		String msg = "subj is "+usermailsubjtosent;
		
		String to="nishith.harsha@gmail.com";//change accordingly  

		/*	   //Get the session object  
		   Properties props = new Properties();  
		   props.put("mail.smtp.host",host);  
		   props.put("mail.smtp.auth", "true");  

		 */
		Properties props = new Properties();  
		props.setProperty("mail.transport.protocol", "smtp");     
		props.setProperty("mail.host", "smtp.gmail.com");  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "465");  
		props.put("mail.debug", "true");  
		props.put("mail.smtp.socketFactory.port", "465");  
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		props.put("mail.smtp.socketFactory.fallback", "false");  
		Session session = Session.getInstance(props,  
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(from,password);  
			}  
		});  

		//session.setDebug(true);  
		Transport transport = session.getTransport();  
		InternetAddress addressFrom = new InternetAddress(from);  


		//Compose the message  

		MimeMessage message = new MimeMessage(session);  
		message.setSender(addressFrom);  
		message.setSubject(subject);  
		String m1 = "<html> <tr> <td>1 </td><td>2 </td><td>3 </td></tr></html>";
		message.setContent(m1, "text/plain");  
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

		transport.connect();  
		Transport.send(message);  
		
			transport.close();
			System.exit(0);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}


