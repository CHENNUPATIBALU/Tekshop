package com.tekshop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class orders extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		String user = "";
		out.write("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<title>Orders</title>\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css\">\r\n"
				+ "<style>\r\n"
				+ "    .order{\r\n"
				+ "        border: 0.5px solid black;\r\n"
				+ "        border-radius: 5px;\r\n"
				+ "        margin: 5px;\r\n"
				+ "        padding: 18px;\r\n"
				+ "        cursor: pointer;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .order:hover{\r\n"
				+ "        background-color: aliceblue;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "		h1{\r\n"
				+ "		    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\r\n"
				+ "		    font-style: oblique;\r\n"
				+ "			text-align: center;\r\n"
				+ "		    color: brown;\r\n"
				+ "		}\r\n"
				+ "    .order>span{\r\n"
				+ "        padding: 10px;\r\n"
				+ "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\r\n"
				+ "        font-size: medium;\r\n"
				+ "        font-style: oblique;\r\n"
				+ "        font-weight: bold;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .order a{\r\n"
				+ "        float: right;\r\n"
				+ "        padding: 2px;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .bill-span{\r\n"
				+ "        font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\r\n"
				+ "        font-size: larger;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .total-bill-div>p{\r\n"
				+ "        font-family: Arial, Helvetica, sans-serif;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .order-details div span{\r\n"
				+ "        font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\r\n"
				+ "        font-size: medium;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .order-details p{\r\n"
				+ "        font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    i{\r\n"
				+ "        font-size: larger;\r\n"
				+ "    }\r\n"
				+ "</style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "     <h1>TekShop</h1>\r\n"
				+ "		<h3 id=\"no-orders-h3\" style=\"text-align: center; display: none; font-family:sans-serif;\">No orders found.</h3>\r\n"
				+ "    <div id=\"root-div\">\r\n"
				+ "		<h2 style=\"font-family: sans-serif;\">Your Orders</h2>\r\n"
				+ "	   </div>\r\n"
				+ "</body>\r\n"
				+ "<script>\r\n"
				+ "    function populateOrder(purchaseId,product,totalBill){\r\n"
				+ "        var products = product.split(\";\");\r\n"
				+ "\r\n"
				+ "        var div = document.createElement('div');\r\n"
				+ "        div.classList.add('order');\r\n"
				+ "\r\n"
				+ "        var purchaseIdSpan = document.createElement('span');\r\n"
				+ "        purchaseIdSpan.innerText = purchaseId;\r\n"
				+ "\r\n"
				+ "        var arrow = document.createElement('a');\r\n"
				+ "        var arrowI = document.createElement('i');\r\n"
				+ "        arrowI.classList.add('fas');\r\n"
				+ "        arrowI.classList.add('fa-circle-arrow-right');\r\n"
				+ "        arrow.appendChild(arrowI);\r\n"
				+ "\r\n"
				+ "        var orderDetailsDiv = document.createElement('div');\r\n"
				+ "\r\n"
				+ "        open = false;\r\n"
				+ "        div.addEventListener('click',function(){\r\n"
				+ "            if(open){\r\n"
				+ "                revealOrder(open,arrowI,orderDetailsDiv)\r\n"
				+ "				   open = false;\r\n"
				+ "            }else{\r\n"
				+ "                revealOrder(open,arrowI,orderDetailsDiv)\r\n"
				+ "				   open = true;\r\n"
				+ "            }\r\n"
				+ "        });\r\n"
				+ "\r\n"
				+ "        orderDetailsDiv.classList.add('order-details');\r\n"
				+ "        var itemP = document.createElement('p');\r\n"
				+ "        var b = document.createElement('b');\r\n"
				+ "        b.innerText = \"Items:\"\r\n"
				+ "        itemP.appendChild(b);\r\n"
				+ "\r\n"
				+ "        var div1 = document.createElement('div');\r\n"
				+ "        \r\n"
				+ "        for(let i=0;i<products.length-1;i++){\r\n"
				+ "            var prod = products[i].split(\":\");\r\n"
				+ "            var p = document.createElement('p');\r\n"
				+ "            var nameSpan = document.createElement('span');\r\n"
				+ "            nameSpan.innerHTML = prod[0]+\"&nbsp;(\";\r\n"
				+ "            var qtySpan = document.createElement('span');\r\n"
				+ "            qtySpan.innerHTML = \"Qty: \"+String(prod[1]).split('-')[0]+\")&nbsp;=&nbsp;\"\r\n"
				+ "            nameSpan.appendChild(qtySpan);\r\n"
				+ "            var priceSpan = document.createElement('span');\r\n"
				+ "            priceSpan.innerText = \"₹ \"+String(prod[1]).split('-')[1]+\"/-\"\r\n"
				+ "\r\n"
				+ "            p.appendChild(nameSpan);\r\n"
				+ "            p.appendChild(priceSpan);\r\n"
				+ "            div1.appendChild(p);\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        var totalDiv = document.createElement('div');\r\n"
				+ "        totalDiv.classList.add('total-bill-div');\r\n"
				+ "        var totalP = document.createElement('p');\r\n"
				+ "        totalP.innerHTML = \"<b>Total Bill: </b>\";\r\n"
				+ "        var totalSpan = document.createElement('span');\r\n"
				+ "        totalSpan.classList.add('bill-span');\r\n"
				+ "		   totalSpan.innerText = '₹ '+totalBill+'/-';\r\n"
				+ "        totalP.appendChild(totalSpan);\r\n"
				+ "        totalDiv.appendChild(totalP);\r\n"
				+ "\r\n"
				+ "        orderDetailsDiv.appendChild(itemP);\r\n"
				+ "        orderDetailsDiv.appendChild(div1);\r\n"
				+ "        orderDetailsDiv.appendChild(totalDiv);\r\n"
				+ "		   orderDetailsDiv.style.display = 'none';\r\n"
				+ "\r\n"
				+ "        div.appendChild(purchaseIdSpan);\r\n"
				+ "        div.appendChild(arrow);\r\n"
				+ "        div.appendChild(orderDetailsDiv);\r\n"
				+ "\r\n"
				+ "        document.getElementById('root-div').appendChild(div);\r\n"
				+ "    }\r\n"
				+ "function revealOrder(open,arrowI,orderDetailsDiv){\r\n"
				+ "   if(open){\r\n"
				+ "      arrowI.classList.remove('fa-circle-arrow-down');\r\n"
				+ "      arrowI.classList.add('fa-circle-arrow-right');\r\n"
				+ "      orderDetailsDiv.style.display = 'none';\r\n"
				+ "		 open = false;\r\n"
				+ "   }else{\r\n"
				+ "      arrowI.classList.add('fa-circle-arrow-down');\r\n"
				+ "      arrowI.classList.remove('fa-circle-arrow-right');\r\n"
				+ "      orderDetailsDiv.style.display = 'block';\r\n"
				+ "		 open = true;\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "</script>\r\n"
				+ "</html>");
		Cookie[] cookies = req.getCookies();
		for(Cookie c:cookies) {
			if(c.getName().equals("user_id")) {
				user = c.getValue();
				break;
			}
		}
		
		List<HashMap<String,String>> orders;
		
		try {
			orders = new ordersDAO().getOrders(user);
			if(orders.size()>0) {
				Iterator<HashMap<String, String>> listIterator = orders.iterator();
				
				while(listIterator.hasNext()) {
					HashMap<String,String> order = listIterator.next();
					out.append("<script>populateOrder('"+order.get("PurchaseID")+"','"+order.get("Products")+"','"+order.get("FinalValue")+"');</script>");
				}
			}else {
				out.append("<script>document.getElementById('no-orders-h3').style.display='block'; document.getElementById('root-div').style.display = 'none';</script>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
