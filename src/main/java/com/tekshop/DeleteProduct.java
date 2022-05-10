package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class DeleteProduct extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String productID = req.getParameter("product");
		PrintWriter out = res.getWriter();
		CartDAO dao;
		
		try {
			 dao = new CartDAO();
			 CartDAO.deleteCartItem(productID);
			 out.write("<script>window.location.href = 'cart.html'</script>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
