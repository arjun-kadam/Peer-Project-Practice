package com.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	public static Connection getDbConnection () throws ClassNotFoundException{
		Connection con=null;
		Class.forName("com.mysql.jdbc.Driver");
		String uName="root";
		String pass="root";
		String url="jdbc:mysql://localhost:3306/loginlogout";
		try {
			con=DriverManager.getConnection(url,uName,pass);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
		
	}
}
