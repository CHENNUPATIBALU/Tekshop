package com.tekshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CheckoutDAO {
	
	Connection con;
	public CheckoutDAO() throws ClassNotFoundException, SQLException {
		Class.forName(DAO.driverType);
		con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
	}
	
	public void addToTransactions
	
	public void reduceInventory() {
		
	}
}
