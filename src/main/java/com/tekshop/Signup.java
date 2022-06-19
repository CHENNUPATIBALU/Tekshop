package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Signup extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");
		String mobile = req.getParameter("mobile");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String secQuestion = req.getParameter("security-question");
		String answer = req.getParameter("answer");
		SignupDAO dao;
		
		try {
			dao = new SignupDAO();
			String user = dao.signUp(firstName, lastName, mobile, email, password, secQuestion, answer);
			res.getWriter().write("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <title>Signup success</title>\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css\">\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <div id=\"signup-success-div\" style=\"background-color: white; width: max-content; text-align: center; height: inherit; margin: 0 auto; display: block;\">\r\n"
					+ "        <img src=\"https://res.cloudinary.com/chennupati-balu/image/upload/v1654926838/Tekshop/reg-success.png\" alt=\"signup-success\" style=\"width: 500px; margin: 0 auto; height: min-content;\">\r\n"
					+ "        <p style=\"font-size: x-large; font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\">Welcome to Tekshop <span id=\"user-name-span\" style=\"font-family: Arial, Helvetica, sans-serif;\"></span></p>\r\n"
					+ "        <button onclick=\"localStorage.setItem('user_id','"+user+"');setCookie('user_id','"+user+"',1);\" style=\"background-color: whitesmoke; padding: 10px; font-size: large; border-radius: 5px; cursor: pointer; border: 0.5px solid grey; box-shadow: 1px 1px 1px grey;\" onmouseover = \"this.style = 'width: max-content; background-color: whitesmoke; padding: 10px; font-size: large; border-radius: 5px; cursor: pointer; border: 0.5px solid grey; box-shadow: 1px 1px 5px grey;'; document.getElementById('arrow-icon').style.display = 'block';\" onmouseout = \"this.style = 'background-color: whitesmoke; padding: 10px; font-size: large; border-radius: 5px; cursor: pointer; border: 0.5px solid grey; box-shadow: 1px 1px 1px grey'; document.getElementById('arrow-icon').style.display = 'none';\">Go to home &nbsp;<i class=\"fas fa-arrow-right\" id=\"arrow-icon\" style=\"display: none;\"></i></button>\r\n"
					+ "    </div>\r\n"
					+ "</body>\r\n"
					+ "<script>"
					+ "function setCookie(cname, cvalue, exdays) {\r\n"
					+ "  const d = new Date();\r\n"
					+ "  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));\r\n"
					+ "  let expires = \"expires=\"+d.toUTCString();\r\n"
					+ "  document.cookie = cname + \"=\" + cvalue + \";\" + expires + \";path=/\";\r\n"
					+ "	 window.location.href='home'; \r\n"
					+ "}\r\n"
					+ "</script>"
					+ "</html>");
		} catch(SQLIntegrityConstraintViolationException e) {
			res.getWriter().write("<script>alert('Provided email is registered with another user. Kindly provide another email'); history.back();</script>");
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
