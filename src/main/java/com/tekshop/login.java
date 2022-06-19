package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
				out.append("<script>\r\n"
						+ "function setCookie(cname, cvalue, exdays) {\r\n"
						+ "  const d = new Date();\r\n"
						+ "  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));\r\n"
						+ "  let expires = \"expires=\"+d.toUTCString();\r\n"
						+ "  document.cookie = cname + \"=\" + cvalue + \";\" + expires + \";path=/\";\r\n"
						+ "	 window.location.href='home'; \r\n"
						+ "}\r\n"
						+ "localStorage.setItem('user_id','"+userID+"'); setCookie('user_id','"+userID+"',1);</script>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
