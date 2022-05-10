package com.tekshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductUpdate extends HttpServlet{
	
	Connection con;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.write("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "    <meta charset='utf-8'>\r\n"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\r\n"
				+ "    <title>Update Product</title>\r\n"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1'>\r\n"
				+ "    <style>\r\n"
				+ "        #top-div{\r\n"
				+ "            width: 100%;\r\n"
				+ "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        #search-div{\r\n"
				+ "            display: inline-block;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        input[type=search]{\r\n"
				+ "            width: 250px;\r\n"
				+ "            height: 40px;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            border: 0.5px solid gainsboro;\r\n"
				+ "            box-shadow: 1px 1px 1px grey;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        #search-btn{\r\n"
				+ "            padding: 10px;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            border: none;\r\n"
				+ "            cursor: pointer;\r\n"
				+ "            box-shadow: 1px 1px 1px blueviolet;\r\n"
				+ "            background-color: blueviolet;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        #search-btn i{\r\n"
				+ "            color: white;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        #top-div h1{\r\n"
				+ "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\r\n"
				+ "            font-style: oblique;\r\n"
				+ "            color: brown;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div id=\"top-div\">\r\n"
				+ "        <h1>TekShop</h1>\r\n"
				+ "        <div id=\"search-div\">\r\n"
				+ "            <form id=\"search-form\">\r\n"
				+ "                <input type=\"search\" placeholder=\"Search\" id=\"search-input\" name=\"search-input\">\r\n"
				+ "                <button id=\"search-btn\" type=\"submit\"><i class=\"fa fa-search\"></i></button>\r\n"
				+ "            </form>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "    <br><br>"
				+ "		<div id=\"form-div\" style=\"border: 0.5px solid grey; width: max-content; margin: 0 auto; display: none;\">\r\n"
				+ "        <form action=\"ProductUpdate\" style=\"padding: 10px;width: max-content; margin: 0 auto;\" id=\"update-form\" method=\"post\">\r\n"
				+ "            <h2 style=\"text-align: center; font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\">Update</h2>\r\n"
				+ "            <span>Product ID: </span><input type=\"text\" name=\"prod_id\" id=\"prod_id\"><br><br>\r\n"
				+ "            <span>Product Name: </span><input type=\"text\" name=\"prod_name\" id=\"prod_input\"><br><br>\r\n"
				+ "            <span>Category: </span>\r\n"
				+ "            <select id=\"category-select\" name=\"category\">\r\n"
				+ "                <option value=\"Apparel_Accessories\">Apparel & Accessories</option>\r\n"
				+ "                <option value=\"Style_Fashion\">Style & Fashion</option>\r\n"
				+ "                <option value=\"Home_Garden\">Home & Garden</option>\r\n"
				+ "                <option value=\"Sporting_Goods\">Sporting Goods</option>\r\n"
				+ "                <option value=\"Health_Wellness\">Health & Wellness</option>\r\n"
				+ "                <option value=\"Children_Infants\">Children & Infants</option>\r\n"
				+ "                <option value=\"Groceries_Food_Drinks\">Groceries, Food and Drinks</option>\r\n"
				+ "                <option value=\"Flowers_Gifts\">Flowers and Gifts</option>\r\n"
				+ "            </select><br><br>\r\n"
				+ "            <span>Unit Price: </span><input type=\"number\" name=\"price\" id=\"price-input\"><br><br>\r\n"
				+ "            <span>Inventory Level: </span><input type=\"number\" name=\"inventory-level\" id=\"inv-input\"><br><br>\r\n"
				+ "            <span>Active: </span><input type=\"radio\" value=\"Yes\" id=\"yes-radio\" name=\"yes-radio\"><span>Yes</span> &nbsp; <input type=\"radio\" value=\"No\" id=\"no-radio\" name=\"no-radio\"><span>No</span><br><br>\r\n"
				+ "            <button type=\"submit\" style=\"margin: 0 auto; display: block;\" onclick=\"updateProduct();\">Update</button>\r\n"
				+ "        </form>\r\n"
				+ "    </div>\r\n"
				+ "    <div id=\"root-div\" style=\"display: flex;\">\r\n"
				+ "    </div>\r\n"
				+ "\r\n"
				+ "    <script>\r\n"
				+ "\r\n"
				+ "        document.getElementById('yes-radio').addEventListener('click',function() {\r\n"
				+ "            document.getElementById('no-radio').checked = false;\r\n"
				+ "        });\r\n"
				+ "\r\n"
				+ "        document.getElementById('no-radio').addEventListener('click',function() {\r\n"
				+ "            document.getElementById('yes-radio').checked = false;\r\n"
				+ "        });\r\n"
				+ "\r\n"
				+ "        function populateProduct(prod_id,prodName,category,price,invLvl,active){\r\n"
				+ "            var div = document.createElement('div');\r\n"
				+ "            div.style = \"border: 0.5px solid grey; width: max-content; border-radius: 5px; margin: 5px; padding: 10px; background-color: rgb(241, 244, 246)\";\r\n"
				+ "\r\n"
				+ "            var innerDiv = document.createElement('div');\r\n"
				+ "            innerDiv.style = \"display: flex\";\r\n"
				+ "\r\n"
				+ "            var nameP = document.createElement('p');\r\n"
				+ "            nameP.innerHTML = prodName;\r\n"
				+ "\r\n"
				+ "            var editIcon = document.createElement('i');\r\n"
				+ "            editIcon.classList.add('fa');\r\n"
				+ "            editIcon.classList.add('fa-edit');\r\n"
				+ "            editIcon.style = \"width:70px; text-align: end; cursor:pointer\";\r\n"
				+ "            editIcon.addEventListener('click',function(){\r\n"
				+ "                document.getElementById('form-div').style.display = 'block';\r\n"
				+ "                document.getElementById('prod_input').value = prodName;\r\n"
				+ "                document.getElementById('category-select').value = category;\r\n"
				+ "                document.getElementById('price-input').value = price;\r\n"
				+ "                document.getElementById('inv-input').value = invLvl;\r\n"
				+ "                document.getElementById('prod_id').value = prod_id;\r\n"
				+ "                \r\n"
				+ "                if(active){\r\n"
				+ "                    document.getElementById('yes-radio').checked = true;\r\n"
				+ "                }else{\r\n"
				+ "                    document.getElementById('no-radio').checked = true;\r\n"
				+ "                }\r\n"
				+ "            });\r\n"
				+ "\r\n"
				+ "            innerDiv.appendChild(nameP);\r\n"
				+ "            innerDiv.appendChild(editIcon);\r\n"
				+ "\r\n"
				+ "            var catP = document.createElement('p');\r\n"
				+ "            var catB = document.createElement('b');\r\n"
				+ "            catB.innerText = \"Category: \";\r\n"
				+ "            catP.appendChild(catB);\r\n"
				+ "            var catSpan = document.createElement('span');\r\n"
				+ "            catSpan.innerText = category;\r\n"
				+ "            catP.appendChild(catSpan);\r\n"
				+ "\r\n"
				+ "            var priceP = document.createElement('p');\r\n"
				+ "            var priceB = document.createElement('b');\r\n"
				+ "            priceB.innerText = \"Price: \";\r\n"
				+ "            priceP.appendChild(priceB);\r\n"
				+ "            var priceSpan = document.createElement('span');\r\n"
				+ "            priceSpan.innerText = price;\r\n"
				+ "            priceP.appendChild(priceSpan);\r\n"
				+ "\r\n"
				+ "            var invP = document.createElement('p');\r\n"
				+ "            var invB = document.createElement('b');\r\n"
				+ "            invB.innerText = \"Inventory Level: \";\r\n"
				+ "            invP.appendChild(invB);\r\n"
				+ "            var invSpan = document.createElement('span');\r\n"
				+ "            invSpan.innerText = invLvl;\r\n"
				+ "            invP.appendChild(invSpan);\r\n"
				+ "\r\n"
				+ "            var activeP = document.createElement('p');\r\n"
				+ "            var activeB = document.createElement('b');\r\n"
				+ "            activeB.innerText = \"Active: \";\r\n"
				+ "            activeP.appendChild(activeB);\r\n"
				+ "            var activeSpan = document.createElement('span');\r\n"
				+ "            activeSpan.innerText = active;\r\n"
				+ "            activeP.appendChild(activeSpan);\r\n"
				+ "\r\n"
				+ "            div.appendChild(innerDiv);\r\n"
				+ "            div.appendChild(catP);\r\n"
				+ "            div.appendChild(priceP);\r\n"
				+ "            div.appendChild(invP);\r\n"
				+ "            div.appendChild(activeP);\r\n"
				+ "\r\n"
				+ "            document.getElementById('root-div').appendChild(div);\r\n"
				+ "        }\r\n"
				+ "    </script>\r\n"
				+ "</body>\r\n"
				+ "</html>");
		
		String query = "select product_id,product_name,product_category,product_unit_price,product_inventory_level,active_flag from products";
		
		try {
			Class.forName(DAO.driverType);
			con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				out.append("\n<script>populateProduct('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getInt(4)+"','"+rs.getInt(5)+"','"+rs.getBoolean(6)+"');</script>");
			}
			
			String activeYes = req.getParameter("yes-radio");
			String activeNo = req.getParameter("no-radio");
			boolean active = false;
			
			if(activeYes!=null) {
				active = true;
			}
			ProductUpdateDAO productUpdateDAO = new ProductUpdateDAO(req.getParameter("prod_name"), req.getParameter("inventory-level"), req.getParameter("price"), req.getParameter("category"), active);
			out.append("<script>document.getElementById('update-form').onsubmit = '"+productUpdateDAO.updateProduct(req.getParameter("prod_id"))+"'</script>");
			
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("select product_id,product_name,product_category,product_unit_price,product_inventory_level,active_flag from products where product_id='"+req.getParameter("search-input")+"'");
			
			if(rs1.next()) {
				out.append("\n<script>document.getElementById('root-div').innerHTML = ''; document.getElementById('search-form').onsubmit = populateProduct('"+rs1.getString(1)+"','"+rs1.getString(2)+"','"+rs1.getString(3)+"','"+rs1.getInt(4)+"','"+rs1.getInt(5)+"','"+rs1.getBoolean(6)+"')</script>");
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
