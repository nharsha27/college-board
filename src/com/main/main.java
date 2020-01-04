package com.main;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.environment.environment;

public class main {
	Logger logger=Logger.getLogger(main.class);
	

public static void main(String[] args) {
	
		// TODO Auto-generated method stub
    
    environment.triggerprocess();
         
}
}
