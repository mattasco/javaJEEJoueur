package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private String url;
	private String username;
	private String password;
	
	public DaoFactory(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DaoFactory getInstance(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			
		}
		
		DaoFactory instance=new DaoFactory("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root", "1234567");
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		Connection connexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root", "1234567");
		return connexion;
	}
	
}
