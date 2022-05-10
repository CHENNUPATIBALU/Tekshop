package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class UpdateQuantity extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String productID = req.getParameter("product");
		String quantity = req.getParameter("quantity");
		PrintWriter out = res.getWriter();
		CartDAO dao;
		
		try {
			 dao = new CartDAO();
			 dao.updateQuantity(productID,quantity);
			 out.write("<script>window.location.href = 'cart'</script>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
