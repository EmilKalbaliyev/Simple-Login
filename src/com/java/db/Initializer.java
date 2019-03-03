package com.java.db;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Initializer implements ServletContextListener {
	  public void contextInitialized(ServletContextEvent event) {
		  ServletContext sc = event.getServletContext(); 
		  Enumeration<String> en = sc.getInitParameterNames(); //getting all the context params
		  
		  Properties props = new Properties(); 
		  
		  while(en.hasMoreElements()) {
			  String name = en.nextElement(); 
			  props.setProperty(name, sc.getInitParameter(name)); //storing 4 params which are in web. xml inside properties
			  //so will be used for db connection
		  }
		  
		  JdbcUtilityWEB.setProps(props);
	  }
	  
	  public void contextDestroyed(ServletContextEvent event) {}
	}