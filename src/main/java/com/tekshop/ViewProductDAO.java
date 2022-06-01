package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewProductDAO {
	
	Connection con;
	String driverType = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tekshop";
	String userName = "root";
	String password = "b@/uv!n@y7474";
	
	public ViewProductDAO() throws ClassNotFoundException, SQLException {
		Class.forName(driverType);
		con = DriverManager.getConnection(url, userName, password);
	}
	
	public ResultSet getProduct(String productId) throws SQLException, ClassNotFoundException{
		String query = "select product_category,product_name,product_unit_price,product_description,product_id from products where product_id='"+productId+"'";
		
		Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(query);
		
		return rs;
	}
}
