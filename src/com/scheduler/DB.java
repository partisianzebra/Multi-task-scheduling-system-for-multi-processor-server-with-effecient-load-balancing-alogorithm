package com.scheduler;


import java.io.*;
import java.sql.*;

public class DB {
	
	
	private static Connection con;
	private static PreparedStatement pstmt;
	private static Statement stmt,stmt1,stmt2;
	private static ResultSet rs;
	
	
	static Connection getConnection(){
		
		Connection con=null;
		try{
			
			System.out.println("MySQL Connect Example.");
			String url="jdbc:mysql://localhost:3306/";
			String dbName = "multicore";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "root";
			String passWord = "root";
			
			
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url+dbName,userName,passWord);
			System.out.println("Connected to database");
			con.setAutoCommit(true);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return con;
		
	}

}
