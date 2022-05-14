package com.tekshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductEntry extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String category = req.getParameter("category");
		String prodName = req.getParameter("prod-name");
		String doa = req.getParameter("doa");
		String invLvl = req.getParameter("inv-lvl");
		String shortDesc = req.getParameter("short-desc");
		String longDesc = req.getParameter("long-desc");
		String perUnitPrice = req.getParameter("per-unit-price");
		String discount = req.getParameter("dis-range");
		String activeYes = req.getParameter("y-radio");
		String activeNo = req.getParameter("n-radio");
		Blob image = null;
		try {
			image = new SerialBlob(req.getParameter("prod-img").getBytes());
		} catch (SerialException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean active = false;
		
		if(activeYes!=null) {
			active = true;
		}
		
		ProductEntryDAO ped;
		try {
			ped = new ProductEntryDAO(prodName, longDesc, "", "balu123", "", category, category, "",Integer.parseInt(perUnitPrice), Integer.parseInt(invLvl), "",0,image,active);
			ped.addProduct();
			PrintWriter out = res.getWriter();
			out.write("<script>alert('Product added.'); window.location.href = 'product-entry.html';</script>");
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
