package com.igate.moviebooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.igate.moviebooking.exception.MovieException;
/*
 * DBUtil class to access a connection pool 
 */
public class DBUtil {
	static Connection connection;

	public static Connection obtainConnection() throws MovieException {
		try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");  
			connection =DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","54321");
	 }
	 catch(Exception e){
		 System.out.println(e);
	 }
	 return connection;
	}
}
