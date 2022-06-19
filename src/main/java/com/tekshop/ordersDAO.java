package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ordersDAO {

	Connection con;
	
	public ordersDAO() throws ClassNotFoundException, SQLException {
		Class.forName(DAO.driverType);
		con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
	}
	
	public List<HashMap<String,String>> getOrders(String user) throws SQLException{
		List<HashMap<String,String>> orders = new ArrayList<HashMap<String,String>>();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select purchase_id,final_value,payment_method_used from purchase_table where customer_id='"+user+"'");
		
		while(rs.next()) {
			HashMap<String,String> order = new HashMap<String, String>();
			
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("select product_id,purchased_units,delivery_instructions,delivery_method from purchase_products_table where purchase_id='"+rs.getString(1)+"'");
			
			String products = "";
			while(rs1.next()) {
				HashMap<String,String> product = getProduct(rs1.getString(1));
				products+=product.get("ProductName")+":"+rs1.getString(2)+"-"+product.get("ProductPrice")+";";
			}
			
			order.put("Products", products);
			order.put("PurchaseID", rs.getString(1));
			order.put("FinalValue", rs.getString(2));
			order.put("PaymentMethod", rs.getString(3));
			orders.add(order);
		}
		
		return orders;
	}
	
	public HashMap<String,String> getProduct(String prodId) throws SQLException{
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
}
