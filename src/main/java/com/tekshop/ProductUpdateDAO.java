package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductUpdateDAO {
	
	String productName, productInventory, productPrice, productCategory;
	boolean activeFlag;
	static Connection con;
	
	public ProductUpdateDAO() throws Exception {
		makeConnection(DAO.driverType, DAO.url, DAO.userName, DAO.password);
	}
	
	public ProductUpdateDAO(String productName, String productInventory, String productPrice, String productCategory, boolean activeFlag) throws Exception {
		this.productName = productName;
		this.productInventory = productInventory;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.activeFlag = activeFlag;
		makeConnection(DAO.driverType, DAO.url, DAO.userName, DAO.password);
	}
	
	public static void makeConnection(String driverType, String driverUrl, String userName, String password) throws Exception {
		Class.forName(driverType);
		con = DriverManager.getConnection(driverUrl,userName,password);
		System.out.println("Connection success");
	}
	
	public String updateProduct(String prodId) throws Exception {
		String query = "update products set product_name=?,product_inventory_level=?,product_unit_price=?,product_category=?,active_flag=? where product_id='"+prodId+"'";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, productName);
		ps.setString(2, productInventory);
		ps.setString(3, productPrice);
		ps.setString(4, productCategory);
		ps.setBoolean(5, activeFlag);
		ps.execute();
		
		return null;
	}

	public String deleteProduct(String prodId) throws SQLException {
		String query = "delete from products where product_id='"+prodId+"'";
		PreparedStatement ps = con.prepareStatement(query);
		ps.execute();
		
		return null;
	}
	
	public void updateInventory(String prodId, int inventory) throws SQLException {
		String query = "update products set product_inventory_level=? where product_id='"+prodId+"'";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, inventory+"");
		ps.execute();
	}
}
