package com.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;

import com.sendalert.SendEmail;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class fetchemail {
	static int firstlimit=1;
	static int secondlimit=0;
	static HashMap<String,Integer> fetchsubj= new HashMap<String,Integer>();
	static ArrayList<String> usermailsubjtosent=new ArrayList<String>();
	public static void fetch(String pop3Host, String storeType, String user,
			String password,String ofolder,String nfolder,int batchsize) {
		try {
			String str="nish";
						// create properties field
			Properties properties = new Properties();
			//		         properties.put("mail.pop3.ssl.enable", "true"); // Use SSL
			//		         properties.put("mail.store.protocol", "pop3");
			//		         properties.put("mail.pop3.host", pop3Host);
			//		         properties.put("mail.pop3.port", "995");
			properties.setProperty("mail.host","imap.gmail.com");
			properties.setProperty("mail.port","995");
			properties.setProperty("mail.transport.protocol","imaps");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);
			// emailSession.setDebug(true);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("imaps");
			//
			System.out.println("user is "+user);
			System.out.println("pass is"+password);
			store.connect(pop3Host, user, password);

			// create the folder object and open it
			Folder[] f = store.getDefaultFolder().list();
			for(Folder fd:f){
				System.out.println("-------------"+fd.getName());
			}
			Folder emailFolder = store.getFolder(ofolder);
			emailFolder.open(Folder.READ_WRITE);
			Folder dupfolder=store.getFolder(nfolder);
			dupfolder.open(Folder.READ_WRITE);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));

			// retrieve the messages from the folder in an array and print it
			int messagesCount = emailFolder.getMessageCount();
			int numberofbatchescreated= fetchemail.createbatch(batchsize,messagesCount);
			for(int j=1;j<=numberofbatchescreated;j++){
				if(j<numberofbatchescreated){
					firstlimit=secondlimit+1;
					secondlimit=firstlimit+batchsize;
					System.out.println(firstlimit+" ,"+(secondlimit));  
				}
				else{
					System.out.println((secondlimit+1)+","+messagesCount);
				}
				Message[] messages=emailFolder.getMessages(firstlimit, secondlimit);
				System.out.println("messages.length---" + messages.length);
				
				

				ArrayList<Message> duplicatemails= new ArrayList<Message>();

				int counter=0;



				for (int i = 0; i < messages.length; i++) {
					if((messages[i].getSubject() !=  null)&&messages[i].getSubject().length()>15){
						Message message = messages[i];
						System.out.println(message.getSubject());
						//System.out.println("---------------------------------");
						//System.out.println(message.getSubject());
						if(fetchsubj.containsKey(message.getSubject().substring(0, 10))){

							fetchsubj.put(message.getSubject().substring(0, 10), fetchsubj.get(message.getSubject().substring(0, 10))+1);
							duplicatemails.add(message);
						}
						else{
							fetchsubj.put(message.getSubject().substring(0, 10), 1);
						}


					}
				}
				System.out.println("subj is"+fetchsubj);
				
				
				for(Message dupmessage:duplicatemails){
					System.out.println("dup mails sunbj is"+dupmessage.getSubject());
				}
				Message[] tomovearray= new Message[duplicatemails.size()];
				tomovearray=duplicatemails.toArray(tomovearray);
				fetchemail.movemails(dupfolder, emailFolder, tomovearray);

for(int k=0;k<tomovearray.length;k++){
	String msg=tomovearray[k].getSubject();
	usermailsubjtosent.add(msg);
	//System.out.println(msg.getSubject());
}
SendEmail.main1(usermailsubjtosent);
				Iterator itr=fetchsubj.values().iterator();
				ArrayList<Integer> countlist=new ArrayList<Integer>();
				while(itr.hasNext()){
					int count=(int) itr.next();
					if(count>5){
						System.out.println("count is"+count);

						for(Map.Entry<String, Integer> entry:fetchsubj.entrySet()){
							if(count==entry.getValue()){
								String key=entry.getKey();
								System.out.println("key is"+key);
							}

						}
					}
				}
				System.out.println("batch " +j+ "completed !!!!");
			}
				// close the store and folder objects
				emailFolder.close(false);
				store.close();
			
		}
		catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	public static void movemails(Folder newfolder,Folder oldfolder, Message[] message ){
		try {
			System.out.println("copying...........");
			oldfolder.copyMessages(message, newfolder);
			System.out.println("done-------------");

			// oldfolder.setFlags(message, new Flags(Flags.Flag.DELETED),true);
			//System.out.println("deletion done");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static int createbatch(int batchsize,int mailcount){

		// int totalmails=400;

		float result=(float)mailcount/batchsize;
		int numberofbatchescreated;
		if(mailcount%batchsize==0){
			numberofbatchescreated=(int) result; 
			System.out.println("batches to be created"+numberofbatchescreated);
		}
		else{
			numberofbatchescreated=(int) (result+1);
			System.out.println("batch created "+numberofbatchescreated);
		}
		// System.out.println(result);





		//System.exit(0);
		return numberofbatchescreated;

	}

}
