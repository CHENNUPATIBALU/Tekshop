package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
	
	Connection con;
	String email;
	String password;
	
	public LoginDAO(String email, String password) throws Exception {
		this.email = email;
		this.password = password;
		Class.forName(DAO.driverType);
		con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
	}
	
	public String checkUser() throws SQLException {
		String query = "select user_id from user_master where user_email_id='"+email+"' and user_password='"+password+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
	
	public String getSecretQuestion() throws SQLException {
		String query = "select user_secret_question from user_master where user_email_id='"+email+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
	
	public String getSecretQuestionAnswer() throws SQLException {
		String query = "select user_secret_answer from user_master where user_email_id='"+email+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
}
