package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class login extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		PrintWriter out = res.getWriter();
		
		String userID = null;
		LoginDAO dao = null;
		
		try {
			dao = new LoginDAO(email, password);
			userID = dao.checkUser();
			if(userID!=null) {
				out.append("<script>localStorage.setItem('user_id','"+userID+"');window.location.href = 'home';</script>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
