package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class forgot extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.write("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <title>Forgot password</title>\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css\">\r\n"
				+ "    <style>\r\n"
				+ "        form h2{\r\n"
				+ "            font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;\r\n"
				+ "        }\r\n"
				+ "        form label,label b{\r\n"
				+ "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\r\n"
				+ "            margin: 0 auto;\r\n"
				+ "            display: block;\r\n"
				+ "        }\r\n"
				+ "        #error-p{\r\n"
				+ "        	padding: 10px;\r\n"
				+ "        	background-color: rgb(250, 110, 110);\r\n"
				+ "        	color: white;\r\n"
				+ "            width: inherit;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;\r\n"
				+ "            display: none;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body style=\"background-color: grey;\">\r\n"
				+ "    <div id=\"main-div\" style=\"display: flex;\">\r\n"
				+ "        <div id=\"email-div\" style=\"width: max-content; background-color: white; border-radius: 8px; padding: 50px; margin: 0 auto; position: relative; display: block;\">\r\n"
				+ "            <form style=\"width: max-content; margin: 0 auto; display: block;\" method=\"post\">\r\n"
				+ "                <h2>Forgot Password</h2>\r\n"
				+ "                <label>Registered Email</label><br>\r\n"
				+ "                <input type=\"email\" name=\"email\" style=\"height: 30px;\"><br>\r\n"
				+ "                <input type=\"submit\" style=\"border-radius: 8px; border: 0.5px solid grey; cursor: pointer; margin: 0 auto; display: block; margin-top: 10px; padding: 8px; font-size: medium;\" value=\"Continue >>\">\r\n"
				+ "            </form>\r\n"
				+ "        </div>\r\n"
				+ "        <div id=\"security-question-div\" style=\"width: max-content; background-color: white; border-radius: 8px; padding: 50px; margin: 0 auto; display: none;\">\r\n"
				+ "            <form style=\"width: max-content; margin: 0 auto; display: block;\" method=\"post\">\r\n"
				+ "                <h2>Forgot Password</h2>\r\n"
				+ "                <p id=\"error-p\">Answer does not match. Please enter the valid answer</p>\r\n"
				+ "                <label><b>Security question: </b><span id=\"sec-question-span\"></span></label><br>\r\n"
				+ "                <input type=\"hidden\" name=\"email\" style=\"height: 30px;\" id=\"sec-email-input\"><br>\r\n"
				+ "                <input placeholder=\"Answer\" type=\"text\" name=\"answer\" style=\"height: 30px; margin: 0 auto; display: block;\"><br>\r\n"
				+ "                <input type=\"submit\" value=\"Validate\" style=\"border-radius: 8px; border: 0.5px solid grey; cursor: pointer; margin: 0 auto; display: block; margin-top: 10px; padding: 8px; font-size: medium;\">\r\n"
				+ "            </form>\r\n"
				+ "        </div>\r\n"
				+ "        <div id=\"reset-password-div\" style=\"width: max-content; background-color: white; border-radius: 8px; padding: 50px; margin: 0 auto; display: none;\">\r\n"
				+ "            <form style=\"width: max-content; margin: 0 auto; display: block;\" method=\"post\">\r\n"
				+ "                <h2>Reset Password</h2>\r\n"
				+ "                <label>Enter new password</label><br>\r\n"
				+ "                <input type=\"hidden\" name=\"email\" style=\"height: 30px;\" id=\"reset-pass-email\"><br>\r\n"
				+ "                <input id=\"password\" type=\"password\" name=\"password\" style=\"position: relative; z-index: 0; height: 30px;\" required oninvalid=\"this.setCustomValidity('Enter the new password')\" oninput=\"this.setCustomValidity('')\">\r\n"
				+ "                <i id=\"eye-icon\" style=\"font-size: medium; cursor: pointer; width: max-content; position: relative; z-index: 1; top: 0; right: 35px; border-radius: 20px; padding: 0px; border: none;\" class=\"fas fa-eye-slash\"></i><br>\r\n"
				+ "                <input type=\"submit\" value=\"Reset\" style=\"border-radius: 8px; border: 0.5px solid grey; cursor: pointer; margin: 0 auto; display: block; margin-top: 10px; padding: 8px; font-size: medium;\">\r\n"
				+ "            </form>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "<script>\r\n"
				+ "    var passHide = true;\r\n"
				+ "    document.getElementById('eye-icon').addEventListener('click', function (e){\r\n"
				+ "        if(passHide){\r\n"
				+ "            passHide = false;\r\n"
				+ "            document.getElementById('password').setAttribute('type','text');\r\n"
				+ "            document.getElementById('eye-icon').classList.remove('fa-eye-slash');\r\n"
				+ "            document.getElementById('eye-icon').classList.add('fa-eye');\r\n"
				+ "            e.preventDefault();\r\n"
				+ "        }else{\r\n"
				+ "            passHide = true;\r\n"
				+ "            document.getElementById('password').setAttribute('type','password');\r\n"
				+ "            document.getElementById('eye-icon').classList.remove('fa-eye');\r\n"
				+ "            document.getElementById('eye-icon').classList.add('fa-eye-slash');\r\n"
				+ "            e.preventDefault();\r\n"
				+ "        }\r\n"
				+ "    });\r\n"
				+ "    function openEmailDiv(){\r\n"
				+ "        document.getElementById('email-div').style.display = 'block';\r\n"
				+ "        document.getElementById('security-question-div').style.display = 'none';\r\n"
				+ "        document.getElementById('reset-password-div').style.display = 'none';\r\n"
				+ "    }\r\n"
				+ "    function openSecurityQuestionDiv(){\r\n"
				+ "        document.getElementById('email-div').style.display = 'none';\r\n"
				+ "        document.getElementById('security-question-div').style.display = 'block';\r\n"
				+ "        document.getElementById('reset-password-div').style.display = 'none';\r\n"
				+ "    }\r\n"
				+ "    function openResetPasswordDiv(){\r\n"
				+ "        document.getElementById('email-div').style.display = 'none';\r\n"
				+ "        document.getElementById('security-question-div').style.display = 'none';\r\n"
				+ "        document.getElementById('reset-password-div').style.display = 'block';\r\n"
				+ "    }\r\n"
				+ "</script>\r\n"
				+ "</html>");
		forgotDAO dao;
		
		try {
			dao = new forgotDAO();
			if(req.getParameter("email")!=null) {
				HashMap<String,String> question = dao.getSecurityQuestionAnswer(req.getParameter("email"));
				out.append("<script>openSecurityQuestionDiv();document.getElementById('sec-email-input').value = '"+req.getParameter("email")+"'; document.getElementById('sec-question-span').innerText = '"+question.get("Question")+"';</script>");
			}
			if(req.getParameter("answer")!=null) {
				boolean answerCheck = dao.checkSecurityAnswer(req.getParameter("email"),req.getParameter("answer"));
				if(answerCheck) {
					out.append("<script>openResetPasswordDiv(); document.getElementById('error-p').style.display = 'none'; document.getElementById('reset-pass-email').value = '"+req.getParameter("email")+"';</script>");
				}else {
					out.append("<script>document.getElementById('error-p').style.display = 'block';</script>");
				}
			}
			if(req.getParameter("password")!=null) {
				boolean updateStatus = dao.updatePassword(req.getParameter("email"), req.getParameter("password"));
				out.append("<script>alert('Password reset successful'); window.location.href = 'home';</script>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
