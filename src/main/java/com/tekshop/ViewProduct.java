package com.tekshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewProduct extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String productId = req.getParameter("ProductID");
		
		ResultSet rs;
		try {
			rs = ViewProductDAO.getProduct(productId);
			
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");
			out.write("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "    <meta charset='utf-8'>\r\n"
					+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\r\n"
					+ "    <title>Product</title>\r\n"
					+ "    <meta name='viewport' content='width=device-width, initial-scale=1'>\r\n"
					+ "	   <script src=\"https://maps.googleapis.com/maps/api/js?libraries=places&language=en&key=AIzaSyCimZAe6VhvRDsKbQVjxS3G6UguQyAyHVg\" type=\"text/javascript\"></script>\r\n"
					+ "    <style>\r\n"
					+ "    	#prod_name{\r\n"
					+ "		    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\r\n"
					+ "		    font-size: x-large;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#prod_price{\r\n"
					+ "		    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;\r\n"
					+ "		    font-size: xx-large;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		#short_desc_span,#long_desc_span{\r\n"
					+ "		    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\r\n"
					+ "		    font-size: small;\r\n"
					+ "		}\r\n"
					+ "		\r\n"
					+ "		.headings-b{\r\n"
					+ "		    font-family: cursive;\r\n"
					+ "		    font-weight: bold;\r\n"
					+ "		}\r\n"
					+ "		#delivery-date-i{\r\n"
					+ "			display: none;\r\n"
					+ "			color: green;\r\n"
					+ "			font-family: sans-serif;\r\n"
					+ "		}\r\n"
					+ "    </style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <div>\r\n"
					+ "        <img id=\"prod_img\" src=\"\" width=\"740\" height=\"500\" alt=\"\">\r\n"
					+ "        <p><b class=\"headings-b\" id=\"prod_name\"></b></p>\r\n"
					+ "        <p><b class=\"headings-b\">Price: </b><span id=\"prod_price\"></span></p>\r\n"
					+ "		   <p><b class=\"headings-b\">Check delivery date: </b><form id=\"check-delivery-form\" action=\"CheckDelivery\"><input type='number' id=\"pincode-input\" name=\"pin-input\">&nbsp;<button type=\"submit\">Check</button></form></p>\r\n"
					+ "		   <span id=\"delivery-date-i\"></span>\r\n"
					+ "        <p><b class=\"headings-b\">Short Description</b><br>&nbsp;&nbsp;&nbsp;<span id=\"short_desc_span\"></span></p>\r\n"
					+ "        <p><b class=\"headings-b\">Long Description</b><br>&nbsp;&nbsp;&nbsp;<span id=\"long_desc_span\"></span></p>\r\n"
					+ "    </div>\r\n"
					+ "</body>\r\n"
					+ "<script>\r\n"
					+ "\r\n"
					+ "    function showProduct(productCategory,productName,productUnitPrice,productShortDesc,productLongDesc){\r\n"
					+ "        document.getElementById(\"prod_img\").src = getProductImage(productCategory);\r\n"
					+ "        document.getElementById(\"prod_name\").innerHTML = productName;\r\n"
					+ "        document.getElementById(\"prod_price\").innerHTML = \"₹ \"+productUnitPrice+\"/-\";\r\n"
					+ "        document.getElementById(\"short_desc_span\").innerHTML = productShortDesc;\r\n"
					+ "        document.getElementById(\"long_desc_span\").innerHTML = productLongDesc;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "	function getProductImage(category){\r\n"
					+ "	    imagePath = \"\";\r\n"
					+ "	    switch (category) {\r\n"
					+ "	        case \"Apparels_Accessories\":\r\n"
					+ "            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494689/Tekshop/apparels-accessories_uc5o8e.jpg\";\r\n"
					+ "            break;\r\n"
					+ "        case \"Style_Fashion\":\r\n"
					+ "            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494689/Tekshop/style-fashion_zghqmi.jpg\";\r\n"
					+ "            break;\r\n"
					+ "        case \"Home_Garden\":\r\n"
					+ "            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494689/Tekshop/home-garden_qizt5h.jpg\";\r\n"
					+ "            break;\r\n"
					+ "        case \"Sporting_Goods\":\r\n"
					+ "            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494689/Tekshop/sport-goods_bwx7ct.jpg\";\r\n"
					+ "            break;\r\n"
					+ "        case \"Health_Wellness\":\r\n"
					+ "            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494688/Tekshop/health-wellness_nnms3f.png\";\r\n"
					+ "            break;\r\n"
					+ "        case \"Children_Infants\":\r\n"
					+ "            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494687/Tekshop/children-infant_x7vls8.png\";\r\n"
					+ "            break;\r\n"
					+ "        case \"Groceries_Food_Drinks\":\r\n"
					+ "            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494688/Tekshop/groceries_h5drlm.jpg\";\r\n"
					+ "            break;\r\n"
					+ "        case \"Flowers_Gifts\":\r\n"
					+ "            imagePath = \"https://res.cloudinary.com/chennupati-balu/image/upload/v1651494688/Tekshop/flower-gift_zvrgvp.jpg\";\r\n"
					+ "            break;\r\n"
					+ "	        default:\r\n"
					+ "	            break;\r\n"
					+ "	    }\r\n"
					+ "	    return imagePath;\r\n"
					+ "	}\r\n"
					+ "</script>\r\n"
					+ "<script>\r\n"
					+ "        // calculate distance\r\n"
					+ "        function calculateDistance() {\r\n"
					+ "            var origin = '524228';\r\n"
					+ "            var destination = document.getElementById('pincode-input').value;\r\n"
					+ "            var service = new google.maps.DistanceMatrixService();\r\n"
					+ "            service.getDistanceMatrix(\r\n"
					+ "                {\r\n"
					+ "                    origins: [origin],\r\n"
					+ "                    destinations: [destination],\r\n"
					+ "                    travelMode: google.maps.TravelMode.DRIVING,\r\n"
					+ "                    unitSystem: google.maps.UnitSystem.metric, // kilometers and meters.\r\n"
					+ "                    avoidHighways: false,\r\n"
					+ "                    avoidTolls: false\r\n"
					+ "                }, callback);\r\n"
					+ "        }\r\n"
					+ "        // get distance results\r\n"
					+ "        function callback(response, status) {\r\n"
					+ "            if (status != google.maps.DistanceMatrixStatus.OK) {\r\n"
					+ "                alert(err);\r\n"
					+ "            } else {\r\n"
					+ "                var origin = response.originAddresses[0];\r\n"
					+ "                var destination = response.destinationAddresses[0];\r\n"
					+ "                if (response.rows[0].elements[0].status === \"ZERO_RESULTS\") {\r\n"
					+ "                    alert(\"Better get on a plane. There are no roads between \"  + origin + \" and \" + destination);\r\n"
					+ "                } else {\r\n"
					+ "                    var distance = response.rows[0].elements[0].distance;\r\n"
					+ "                    var duration = response.rows[0].elements[0].duration;\r\n"
					+ "                    console.log(response.rows[0].elements[0].distance);\r\n"
					+ "                    var distance_in_kilo = distance.value / 1000; // the kilom\r\n"
					+ "                    var distance_in_mile = distance.value / 1609.34; // the mile\r\n"
					+ "                    var duration_text = duration.text;\r\n"
					+ "                    var duration_value = duration.value;\r\n"
					+ "					   document.getElementById('delivery-date-i').style.display = 'block';\r\n"
					+ "					   getDeliveryDate(duration_text);\r\n"
					+ "                }\r\n"
					+ "            }\r\n"
					+ "    }\r\n"
					+ "		function getDeliveryDate(duration_text){\r\n"
					+ "        var duration = duration_text.split(\" \");\r\n"
					+ "        var date = new Date();\r\n"
					+ "        var delDate = date.getDate();\r\n"
					+ "        var delMonth = date.getMonth()+1;\r\n"
					+ "        var delYear = date.getFullYear();\r\n"
					+ "        \r\n"
					+ "        switch(duration[1]){\r\n"
					+ "          case 'day' || 'hour' || 'hours': \r\n"
					+ "              delDate = date.getDate()+1;\r\n"
					+ "              delMonth = date.getMonth()+1;\r\n"
					+ "              delYear = date.getFullYear();\r\n"
					+ "              break;\r\n"
					+ "          case 'days':\r\n"
					+ "              delDate = date.getDate()+parseInt(duration[0]);\r\n"
					+ "              delMonth = date.getMonth()+1;\r\n"
					+ "              delYear = date.getFullYear();\r\n"
					+ "              break;\r\n"
					+ "          case 'month':\r\n"
					+ "              delDate = date.getDate();\r\n"
					+ "              delMonth = date.getMonth()+2;\r\n"
					+ "              delYear = date.getFullYear();\r\n"
					+ "              break;\r\n"
					+ "          case 'months':\r\n"
					+ "              delDate = date.getDate();\r\n"
					+ "              delMonth = date.getMonth()+parseInt(duration[0])+1;\r\n"
					+ "              delYear = date.getFullYear();\r\n"
					+ "              break;\r\n"
					+ "          case 'year':\r\n"
					+ "              delDate = date.getDate();\r\n"
					+ "              delMonth = date.getMonth()+1;\r\n"
					+ "              delYear = date.getFullYear()+1;\r\n"
					+ "              break;\r\n"
					+ "          case 'years':\r\n"
					+ "              delDate = date.getDate();\r\n"
					+ "              delMonth = date.getMonth()+1;\r\n"
					+ "              delYear = date.getFullYear()+parseInt(duration[0]);\r\n"
					+ "              break;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        document.getElementById('delivery-date-i').innerHTML = '<b>Delivery on (or) before: </b>'+delDate+'-'+delMonth+'-'+delYear;\r\n"
					+ "    }\r\n"
					+ "    </script>"
					+ "</html>");
			while(rs.next()) {
				out.append("<script>showProduct('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"',\""+rs.getString(4)+"\",\""+rs.getString(4)+"\")</script>");
			}
			out.append("<script>document.getElementById('check-delivery-form').onsubmit = function(e){"
					+ "		e.preventDefault();\r\ncalculateDistance();\r\n"
					+ "};\r\n</script>");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}

class ViewProductDAO{
	
	static ResultSet getProduct(String productId) throws SQLException, ClassNotFoundException{
		String query = "select product_category,product_name,product_unit_price,product_description,product_id from products where product_id='"+productId+"'";
		
		Class.forName(DAO.driverType);
		Connection con = DriverManager.getConnection(DAO.url, DAO.userName, DAO.password);
		Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(query);
		
		return rs;
	}
		
}
