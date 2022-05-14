package com.tekshop;
import java.sql.*;

public class ProductEntryDAO {
	
	static Connection con;
	static String productId;
	static String productName;
	static String productDesc;
	static String productFeatures;
	static String productManufacturer;
	static String productSize;
	static String productCategory;
	static String productSubCategory;
	static String productUnit;
	static int unitPrice;
	static int inventoryLevel;
	static String priceCurrency;
	static int inventoryThreshold;
	static Blob image;
	static boolean active;
	
	public ProductEntryDAO(String productName, String productDesc, String productFeatures, 
							String productManufacturer,
							String productSize,
							String productCategory,
							String productSubCategory,
							String productUnit,
							int unitPrice,
							int inventoryLevel,
							String priceCurrency,
							int inventoryThreshold,
							Blob image,
							boolean active) throws Exception {
		ProductEntryDAO.image = image;
		ProductEntryDAO.inventoryLevel = inventoryLevel;
		ProductEntryDAO.inventoryThreshold = inventoryThreshold;
		ProductEntryDAO.priceCurrency = priceCurrency;
		ProductEntryDAO.productCategory = productCategory;
		ProductEntryDAO.productDesc = productDesc;
		ProductEntryDAO.productFeatures = productFeatures;
		ProductEntryDAO.productManufacturer = productManufacturer;
		ProductEntryDAO.productName = productName;
		ProductEntryDAO.productSize = productSize;
		ProductEntryDAO.productSubCategory = productSubCategory;
		ProductEntryDAO.productUnit = productUnit;
		ProductEntryDAO.unitPrice = unitPrice;
		ProductEntryDAO.active = active;
		ProductEntryDAO.makeConnection(DAO.driverType, DAO.url, DAO.userName, DAO.password);
	}
	
	public static void makeConnection(String driverType, String driverUrl, String userName, String password) throws Exception {
		Class.forName(driverType);
		con = DriverManager.getConnection(driverUrl,userName,password);
		System.out.println("Connection success");
	}
	
	public void addProduct() throws Exception {
		productId = getGeneratedProductId();
		String query = "insert into products values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println(productId);
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, productId);
		ps.setString(2, productName);
		ps.setString(3, productDesc);
		ps.setString(4, productFeatures);
		ps.setString(5, productManufacturer);
		ps.setString(6, productSize);
		ps.setString(7, productCategory);
		ps.setString(8, productSubCategory);
		ps.setString(9, productUnit);
		ps.setString(10, inventoryLevel+"");
		ps.setString(11, unitPrice+"");
		ps.setString(12, priceCurrency);
		ps.setString(13, inventoryThreshold+"");
		ps.setBlob(14, image);
		ps.setBoolean(15, active);
		ps.execute();
		
		con.close();
		ps.close();
	}
	
	public static String getGeneratedProductId() {
		return generateProductId();
	}
	
	public static String generateProductId() {
		return String.format("%s%s",productCategory.substring(0, 3),productName.substring(0, 3));
	}
}
