package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mycompany.beans.User;

public class UserDaoImpl {
	private DaoFactory dao;

	public UserDaoImpl(DaoFactory dao) {
		super();
		this.dao = dao;
	}
	
	public User isValidLogin(String login, String password){
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn=dao.getConnection();
			statement=conn.prepareStatement("SELECT * FROM connexion where login =? AND password=?");
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				return new User(rs.getInt("id"),rs.getString("login"),HashClass.sha1(rs.getString("password")),rs.getInt("profil"));
			}else {
				return null;
			}
		}catch(Exception exception){
			throw new RuntimeException(exception);	
		} 
		
	}
	
	
}
