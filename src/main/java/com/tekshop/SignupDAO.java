package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupDAO {

	Connection con;
	
	public SignupDAO() throws ClassNotFoundException, SQLException {
		Class.forName(DAO.driverType);
		con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
	}
	
	public String signUp(String firstName, String lastName, String mobile, String email, String password, String securityQuestion, String answer) throws SQLException {
		PreparedStatement ps = con.prepareStatement("insert into user_master values(?,?,?,?,?,?,?,?,?)");
		String user = firstName.substring(0,3)+lastName.substring(0,3)+mobile.substring(0,3);
		ps.setString(1, user);
		ps.setString(2, firstName);
		ps.setString(3, lastName);
		ps.setString(4, email);
		ps.setString(5, mobile);
		ps.setString(6, "user");
		ps.setString(7, password);
		ps.setString(8, securityQuestion);
		ps.setString(9, answer);
		
		ps.execute();
		
		return user;
	}
}
