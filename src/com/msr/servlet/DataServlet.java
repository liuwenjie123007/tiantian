package com.msr.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msr.utils.ExcelUtil;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/DataServlet")
public class DataServlet extends BaseServlet {
	
	public String printExcel(HttpServletRequest req, HttpServletResponse reps) {
		ExcelUtil eu = new ExcelUtil();
		eu.output(eu.printAll());		
		return "admin/output-result.jsp";
	}
}
