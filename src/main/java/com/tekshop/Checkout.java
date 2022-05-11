package com.tekshop;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Checkout extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String amount = req.getParameter("order-amount");
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.write("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <title>Checkout</title>\r\n"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1'>\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n"
				+ "    <style>\r\n"
				+ "        span b, p b{\r\n"
				+ "            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;\r\n"
				+ "        }\r\n"
				+ "        h2{\r\n"
				+ "            font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;\r\n"
				+ "        }\r\n"
				+ "        #card-no-div{\r\n"
				+ "            width: max-content;\r\n"
				+ "        }\r\n"
				+ "        .card-input{\r\n"
				+ "            width: inherit;\r\n"
				+ "            height: 33px;\r\n"
				+ "            border-right: none;\r\n"
				+ "            border: 0.5px solid black;\r\n"
				+ "            border-radius: 3px;\r\n"
				+ "            border-top-right-radius: 0px;\r\n"
				+ "            border-bottom-right-radius: 0px;\r\n"
				+ "        }\r\n"
				+ "        #cvv, #upi-id{\r\n"
				+ "            width: inherit;\r\n"
				+ "            height: 33px;\r\n"
				+ "            border: 0.5px solid grey;\r\n"
				+ "            border-radius: 3px;\r\n"
				+ "        }\r\n"
				+ "        #card-icon-btn{\r\n"
				+ "            height: 36px;\r\n"
				+ "            border: 0.5px solid black;\r\n"
				+ "            border-radius: 3px;\r\n"
				+ "            border-top-left-radius: 0px;\r\n"
				+ "            border-bottom-left-radius: 0px;\r\n"
				+ "        }\r\n"
				+ "        #card-btn{\r\n"
				+ "            border: 2px solid #2a6dff;\r\n"
				+ "            padding: 10px;\r\n"
				+ "            border-bottom: none;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            border-bottom-right-radius: 0px;\r\n"
				+ "            cursor: pointer;\r\n"
				+ "            background-color: white;\r\n"
				+ "            box-shadow: 0px 0px 1px 2px #8098ff;\r\n"
				+ "        }\r\n"
				+ "        #upi-btn{\r\n"
				+ "            padding: 10px;\r\n"
				+ "            border: 1px solid grey;\r\n"
				+ "            border-left: none;\r\n"
				+ "            border-bottom: none;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            border-top-left-radius: 0px;\r\n"
				+ "            border-bottom-left-radius: 0px;\r\n"
				+ "            cursor: pointer;\r\n"
				+ "            background-color: white;\r\n"
				+ "        }\r\n"
				+ "		   #delivery-select{\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            height: 30px;\r\n"
				+ "            border: none;\r\n"
				+ "            margin: 5px;\r\n"
				+ "        }\r\n"
				+ "        #del-instruct{\r\n"
				+ "            height: auto;\r\n"
				+ "            border: none;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "        }\r\n"
				+ "        td span{\r\n"
				+ "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\r\n"
				+ "            font-size: small;\r\n"
				+ "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "        td{\r\n"
				+ "            padding: 10px;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div id=\"total-bill-div\" style=\"background-color: rgb(247, 235, 217); border: 0.5px solid grey; border-radius: 5px; margin: 5px; padding-left: 5px;\">\r\n"
				+ "        <p><b style=\"font-size: large;\">Total Bill: </b><span id=\"total-bill-span\" style=\"font-size: x-large;\"></span></p>\r\n"
				+ "		</div>\r\n"
				+ "    <div id=\"payment-div\" style=\"background-color: beige; border: 0.5px solid grey; border-radius: 5px; margin: 5px;\">\r\n"
				+ "        <h2 style=\"text-align: center;\">Select Payment method</h2>\r\n"
				+ "        <div style=\"width: max-content; margin: 0 auto; display: block; border-bottom: 2px solid #2a6dff; border-radius: 8px;\">\r\n"
				+ "            <button id=\"card-btn\" onclick=\"openCard();\"><i class=\"fa fa-credit-card\" style=\"width: 50px; height: 20px; font-size: x-large;\"></i></button>\r\n"
				+ "            <button id=\"upi-btn\" onclick=\"openUpi();\"><img src=\"https://www.vectorlogo.zone/logos/upi/upi-ar21.svg\" style=\"width: 50px; height: 23px;\"></button>\r\n"
				+ "        </div>\r\n"
				+ "        <br>\r\n"
				+ "        <div style=\"width: max-content; margin: 0 auto; display: block; text-align: center;\" id=\"card-div\">\r\n"
				+ "            <form action=\"CardPayment\" id=\"card-form\" method=\"post\">\r\n"
				+ "                <span><b>CARD NUMBER</b></span>\r\n"
				+ "				   <input type='hidden' name='amount' id='amount-input'>"
				+ "                <div id=\"card-no-div\" style=\"width: max-content; margin: 0 auto; display: block;\">\r\n"
				+ "                <input type=\"tel\" id=\"card-number\" class=\"card-input\" name=\"card-no\" maxlength=\"18\"><button disabled id=\"card-icon-btn\"><i id=\"card-type-icon\" class=\"fa fa-credit-card\"></i></button> \r\n"
				+ "                </div><br>\r\n"
				+ "                <table>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td>\r\n"
				+ "                        <span><b>EXPIRY DATE</b></span><br>\r\n"
				+ "                        <input type=\"month\" name=\"expiry\" id=\"expiry-date\" style=\"height: 33px;\">\r\n"
				+ "                        </td>\r\n"
				+ "                        <td>\r\n"
				+ "                            <span><b>CVV</b></span><br>\r\n"
				+ "                            <input type=\"tel\" minlength=\"3\" maxlength=\"4\" id=\"cvv\" name=\"cvv\">\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                </table>\r\n"
				+ "                <br>\r\n"
				+ "        <div>\r\n"
				+ "            <p><b>Delivery: </b></p>\r\n"
				+ "            <table>\r\n"
				+ "                <tr>\r\n"
				+ "                    <td><span>Delivery mode: </span></td>\r\n"
				+ "                    <td>\r\n"
				+ "                        <select name=\"delivery-type\" id=\"delivery-select\">\r\n"
				+ "                            <option value=\"normal_del\">Normal Delivery</option>\r\n"
				+ "                            <option value=\"fast_del\">Fast Delivery</option>\r\n"
				+ "                        </select>\r\n"
				+ "                    </td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr>\r\n"
				+ "                    <td><span>Delivery instructions: </span></td>\r\n"
				+ "                    <td>\r\n"
				+ "                        <textarea id=\"del-instruct\" name=\"del-instructions\" placeholder=\"Address, Landmark\"></textarea>\r\n"
				+ "                    </td>\r\n"
				+ "                </tr>\r\n"
				+ "            </table>\r\n"
				+ "            <br>"
				+ "                <button type=\"submit\" style=\"margin: 5px; padding: 8px; background-color: #2a6dff; color: white; border: 0.5px solid #5582ff; border-radius: 5px; box-shadow: 1px 1px 1px #8098ff; cursor: pointer;\">Make Payment</button>\r\n"
				+ "            </form>\r\n"
				+ "        </div>\r\n"
				+ "			</div>\r\n"
				+ "        <div id=\"upi-div\" style=\"width: max-content; margin: 0 auto; display: none; text-align: center;\">\r\n"
				+ "            <form action=\"UpiPayment\" id=\"upi-form\">\r\n"
				+ "                <span><b>Enter UPI ID</b></span><br>\r\n"
				+ "				   <input type='hidden' name='amount' id='upi-amount-input'>"
				+ "                <input name=\"upi-id\" type=\"email\" id=\"upi-id\" name=\"upi-id\"><br><br>\r\n"
				+ "				   <br>\r\n"
				+ "        <div>\r\n"
				+ "            <p><b>Delivery: </b></p>\r\n"
				+ "            <table>\r\n"
				+ "                <tr>\r\n"
				+ "                    <td><span>Delivery mode: </span></td>\r\n"
				+ "                    <td>\r\n"
				+ "                        <select name=\"delivery-type\" id=\"delivery-select\">\r\n"
				+ "                            <option value=\"normal_del\">Normal Delivery</option>\r\n"
				+ "                            <option value=\"fast_del\">Fast Delivery</option>\r\n"
				+ "                        </select>\r\n"
				+ "                    </td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr>\r\n"
				+ "                    <td><span>Delivery instructions: </span></td>\r\n"
				+ "                    <td>\r\n"
				+ "                        <textarea id=\"del-instruct\" name=\"del-instructions\" placeholder=\"Address, Landmark\"></textarea>\r\n"
				+ "                    </td>\r\n"
				+ "                </tr>\r\n"
				+ "            </table>\r\n"
				+ "            <br>"	
				+ "                <button type=\"submit\" style=\"margin: 5px; padding: 8px; background-color: #2a6dff; color: white; border: 0.5px solid #5582ff; border-radius: 5px; box-shadow: 1px 1px 1px #8098ff; cursor: pointer;\" id=\"upi-pay-btn\">Verify & Pay</button>\r\n"
				+ "				</form>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "<script>\r\n"
				+ "    var cardNumber = document.getElementById('card-number');\r\n"
				+ "    var expiry = document.getElementById('expiry-date');\r\n"
				+ "    var cvv = document.getElementById('cvv');\r\n"
				+ "\r\n"
				+ "    cardNumber.addEventListener('keyup',function(){\r\n"
				+ "        const len = cardNumber.value.trim().length;\r\n"
				+ "        if(!(len>=15 && len<=16)){\r\n"
				+ "            cardNumber.style.borderColor = 'red';\r\n"
				+ "            cardNumber.style.borderWidth = '3px';\r\n"
				+ "        }else{\r\n"
				+ "            cardNumber.style.borderColor = 'grey';\r\n"
				+ "            cardNumber.style.borderWidth = '0.5px';\r\n"
				+ "        }\r\n"
				+ "    });\r\n"
				+ "\r\n"
				+ "    expiry.addEventListener('change',function(){\r\n"
				+ "        const expDate = expiry.value;\r\n"
				+ "        const year = expDate.split('-')[0];\r\n"
				+ "        const month = expDate.split('-')[1];\r\n"
				+ "\r\n"
				+ "        if(parseInt(year)<new Date().getFullYear()){\r\n"
				+ "            alert('Incorrect year. Year should be greater than or equal to the current year.');\r\n"
				+ "            expiry.value = '';\r\n"
				+ "        }\r\n"
				+ "    });\r\n"
				+ "\r\n"
				+ "    cvv.addEventListener('keyup',function(){\r\n"
				+ "        const cvvNum = cvv.value;\r\n"
				+ "        if(cvvNum.length<3 || cvvNum.length==0){\r\n"
				+ "            cvv.style.border = '3px solid red';\r\n"
				+ "        }else{\r\n"
				+ "            cvv.style.border = '0.5px solid grey';\r\n"
				+ "        }\r\n"
				+ "    });\r\n"
				+ "    \r\n"
				+ "    function changePaymentMode(mode){\r\n"
				+ "        var cardBtn = document.getElementById('card-btn');\r\n"
				+ "        var upiBtn = document.getElementById('upi-btn');\r\n"
				+ "        if(mode=='card'){\r\n"
				+ "            cardBtn.style = 'border: 2px solid #2a6dff; border-bottom: none; border-radius: 5px; border-bottom-right-radius: 0px; cursor: pointer; background-color: white; box-shadow: 0px 0px 1px 2px #8098ff;';\r\n"
				+ "            upiBtn.style = 'border: 1px solid grey; border-left: none; border-bottom: none; border-radius: 5px; border-top-left-radius: 0px; border-bottom-left-radius: 0px; cursor: pointer; background-color: white;';\r\n"
				+ "        }\r\n"
				+ "        if(mode=='upi'){\r\n"
				+ "            upiBtn.style = 'border: 2px solid #2a6dff; border-bottom: none; border-radius: 5px; border-bottom-left-radius: 0px; cursor: pointer; background-color: white; box-shadow: 0px 0px 1px 2px #8098ff;';\r\n"
				+ "            cardBtn.style = 'border: 1px solid grey; border-right: none; border-bottom: none; border-radius: 5px; border-top-right-radius: 0px; border-bottom-right-radius: 0px; cursor: pointer; background-color: white;box-shadow: none;';    \r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "    \r\n"
				+ "    function showCardDiv(){\r\n"
				+ "        document.getElementById('card-div').style.display = 'block';\r\n"
				+ "        document.getElementById('upi-div').style.display = 'none';\r\n"
				+ "	   	   document.getElementById('amount-input').value="+amount+";\r\n"
				+ "    }\r\n"
				+ "    \r\n"
				+ "    function showUpiDiv(){\r\n"
				+ "        document.getElementById('card-div').style.display = 'none';\r\n"
				+ "        document.getElementById('upi-div').style.display = 'block';\r\n"
				+ "	       document.getElementById('upi-amount-input').value="+amount+";\r\n"
				+ "    }\r\n"
				+ "    \r\n"
				+ "    function openUpi(){\r\n"
				+ "        changePaymentMode('upi');\r\n"
				+ "        showUpiDiv();\r\n"
				+ "    }\r\n"
				+ "    \r\n"
				+ "    function openCard(){\r\n"
				+ "        changePaymentMode('card');\r\n"
				+ "        showCardDiv();\r\n"
				+ "    }\r\n"
				+ "</script>\r\n"
				+ "</html>");
		out.append("<script>document.getElementById('total-bill-span').innerText='₹ "+amount+"/-';</script>");
//		out.append("<script>document.getElementById('card-form').onsubmit = CardPayment;</script>");
//		out.append("<script>document.getElementById('upi-form').onsubmit = UpiPayment;</script>");
	}
}
