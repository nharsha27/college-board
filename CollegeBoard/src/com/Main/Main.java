package com.Main;

import java.awt.Label;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.pojo.Student;

public class Main {

	public static void main(String args[]) throws IOException
	{
		ArrayList<Student> sList = new ArrayList<Student>();

		String result = startApplication();
		try {
			check1: 	
					if(result.contentEquals("1"))
					{
						Student s1 = Student.createInstance();
						sList.add(s1);
						result = startApplication();
						break check1;
					}

			if(result.contentEquals("2"))
			{
				Student.createInstance();
			}

			if(result.contentEquals("4"))
			{
				System.out.println(" All students are: ");
				System.out.println(sList);
				result = startApplication();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String startApplication() throws IOException
	{
		String result = null;
		System.out.println(" <<-- Welcome to College Baord -->>");

		try {

			System.out.println(" Press 1 :- Add Student Details");
			Thread.currentThread().sleep(1000);

			System.out.println(" Press 2 :- Update Student Details");
			Thread.currentThread().sleep(1000);

			System.out.println(" Press 3 :- Delete Student Details");
			Thread.currentThread().sleep(1000);

			System.out.println(" Press 4 :- View Student Details");
			Thread.currentThread().sleep(1000);

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			result = br.readLine();

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
}
