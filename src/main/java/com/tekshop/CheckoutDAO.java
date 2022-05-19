package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CheckoutDAO {
	
	static Connection con;
	public CheckoutDAO() throws ClassNotFoundException, SQLException {
		Class.forName(DAO.driverType);
		con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
	}
	
	public void addToPurchase(String cusId, String paymentMethod, String totalAmount, String discountID, String finalAmount, String purchaseID, String delMethod, String delInstructions) throws SQLException {
		List<HashMap<String,String>> cartItems = cartItems();
		Iterator<HashMap<String, String>> cartIterator = cartItems.iterator();
		
		PreparedStatement ps1 = con.prepareStatement("insert into purchase_table values(?,?,?,?,?,?)");
		ps1.setString(1, purchaseID);
		ps1.setString(2, cusId);
		ps1.setString(3, paymentMethod);
		ps1.setInt(4, Integer.parseInt(totalAmount));
		ps1.setString(5, discountID);
		ps1.setInt(6, Integer.parseInt(finalAmount));
		ps1.execute();
		
		while(cartIterator.hasNext()) {
			HashMap<String,String> item = cartIterator.next();
			PreparedStatement ps = con.prepareStatement("insert into purchase_products_table values(?,?,?,?,?)");
			ps.setString(1, purchaseID);
			ps.setString(2, item.get("ProductID"));
			ps.setInt(3, Integer.parseInt(item.get("SelectedUnits")));
			ps.setString(4, delInstructions);
			ps.setString(5, delMethod);
			ps.execute();		
			reduceInventory(item.get("ProductID"), Integer.parseInt(item.get("SelectedUnits")));
		}
		clearCart();
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
	
	protected void reduceInventory(String productID, int units) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from products where product_id='"+productID+"'");
		
		while(rs.next()) {
			PreparedStatement ps = con.prepareStatement("update products set product_inventory_level=? where product_id=?");
			ps.setInt(1, (rs.getInt("product_inventory_level")-units));
			ps.setString(2, productID);
			ps.execute();
		}
	}
	
	protected void clearCart() throws SQLException {
		PreparedStatement ps = con.prepareStatement("delete from cart_table");
		ps.execute();
	}
	
	public int getDiscountPercentage(String discountID) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select discount_coupon_percent from discounts_table where discount_id='"+discountID+"'");
		while(rs.next()) {
			return rs.getInt("discount_coupon_percent");
		}
		return 0;
	}
}
