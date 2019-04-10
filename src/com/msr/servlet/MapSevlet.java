package com.msr.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MapSevlet
 */
@WebServlet("/MapSevlet")
public class MapSevlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String,String> map1 = new HashMap<String,String>();
		
		map1.put("1", "1");
		map1.put("2", "2");
		map1.put("2", "2");
		
		req.setAttribute("map1", map1);
		req.getRequestDispatcher("map.jsp").forward(req, resp);
	}
	
}
