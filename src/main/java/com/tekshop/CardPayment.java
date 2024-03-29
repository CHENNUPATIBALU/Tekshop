package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CardPayment extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String finalAmount = req.getParameter("amount");
		String cardNumber = req.getParameter("card-no");
		String expiry = req.getParameter("expiry");
		String cvv = req.getParameter("cvv");
		String delMethod = req.getParameter("delivery-type");
		String delInstructions = req.getParameter("del-instructions");
		String cusId = req.getParameter("cus_id");
		String purchaseID = generatePurchaseID();
		PrintWriter out = res.getWriter();
		
		CheckoutDAO dao;
		try {
			dao = new CheckoutDAO();
			dao.addToPurchase(cusId, "cred_deb", finalAmount, "first_10", finalAmount, purchaseID, delMethod, delInstructions);
			out.write("<script>window.location.href='order-success.html';</script>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String generatePurchaseID(){
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		return String.format("p_%s%s%s", timeStamp.getHours(),timeStamp.getMinutes(),timeStamp.getSeconds());
	}
}
