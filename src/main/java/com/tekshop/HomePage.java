package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

public class HomePage extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HomePageDAO hpd = new HomePageDAO(res);
		List<HashMap<String,String>> products;
		try {
			products = hpd.getProducts();
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");
			out.write("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "\r\n"
					+ "<head>\r\n"
					+ "    <meta charset='utf-8'>\r\n"
					+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\r\n"
					+ "    <title>TekShop</title>\r\n"
					+ "    <meta name='viewport' content='width=device-width, initial-scale=1'>\r\n"
					+ "    <style>\r\n"
					+ "    	*{\r\n"
					+ "		    margin: 0;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		body{\r\n"
					+ "		    background-color: rgb(241, 244, 246);\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#top-div{\r\n"
					+ "		    width: 100%;\r\n"
					+ "		    text-align: center;\r\n"
					+ "			display: none;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#search-div{\r\n"
					+ "		    display: inline-block;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		input[type=search]{\r\n"
					+ "		    width: 250px;\r\n"
					+ "		    height: 40px;\r\n"
					+ "		    border-radius: 5px;\r\n"
					+ "		    border: 0.5px solid gainsboro;\r\n"
					+ "		    box-shadow: 1px 1px 1px grey;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#search-btn{\r\n"
					+ "		    padding: 10px;\r\n"
					+ "		    border-radius: 5px;\r\n"
					+ "		    border: none;\r\n"
					+ "		    box-shadow: 1px 1px 1px blueviolet;\r\n"
					+ "		    background-color: blueviolet;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#search-btn i{\r\n"
					+ "		    color: white;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#top-div h1{\r\n"
					+ "		    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\r\n"
					+ "		    font-style: oblique;\r\n"
					+ "		    color: brown;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		.root-div{\r\n"
					+ "		    display: none;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#products-div{\r\n"
					+ "		    margin: 5px;\r\n"
					+ "		    flex-wrap: wrap;\r\n"
					+ "		    display: none;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#sort-filter-div{\r\n"
					+ "		    width: max-content;\r\n"
					+ "		    margin: 5px;\r\n"
					+ "		    padding: 10px;\r\n"
					+ "		    padding-right: 70px;\r\n"
					+ "		    border-radius: 5px;\r\n"
					+ "			background-color: white;\r\n"
					+ "		    box-shadow: 0.5px 1px 1px 1.32px grey;\r\n"
					+ "		    border: none;\r\n"
					+ "			display: none;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#price-form{\r\n"
					+ "			margin: 0 auto; display: block;\r\n"
					+ "		}\r\n"
					+ "		#start-price,#end-price{\r\n"
					+ "			font-size: small;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		input[type=range]{\r\n"
					+ "		    width: max-content;\r\n"
					+ "		}\r\n"
					+ "		.cat-div span{\r\n"
					+ "		    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\r\n"
					+ "		}\r\n"
					+ "    </style>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n"
					+ "</head>\r\n"
					+ "\r\n"
					+ "<body>\r\n"
					+ "	<div id=\"login-div\" style=\"width: max-content; text-align: center; margin: 0 auto; margin-top: 15%; display: block;\">\r\n"
					+ "        <form action=\"login\" id=\"login-form\" method=\"post\">\r\n"
					+ "            <h2 style=\"font-size: xx-large; text-align: center; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Login</h2><br>\r\n"
					+ "            <div id=\"error-div\" style=\"display: none; background-color: rgb(244, 114, 114); border-radius: 5px; width: max-content; text-align: center; margin: 0 auto;\">\r\n"
					+ "                <div>\r\n"
					+ "                    <a style=\"color: white; cursor: pointer;\" onclick=\"document.getElementById('error-div').style.display = 'none';\">&cross;</a>\r\n"
					+ "                    <p id=\"error-p\" style=\"color: white; font-family: Verdana, Geneva, Tahoma, sans-serif;\"></p>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "            <input type=\"email\" placeholder=\"Email*\" name=\"email\" required style=\"width: 300px; font-size: large; text-align:center; font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif; margin: 5px; margin: auto; display: block; border-radius: 5px; border: 0.5px solid grey; height: 40px;\"><br>\r\n"
					+ "            <input type=\"password\" placeholder=\"Password*\" name=\"password\" required style=\"width: 300px; font-size: large; text-align:center; margin: 5px; margin: auto; display: block; font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif; border-radius: 5px; border: 0.5px solid grey; height: 40px;\"><br>\r\n"
					+ "            <input type=\"submit\" value=\"Login\" style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-size: medium;border-radius: 5px; padding: 10px; border: 0.5px solid blueviolet; margin: 0 auto; display: block; cursor: pointer;\">\r\n"
					+ "        </form>\r\n"
					+ " </div>"
					+ "    <div id=\"top-div\">\r\n"
					+ "        <div>\r\n"
					+ "            <div onclick=\"openCart()\" style=\"float: right; margin: 15px; cursor: pointer;\">\r\n"
					+ "                <span><i class=\"fa fa-shopping-cart\" style=\"color:rgb(120, 118, 224); font-size: xx-large;\"></i><sup id=\"cart-count\" style=\"background-color: grey; color: white; border-radius: 5px; padding-left: 3px; padding-right: 3px;\"></sup></span>\r\n"
					+ "            </div>\r\n"
					+ "            <div style=\"float: right; margin: 15px; cursor: pointer;\" onclick=\"logout();\">\r\n"
					+ "                <span><dfn><abbr id=\"user-name\"><i class=\"fa fa-user\" style=\"color:rgb(120, 118, 224); font-size: xx-large;\"></i></abbr></dfn></span>\r\n"
					+ "            </div>\r\n"
					+ "            <h1>TekShop</h1>\r\n"
					+ "        </div>\r\n"
					+ "        <div id=\"search-div\">\r\n"
					+ "            <form id=\"search-form\">\r\n"
					+ "                <input type=\"search\" placeholder=\"Search\" id=\"search-input\" name=\"search-input\">\r\n"
					+ "                <button id=\"search-btn\" type=\"submit\"><i class=\"fa fa-search\"></i></button>\r\n"
					+ "            </form>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div id=\"message-div\" style=\"display: none; border: 3px solid rgb(103, 208, 103); border-radius: 5px; background-color: rgb(208, 245, 233); margin: 5px;\r\n"
					+ "    padding: 10px; text-align: center;\">\r\n"
					+ "        <p><i>&check;&nbsp;</i><span id=\"message-span\" style=\"font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;\"> Added to cart</span></p>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"root-div\" id=\"root-div\">\r\n"
					+ "        <div id=\"sort-filter-div\">\r\n"
					+ "            <div>\r\n"
					+ "					<form id=\"price-form\">\r\n"	
					+ "                		<p style=\"font-size: medium; font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;\"><b>Price range</b></p><br>\r\n"
					+ "						<span style=\"float: left;\" id=\"start-price\"></span>\r\n"
					+ "						<span style=\"float: right;\" id=\"end-price\"></span>\r\n"
					+ "                		<input type=\"range\" id=\"price-range\"><br>\r\n"
					+ "						<select name=\"constraint\" style=\"border: 0.5px solid grey; border-radius: 3px;\">\r\n"
					+ "							<option value=\">\">> Greater than</option>\r\n"
					+ "							<option value=\"<\">< Less than</option>\r\n"
					+ "							<option value=\">=\">>= Greater than equal</option>\r\n"
					+ "							<option value=\"<=\"><= Less than equal</option>\r\n"
					+ "							<option value=\"==\">== Equals</option>\r\n"
					+ "						</select>\r\n"
					+ "				   		<input type=\"hidden\" id=\"price\" name=\"price-range\" style=\"display: none;\">\r\n"
					+ "                 </form>\r\n"
					+ "            </div><br>\r\n"
					+ "            <div class=\"cat-div\">\r\n"
					+ "                <form id=\"cat-form\">\r\n"
					+ "                    <p><b>Category:</b></p>\r\n"
					+ "                    <div>\r\n"
					+ "                        <input type=\"checkbox\" value=\"Apparels_Accessories\"><span>&nbsp;Apparels & Accessories</span><br>\r\n"
					+ "                        <input type=\"checkbox\" value=\"Style_Fashion\"><span>&nbsp;Style & Fashion</span><br>\r\n"
					+ "                        <input type=\"checkbox\" value=\"Home_Garden\"><span>&nbsp;Home & Garden</span><br>\r\n"
					+ "                        <input type=\"checkbox\" value=\"Sporting_Goods\"><span>&nbsp;Sporting Goods</span><br>\r\n"
					+ "                        <input type=\"checkbox\" value=\"Health_Wellness\"><span>&nbsp;Health & Wellness</span><br>\r\n"
					+ "                        <input type=\"checkbox\" value=\"Children_Infants\"><span>&nbsp;Children & Infants</span><br>\r\n"
					+ "                        <input type=\"checkbox\" value=\"Groceries_Food_Drinks\"><span>&nbsp;Groceries, Food and Drinks</span><br>\r\n"
					+ "                        <input type=\"checkbox\" value=\"Flowers_Gifts\"><span>&nbsp;Flowers and Gifts</span>\r\n"
					+ "                        <input type=\"hidden\" id=\"categories\" name=\"categories\">\r\n"
					+ "                    </div>\r\n"
					+ "                </form>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "        <div id=\"products-div\">\r\n"
					+ "            \r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "</body>\r\n"
					+ "<script>\r\n"
					+ "	\r\n"
					+ " var categories = document.querySelectorAll(\"input[type=checkbox]\");\r\n"
					+ "\r\n"
					+ "    var category = [];\r\n"
					+ "    \r\n"
					+ "    for(let cat=0;cat<categories.length;cat++){\r\n"
					+ "        categories[cat].addEventListener('change',function(){\r\n"
					+ "            if(this.checked){\r\n"
					+ "                category.push(categories[cat].value);\r\n"
					+ "				   document.getElementById('categories').value = category;\r\n"
					+ "				   document.getElementById('cat-form').submit();"
					+ "            }else{\r\n"
					+ "                for(let i=0;i<category.length;i++){\r\n"
					+ "                    if(category[i]==categories[cat].value){\r\n"
					+ "                        delete category[i];\r\n"
					+ "				   		   document.getElementById('categories').value = category;\r\n"
					+ "                    }\r\n"
					+ "                }\r\n"
					+ "            }\r\n"
					+ "        });\r\n"
					+ "    }"
					+ "	document.getElementById(\"price-range\").addEventListener('change',function(){\r\n"
					+ "	    document.getElementById('price').value = document.getElementById('price-range').value;\r\n"
					+ "		document.getElementById('price-form').submit();\r\n"
					+ "	});\r\n"
					+ "\r\n"
					+ "function logout(){\r\n"
					+ "		if(confirm('Are you sure to logout from this site?')){\r\n"
					+ "			localStorage.removeItem('user_id'); location.reload();\r\n"
					+ "		}\r\n"
					+ "}\r\n"
					+ " document.getElementById('price-form').onsubmit = function(e){"
					+ "		document.getElementById('products-div').innerHTML = '';\r\n"
					+ "};\r\n"
					+ "	\r\n"
					+ "\r\n"
					+ " document.getElementById('cat-form').onsubmit = function(e){"
					+ "		document.getElementById('products-div').innerHTML = '';\r\n"
					+ "};\r\n"
					+ "document.getElementById('user-name').title = 'User: '+localStorage.getItem('user_id');"
					+ "	\r\n"
					+ "	document.getElementById(\"search-btn\").addEventListener('click',function(){\r\n"
					+ "	    var searchInput = document.getElementById('search-input').value;\r\n"
					+ "	    if(searchInput.length>0){\r\n"
					+ "	        searchProduct(searchInput);\r\n"
					+ "	    }\r\n"
					+ "	});\r\n"
					+ "	\r\n"
					+ "	function openCart(){\r\n"
					+ "	    window.location.href = \"cart\";\r\n"
					+ "	}\r\n"
					+ "\r\n"
					+ "		if(localStorage.getItem('user_id')!=null){\r\n"
					+ "			document.getElementById('top-div').style.display = 'block';\r\n"
					+ "			document.getElementById('root-div').style.display = 'flex';\r\n"
					+ "			document.getElementById('login-div').style.display = 'none';\r\n"
					+ "			document.getElementById('products-div').style.display = 'flex';\r\n"
					+ "			document.getElementById('sort-filter-div').style.display = 'block';\r\n"
					+ "		}\r\n"
					+ "		else{\r\n"
					+ "			document.getElementById('top-div').style.display = 'none';\r\n"
					+ "			document.getElementById('root-div').style.display = 'none';\r\n"
					+ "			document.getElementById('login-div').style.display = 'block';\r\n"
					+ "			document.getElementById('products-div').style.display = 'none';\r\n"
					+ "			document.getElementById('sort-filter-div').style.display = 'none';\r\n"
					+ "		}\r\n"
					+ "	\r\n"
					+ "	function populateProduct(prod_id,image,category,prod_name,prod_price){\r\n"
					+ "		document.getElementById('root-div').style.display = 'flex';\r\n"
					+ "	    if(true){\r\n"
					+ "	        var div = document.createElement('div');\r\n"
					+ "	        div.style = \"border: 0.1px solid rgb(230, 206, 206); border-radius:5px; width: max-content; margin: 5px; padding: 10px; background-color:white;\";\r\n"
					+ "	\r\n"
					+ "	        var img = document.createElement('img');\r\n"
					+ "	        img.src = getProductImage(category);\r\n"
					+ "	        img.style = \"width:200px; margin:0 auto; display:block;\";\r\n"
					+ "	        img.alt = prod_name+\"_img\";\r\n"
					+ "	\r\n"
					+ "	        var p = document.createElement('p');\r\n"
					+ "	        var b = document.createElement('b');\r\n"
					+ "	\r\n"
					+ "	        p.appendChild(b);\r\n"
					+ "	        b.innerText = prod_name;\r\n"
					+ "	        b.style.fontFamily = \"'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif\";\r\n"
					+ "	        b.style.fontSize = \"large\";\r\n"
					+ "	        p.style.textAlign = \"center\";\r\n"
					+ "	\r\n"
					+ "	        var p1 = document.createElement('p');\r\n"
					+ "	        var b1 = document.createElement('b');\r\n"
					+ "	        b1.innerText = \"Price: \";\r\n"
					+ "	        b1.style.fontFamily = \"Lucida Sans, 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif\";\r\n"
					+ "	        p1.appendChild(b1);\r\n"
					+ "	        var span = document.createElement('span');\r\n"
					+ "	        span.innerText = \"₹ \"+prod_price+\"/-\";\r\n"
					+ "	        span.style.fontSize = \"20px\";\r\n"
					+ "	        span.style.color = \"green\";\r\n"
					+ "	        span.style.fontFamily = \"Arial, Helvetica, sans-serif\";\r\n"
					+ "	        span.style.fontStyle = \"italic\";\r\n"
					+ "	        p1.appendChild(span);\r\n"
					+ "	\r\n"
					+ "	        var div1 = document.createElement('div');\r\n"
					+ "	        div1.style = \"width: max-content; height: fit-content; display: block; margin: 0 auto;\";\r\n"
					+ "	\r\n"
					+ "	        var view_details_btn = document.createElement('button');\r\n"
					+ "	        var view_details_icon = document.createElement('i');\r\n"
					+ "	        view_details_icon.classList.add(\"fa\");\r\n"
					+ "	        view_details_icon.classList.add(\"fa-info-circle\");\r\n"
					+ "	        view_details_icon.style.fontSize = \"large\";\r\n"
					+ "	\r\n"
					+ "	        view_details_btn.innerText = \"View details \";\r\n"
					+ "	\r\n"
					+ "	        view_details_btn.appendChild(view_details_icon);\r\n"
					+ "	        view_details_btn.style.margin = \"5px\";\r\n"
					+ "	        view_details_btn.style = \"cursor: pointer; border: 0.5px solid blue; border-radius: 3px; background-color:blue; color: white;\";\r\n"
					+ "	\r\n"
					+ "	        var purchase_btn = document.createElement('button');\r\n"
					+ "	        var purchase_icon = document.createElement('i');\r\n"
					+ "	        purchase_icon.classList.add(\"fa\");\r\n"
					+ "	        purchase_icon.classList.add(\"fa-shopping-bag\");\r\n"
					+ "	        purchase_icon.style.fontSize = \"large\";\r\n"
					+ "	        purchase_btn.innerText = \"Purchase \";\r\n"
					+ "\r\n"
					+ "	        purchase_btn.appendChild(purchase_icon);\r\n"
					+ "	        purchase_btn.style = \"cursor: pointer; border: 0.5px solid blue; border-radius: 3px; background-color:blue; color: white; margin: 5px;\";\r\n"
					+ "\r\n"
					+ "	        var purchaseForm = document.createElement('form');\r\n"
					+ "	        purchaseForm.setAttribute('action','AddCart');\r\n"
					+ "			purchaseForm.setAttribute('method','post');\r\n"
					+ "			var purchaseInput = document.createElement('input')\r\n"
					+ "			purchaseInput.value = prod_id+';'+localStorage.getItem('user_id');\r\n"
					+ "			purchaseInput.name = \"product\"\r\n"
					+ "			purchaseInput.setAttribute('type','hidden');\r\n"
					+ "	        purchaseForm.appendChild(purchaseInput);\r\n"
					+ "			purchaseForm.appendChild(purchase_btn);\r\n"
					+ "\r\n"
					+ "	        div1.appendChild(view_details_btn);\r\n"
					+ "	        div1.appendChild(purchaseForm);\r\n"
					+ "	\r\n"
					+ "	        div.appendChild(img);\r\n"
					+ "	        div.appendChild(p);\r\n"
					+ "	        div.appendChild(p1);\r\n"
					+ "	        div.appendChild(div1);\r\n"
					+ "	\r\n"
					+ "	        view_details_btn.addEventListener('click',function(){\r\n"
					+ "	            window.open(\"ViewProduct?ProductID=\"+prod_id,\"_blank\",\"width:200px,height:200px\");\r\n"
					+ "	        });\r\n"
					+ "	        view_details_btn.addEventListener('mouseover',function(){\r\n"
					+ "	            view_details_btn.style = 'box-shadow: 1px 1px 2px grey; cursor: pointer; border: 0.5px solid blue; border-radius: 3px; background-color:blue; color: white; margin: 5px;';\r\n"
					+ "	        });\r\n"
					+ "	\r\n"
					+ "	        purchase_btn.addEventListener('mouseover',function(){\r\n"
					+ "	           purchase_btn.style = 'box-shadow: 1px 1px 1px grey; cursor: pointer; border: 0.5px solid blue; border-radius: 3px; background-color:blue; color: white; margin: 5px';\r\n"
					+ "	        });\r\n"
					+ "	        view_details_btn.addEventListener('mouseout',function(){\r\n"
					+ "	            view_details_btn.style = 'box-shadow: none; cursor: pointer; border: 0.5px solid blue; border-radius: 3px; background-color:blue; color: white; margin: 5px';\r\n"
					+ "	        });\r\n"
					+ "	\r\n"
					+ "	        purchase_btn.addEventListener('mouseout',function(){\r\n"
					+ "	           purchase_btn.style = 'box-shadow: none; cursor: pointer; border: 0.5px solid blue; border-radius: 3px; background-color:blue; color: white; margin: 5px';\r\n"
					+ "	        });\r\n"
					+ "	        purchaseForm.addEventListener('onsubmit',function(){\r\n"
					+ "				\r\n"
					+ "	        });\r\n"
					+ "	\r\n"
					+ "	        document.getElementById(\"products-div\").appendChild(div);\r\n"
					+ "	    }\r\n"
					+ "	}\r\n"
					+ "	\r\n"
					+ "		document.getElementById('cart-count').innerText = '"+HomePageDAO.countCartItems()+"';\r\n"
					+ " \r\n"
					+ "    function getProductImage(category){\r\n"
					+ "	    imagePath = \"\";\r\n"
					+ "	    switch (category) {\r\n"
					+ "	        case \"Apparels_Accessories\":\r\n"
					+ "	            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494689/Tekshop/apparels-accessories_uc5o8e.jpg\";\r\n"
					+ "	            break;\r\n"
					+ "	        case \"Style_Fashion\":\r\n"
					+ "	            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494689/Tekshop/style-fashion_zghqmi.jpg\";\r\n"
					+ "	            break;\r\n"
					+ "	        case \"Home_Garden\":\r\n"
					+ "	            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494689/Tekshop/home-garden_qizt5h.jpg\";\r\n"
					+ "	            break;\r\n"
					+ "	        case \"Sporting_Goods\":\r\n"
					+ "	            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494689/Tekshop/sport-goods_bwx7ct.jpg\";\r\n"
					+ "	            break;\r\n"
					+ "	        case \"Health_Wellness\":\r\n"
					+ "	            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494688/Tekshop/health-wellness_nnms3f.png\";\r\n"
					+ "	            break;\r\n"
					+ "	        case \"Children_Infants\":\r\n"
					+ "	            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494687/Tekshop/children-infant_x7vls8.png\";\r\n"
					+ "	            break;\r\n"
					+ "	        case \"Groceries_Food_Drinks\":\r\n"
					+ "	            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494688/Tekshop/groceries_h5drlm.jpg\";\r\n"
					+ "	            break;\r\n"
					+ "	        case \"Flowers_Gifts\":\r\n"
					+ "	            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494688/Tekshop/flower-gift_zvrgvp.jpg\";\r\n"
					+ "	            break;\r\n"
					+ "	        default:\r\n"
					+ "	            break;\r\n"
					+ "	    }\r\n"
					+ "	    return imagePath;\r\n"
					+ "	}\r\n"
					+ "	\r\n"
					+ "	\r\n"
					+ "	function filterByPrice(){\r\n"
					+ "	    var productsDiv = document.getElementById(\"products-div\");\r\n"
					+ "	    productsDiv.innerHTML = \"\";\r\n"
					+ "	}\r\n"
					+ "	\r\n"
					+ "	function filterByCategory(){\r\n"
					+ "	\r\n"
					+ "	}\r\n"
					+ "	\r\n"
					+ "</script>\r\n"
					+ "</html>");
			
			int max = 0;
			int min = 0;
			for(HashMap<String,String> map: products) {
				if(Boolean.parseBoolean(map.get("ActiveFlag"))==true && Integer.parseInt(map.get("Product_Inventory"))>0) {
					if(Integer.parseInt(map.get("Product_Unit_Price"))>max) {
						max = Integer.parseInt(map.get("Product_Unit_Price"));
					}
					
					out.append("\n<script>populateProduct('"+map.get("Product_ID")+"','"+map.get("Product_Image")+"','"+map.get("Product_Category")+"','"+map.get("Product_Name")+"','"+map.get("Product_Unit_Price")+"');</script>");
					out.append("\n<script>document.getElementById('start-price').innerText = '"+min+"'; document.getElementById('end-price').innerText = '"+max+"'; document.getElementById('price-range').setAttribute('max','"+max+"');document.getElementById('price-range').setAttribute('min','"+min+"');</script>");
				}
			}
			
			if(req.getParameter("search-input")!=null) {
				out.append("<script>document.getElementById('products-div').innerHTML = '';</script>");
				for(HashMap<String,String> map: products) {
					if(map.get("Product_ID").equals(req.getParameter("search-input")) || map.get("Product_Name").equals(req.getParameter("search-input"))) {
						out.append("\n<script>populateProduct('"+map.get("Product_ID")+"','"+map.get("Product_Image")+"','"+map.get("Product_Category")+"','"+map.get("Product_Name")+"','"+map.get("Product_Unit_Price")+"');</script>");
					}
				}
			}
			
			if(req.getParameter("categories")!=null) {
				out.append("<script>document.getElementById('products-div').innerHTML = '';</script>");
				String categories[] = req.getParameter("categories").split(",");
				for(HashMap<String,String> map: products) {
					for(String category: categories) {
						if(!category.equals("") && category.equals(map.get("Product_Category"))) {
							out.append("\n<script>populateProduct('"+map.get("Product_ID")+"','"+map.get("Product_Image")+"','"+map.get("Product_Category")+"','"+map.get("Product_Name")+"','"+map.get("Product_Unit_Price")+"');</script>");
						}
					}
				}
			}
			
			if(req.getParameter("price-range")!=null && req.getParameter("constraint")!=null) {
				out.append("<script>document.getElementById('products-div').innerHTML = '';</script>");
				for(HashMap<String,String> map: products) {
					switch(req.getParameter("constraint")) {
						case ">": 
								if(Integer.parseInt(map.get("Product_Unit_Price"))>Integer.parseInt(req.getParameter("price-range"))) {
									out.append("\n<script>populateProduct('"+map.get("Product_ID")+"','"+map.get("Product_Image")+"','"+map.get("Product_Category")+"','"+map.get("Product_Name")+"','"+map.get("Product_Unit_Price")+"');</script>");
								}
								break;
						case "<": 
								if(Integer.parseInt(map.get("Product_Unit_Price"))<Integer.parseInt(req.getParameter("price-range"))) {
									out.append("\n<script>populateProduct('"+map.get("Product_ID")+"','"+map.get("Product_Image")+"','"+map.get("Product_Category")+"','"+map.get("Product_Name")+"','"+map.get("Product_Unit_Price")+"');</script>");
								}
								break;
						case "==": 
								if(Integer.parseInt(map.get("Product_Unit_Price"))==Integer.parseInt(req.getParameter("price-range"))) {
									out.append("\n<script>populateProduct('"+map.get("Product_ID")+"','"+map.get("Product_Image")+"','"+map.get("Product_Category")+"','"+map.get("Product_Name")+"','"+map.get("Product_Unit_Price")+"');</script>");
								}
								break;
						case ">=": 
								if(Integer.parseInt(map.get("Product_Unit_Price"))>=Integer.parseInt(req.getParameter("price-range"))) {
									out.append("\n<script>populateProduct('"+map.get("Product_ID")+"','"+map.get("Product_Image")+"','"+map.get("Product_Category")+"','"+map.get("Product_Name")+"','"+map.get("Product_Unit_Price")+"');</script>");
								}
								break;
						case "<=": 
								if(Integer.parseInt(map.get("Product_Unit_Price"))<=Integer.parseInt(req.getParameter("price-range"))) {
									out.append("\n<script>populateProduct('"+map.get("Product_ID")+"','"+map.get("Product_Image")+"','"+map.get("Product_Category")+"','"+map.get("Product_Name")+"','"+map.get("Product_Unit_Price")+"');</script>");
								}
								break;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
