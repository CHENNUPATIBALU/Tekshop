package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class updateInventory extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String productId = req.getParameter("productId");
		int inventory = Integer.parseInt(req.getParameter("inventory"));
		
		ProductUpdateDAO dao;
		try {
			dao = new ProductUpdateDAO();
			dao.updateInventory(productId, inventory);
			res.getWriter().write("<script>window.location.href='merchant';</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
