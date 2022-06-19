package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class forgotDAO {

	Connection con;
	
	public forgotDAO() throws ClassNotFoundException, SQLException {
		Class.forName(DAO.driverType);
		con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
	}
	
	public HashMap<String,String> getSecurityQuestionAnswer(String email) throws SQLException{
		HashMap<String,String> question = new HashMap<String, String>();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select user_secret_question,user_secret_answer from user_master where user_email_id='"+email+"'");
		
		while(rs.next()) {
			question.put("Question", rs.getString(1));
			question.put("Answer", rs.getString(2));
		}
		
		return question;
	}
	
	public boolean checkSecurityAnswer(String email, String answer) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select user_secret_question,user_secret_answer from user_master where user_email_id='"+email+"'");
		
		while(rs.next()) {
			if(answer.equals(rs.getString(2))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean updatePassword(String email, String password) throws SQLException {
		PreparedStatement ps = con.prepareStatement("update user_master set user_password = ? where user_email_id=?");
		ps.setString(1, password);
		ps.setString(2, email);
		boolean status = ps.execute();
		return status;
	}
}
