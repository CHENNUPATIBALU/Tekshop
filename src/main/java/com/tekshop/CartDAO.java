package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDAO {
	
	static Connection con;
	public CartDAO() throws ClassNotFoundException, SQLException {
		Class.forName(DAO.driverType);
		con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
	}
	
	public static List<HashMap<String,String>> cartItems() throws SQLException{
		List<HashMap<String,String>> cart = new ArrayList<HashMap<String,String>>();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from cart_table");
		
		while(rs.next()) {
			HashMap<String,String> map = new HashMap<String, String>();
			HashMap<String,String> product = getProduct(rs.getString("product_id"));
			map.put("ProductID", rs.getString("product_id"));
			map.put("ProductName", product.get("ProductName"));
			map.put("ProductPrice", product.get("ProductPrice"));
			map.put("ProductCategory", product.get("ProductCategory"));
			map.put("SelectedUnits", rs.getInt("selected_units")+"");
			map.put("CustomerID", rs.getString("customer_id"));
			map.put("CartID", rs.getString("cart_id"));
			map.put("CartTimeStamp", rs.getString("cart_timestamp"));
			cart.add(map);
		}
		
		return cart;
	}
	
	public static HashMap<String,String> getProduct(String prodId) throws SQLException{
		HashMap<String,String> product = new HashMap<String, String>();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select product_name,product_unit_price,product_category from products where product_id='"+prodId+"'");
		
		while(rs.next()) {
			product.put("ProductName", rs.getString(1));
			product.put("ProductPrice", rs.getString(2));
			product.put("ProductCategory", rs.getString(3));
		}
		return product;
	}
	
	public static void deleteCartItem(String productID) throws SQLException {
		PreparedStatement ps = con.prepareStatement("delete from cart_table where product_id='"+productID+"'");
		ps.execute();
	}
	
	public void updateQuantity(String productID, String quantity) throws SQLException {
		PreparedStatement ps = con.prepareStatement("update cart_table set selected_units=? where product_id=?");
		ps.setInt(1, Integer.parseInt(quantity));
		ps.setString(2, productID);
		ps.execute();
	}
}
