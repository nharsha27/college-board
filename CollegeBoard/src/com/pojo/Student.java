package com.pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Student {

	
	String studentName;
	int studentRollNo;
	String studentEmailAddress;
	
	
	public static Student createInstance() throws IOException
	{
		
		System.out.println(" -- Adding student Details --");
		System.out.println(" <-- Please enter name, email and rollNo --> ");
		
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		String email = br.readLine();
		int rollNo = Integer.parseInt(br.readLine());
		
		Student s1 = new Student(rollNo,name, email);
		
		System.out.println("details are: " +name+ " " +email+ " " +rollNo);

		
		return s1;
		
	}
	public Student()
	{
		System.out.println(" -- Adding student Details --");
	}
	public Student(int studentRollNo, String studentName, String studentEmailAddress)
	{
		this.studentName = studentName;
		this.studentRollNo = studentRollNo;
		this.studentEmailAddress = studentEmailAddress;
		
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentRollNo() {
		return studentRollNo;
	}
	public void setStudentRollNo(int studentRollNo) {
		this.studentRollNo = studentRollNo;
	}
	public String getStudentEmailAddress() {
		return studentEmailAddress;
	}
	public void setStudentEmailAddress(String studentEmailAddress) {
		this.studentEmailAddress = studentEmailAddress;
	}
	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", studentRollNo=" + studentRollNo + ", studentEmailAddress="
				+ studentEmailAddress + "]";
	}
	
	
}
