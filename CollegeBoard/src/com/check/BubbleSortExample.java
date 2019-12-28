package com.check;

import java.util.ArrayList;
import java.util.Collections;

public class BubbleSortExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// LinkedList[1,12,11,11,23] - Removing duplicate values
		ArrayList<Integer> aList = new ArrayList<Integer>();
		
		aList.add(15);
		aList.add(19);
		aList.add(15);
		aList.add(12);
		aList.add(55);
		aList.add(12);
		Collections.sort(aList);
		System.out.println(aList);
		
		for(int i = 0; i < aList.size() ; i++)
		{
			System.out.println(aList.get(i));
			
		}

	}

}
