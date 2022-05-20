package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MerchantDAO {
	
	Connection con;
	
	public MerchantDAO() throws Exception {
		Class.forName(DAO.driverType);
		con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
	}
	
	public List<HashMap<String,String>> getTransactions() throws SQLException{
		List<HashMap<String,String>> transactions = new ArrayList<HashMap<String,String>>();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from purchase_table");
		
		while(rs.next()) {
			HashMap<String,String> transaction = new HashMap<String, String>();
			transaction.put("PurchaseID", rs.getString(1));
			transaction.put("CustomerID", rs.getString(2));
			transaction.put("PaymentMethod", rs.getString(3));
			transaction.put("TotalValue", rs.getString(4));
			transaction.put("DiscountID", rs.getString(5));
			transaction.put("FinalValue", rs.getString(6));
			
			transactions.add(transaction);
		}
		return transactions;
	}
	
	public List<HashMap<String,String>> getRevenue() throws Exception{
		List<HashMap<String,String>> revenues = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> products = new HomePageDAO().getProducts();
		
		for(HashMap<String,String> product:products) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select product_id,sum(purchased_units),purchased_units from purchase_products_table where product_id='"+product.get("Product_ID")+"'");
			
			while(rs.next()) {
				HashMap<String,String> revenue = new HashMap<String, String>();
				int rev = rs.getInt(2)*Integer.parseInt(product.get("Product_Unit_Price"));
				
				revenue.put("ProductID", rs.getString(1));
				revenue.put("ProductName", 	product.get("Product_Name"));
				revenue.put("ProductUnits", rs.getInt(3)+"");
				revenue.put("ProductRevenue", rev+"");
				
				revenues.add(revenue);
			}
		}
		return revenues;
	}
	
	public List<HashMap<String,String>> getInventories() throws Exception{
		List<HashMap<String,String>> products = new HomePageDAO().getProducts();
		List<HashMap<String,String>> productInventories = new ArrayList<HashMap<String,String>>();
		
		for(HashMap<String,String> product:products) {
			HashMap<String,String> productInventory = new HashMap<String, String>();
			
			productInventory.put("ProductID", product.get("Product_ID"));
			productInventory.put("ProductName", product.get("Product_Name"));
			productInventory.put("ProductCategory", product.get("Product_Category"));
			productInventory.put("ProductPrice", product.get("Product_Unit_Price"));
			productInventory.put("ProductInventory", product.get("Product_Inventory"));
			
			productInventories.add(productInventory);
		}
		return productInventories;
	}
}
