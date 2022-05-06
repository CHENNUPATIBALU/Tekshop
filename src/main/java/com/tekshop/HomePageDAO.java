package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomePageDAO {
	
	static Connection con;
	
	public List<HashMap<String, String>> getProducts() throws Exception{
		List<HashMap<String,String>> products = new ArrayList<HashMap<String,String>>();
		
		makeConnection(DAO.driverType, DAO.url, DAO.userName, DAO.password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from products");
		
		while(rs.next()) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("Product_ID", rs.getString(1));
			map.put("Product_Name", rs.getString(2));
			map.put("Product_Desc", rs.getString(3));
			map.put("Product_Features", rs.getString(4));
			map.put("Product_Manufacturer", rs.getString(5));
			map.put("Product_size", rs.getString(6));
			map.put("Product_Category", rs.getString(7));
			map.put("Product_Sub_Category", rs.getString(8));
			map.put("Product_Unit", rs.getString(9));
			map.put("Product_Inventory", rs.getString(10));
			map.put("Product_Unit_Price", rs.getString(11));
			map.put("Product_Price_Currency", rs.getString(12));
			map.put("Product_Inventory_Threshold", rs.getString(13));
			map.put("Product_Image", rs.getString(14));
			map.put("ActiveFlag",rs.getBoolean(15)+"");
			
			products.add(map);
		}
		
		con.close();
		st.close();
		
		return products;
	}
	
	public static void makeConnection(String driverType, String driverUrl, String userName, String password) throws Exception {
		Class.forName(driverType);
		con = DriverManager.getConnection(driverUrl,userName,password);
		System.out.println("Connection success");
	}
}
